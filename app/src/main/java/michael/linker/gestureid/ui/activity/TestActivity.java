package michael.linker.gestureid.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestureid.R;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.databinding.ActivityTestBinding;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialogModel;

public class TestActivity extends AppCompatActivity {

    private IDialog interruptDialog;

    // TODO: Enable gesture system like in the PlaygroundActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDialogs();
    }

    @Override
    public void onBackPressed() {
        if (!getOnBackPressedDispatcher().hasEnabledCallbacks()) {
            interruptDialog.show();
        } else {
            super.onBackPressed();
        }
    }

    private void initDialogs() {
        interruptDialog = new TwoChoicesDialog(this, new TwoChoicesDialogModel(
                StringsProvider.getString(R.string.dialog_interrupt_test_title),
                StringsProvider.getString(R.string.dialog_interrupt_test_text),
                StringsProvider.getString(R.string.button_interrupt),
                StringsProvider.getString(R.string.button_cancel)),
                (dialogInterface, i) -> {
                    interruptDialog.dismiss();
                    ActivityGate.moveToMainActivity(this);
                },
                (dialogInterface, i) -> interruptDialog.dismiss()
        );
    }
}