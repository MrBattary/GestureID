package michael.linker.gestureid.ui.activity;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.data.sensor.manager.ISensorManager;
import michael.linker.gestureid.databinding.ActivityPlaygroundBinding;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialogModel;

public class PlaygroundActivity extends AppCompatActivity {
    private ISensorManager manager;

    private IDialog leaveDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlaygroundBinding binding = ActivityPlaygroundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDialogs();
        initSensorManager();
    }

    @Override
    protected void onDestroy() {
        manager.destroy();
        super.onDestroy();
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
    }

    private void initSensorManager() {
        EventAccumulatorConfiguration.getFlushableActiveAccumulator();
        manager = SensorManagerConfiguration.getFreshManager();
        manager.suppressRegistering();
    }
}