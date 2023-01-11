package michael.linker.gestureid.ui.view.elementary.dialog.two;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;

public class TwoChoicesDialog implements IDialog {
    private final MaterialAlertDialogBuilder dialogBuilder;
    private AlertDialog dialog;

    public TwoChoicesDialog(
            final Context fragmentContext,
            final TwoChoicesDialogModel dialogModel,
            final DialogInterface.OnClickListener acceptListener,
            final DialogInterface.OnClickListener rejectListener) {
        dialogBuilder = new MaterialAlertDialogBuilder(fragmentContext);
        dialogBuilder.setTitle(dialogModel.getTitle());
        dialogBuilder.setMessage(dialogModel.getMessage());
        dialogBuilder.setPositiveButton(dialogModel.getAcceptButtonText(), acceptListener);
        dialogBuilder.setNegativeButton(dialogModel.getRejectButtonText(), rejectListener);
        dialogBuilder.setCancelable(false);
        dialogBuilder.create();
    }

    @Override
    public void show() {
        dialog = dialogBuilder.show();
    }

    @Override
    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
