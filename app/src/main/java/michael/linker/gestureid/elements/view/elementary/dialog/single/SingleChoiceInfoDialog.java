package michael.linker.gestureid.elements.view.elementary.dialog.single;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import michael.linker.gestureid.elements.view.elementary.dialog.IDialog;

public class SingleChoiceInfoDialog implements IDialog {
    private final MaterialAlertDialogBuilder dialogBuilder;
    private AlertDialog dialog;

    public SingleChoiceInfoDialog(
            final Context fragmentContext,
            final SingleChoiceDialogModel dialogModel,
            final DialogInterface.OnClickListener acceptListener) {
        dialogBuilder = new MaterialAlertDialogBuilder(fragmentContext);
        dialogBuilder.setTitle(dialogModel.getTitle());
        dialogBuilder.setMessage(dialogModel.getMessage());
        dialogBuilder.setPositiveButton(dialogModel.getAcceptButtonText(), acceptListener);
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
