package michael.linker.gestureid.ui.activity;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.Configuration;
import michael.linker.gestureid.config.ConfigurationChain;
import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.config.system.SystemGateConfiguration;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.data.sensor.manager.ISensorManager;
import michael.linker.gestureid.data.system.gate.ISystemGate;
import michael.linker.gestureid.data.system.gate.ISystemGateListener;
import michael.linker.gestureid.data.system.gate.SystemGateAuthResult;
import michael.linker.gestureid.databinding.ActivityPlaygroundBinding;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialogModel;

public class PlaygroundActivity extends AppCompatActivity implements ISystemGateListener {
    private IActiveEventAccumulator activeEventAccumulator;
    private ISystemGate systemGate;
    private ISensorManager manager;

    private IDialog leaveDialog, authStubDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlaygroundBinding binding = ActivityPlaygroundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDialogs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initConfigurable();
    }

    @Override
    protected void onPause() {
        activeEventAccumulator.unsubscribeAll();
        systemGate.shutdown();
        manager.destroy();
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                ((IActiveFlushableEventAccumulator) EventAccumulatorConfiguration.getActiveAccumulator()).startAccumulation();
                break;
            case (MotionEvent.ACTION_UP):
                ((IActiveFlushableEventAccumulator) EventAccumulatorConfiguration.getActiveAccumulator()).flush();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (!getOnBackPressedDispatcher().hasEnabledCallbacks()) {
            leaveDialog.show();
        } else {
            super.onBackPressed();
        }
    }

    private void initDialogs() {
        leaveDialog = new TwoChoicesDialog(
                this,
                new TwoChoicesDialogModel(
                        StringsProvider.getString(R.string.dialog_leave_playground_title),
                        StringsProvider.getString(R.string.dialog_leave_playground_text),
                        StringsProvider.getString(R.string.button_leave),
                        StringsProvider.getString(R.string.button_cancel)),
                (dialogInterface, i) -> {
                    leaveDialog.dismiss();
                    ActivityGate.moveToMainActivity(this);
                },
                (dialogInterface, i) -> leaveDialog.dismiss()
        );
        authStubDialog = new TwoChoicesDialog(
                this,
                new TwoChoicesDialogModel(
                        StringsProvider.getString(R.string.dialog_auth_title),
                        StringsProvider.getString(R.string.dialog_auth_text),
                        StringsProvider.getString(R.string.button_yes),
                        StringsProvider.getString(R.string.button_no)),
                (dialogInterface, i) -> {
                    leaveDialog.dismiss();
                    systemGate.notifyAboutAuthResult(SystemGateAuthResult.AUTH_ACQUIRED);
                },
                (dialogInterface, i) -> {
                    leaveDialog.dismiss();
                    systemGate.notifyAboutAuthResult(SystemGateAuthResult.AUTH_FAILED);
                }
        );
    }

    private void initConfigurable() {
        Configuration.updateConfiguration(
                new ConfigurationChain(
                        EventAccumulatorConfiguration.Type.ACTIVE_FLUSHABLE,
                        SystemConfiguration.Type.Status.ENABLED
                ));
        systemGate = SystemGateConfiguration.getSystemGate();
        systemGate.subscribe(this);

        activeEventAccumulator = EventAccumulatorConfiguration.getActiveAccumulator();
        activeEventAccumulator.subscribe(systemGate);

        manager = SensorManagerConfiguration.getManager();
        manager.unsuppressRegistering();
    }

    @Override
    public void requireAuth() {
        authStubDialog.show();
    }
}