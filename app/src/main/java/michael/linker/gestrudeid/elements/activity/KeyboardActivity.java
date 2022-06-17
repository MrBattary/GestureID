package michael.linker.gestrudeid.elements.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestrudeid.R;
import michael.linker.gestrudeid.elements.task.ITask;
import michael.linker.gestrudeid.elements.task.KeyboardTask;

public class KeyboardActivity extends AppCompatActivity {
    private AlertDialog successAlertDialog;
    private ITask keyboardTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        AlertDialog.Builder descriptionAlertBuilder = new AlertDialog.Builder(this);
        descriptionAlertBuilder.setTitle(R.string.description);
        descriptionAlertBuilder.setMessage(R.string.keyboard_test_description);
        descriptionAlertBuilder.setPositiveButton(R.string.start, (dialogInterface, i) -> {
            keyboardTask.start();
            dialogInterface.dismiss();
        });
        AlertDialog descriptionAlertDialog = descriptionAlertBuilder.create();
        descriptionAlertDialog.show();

        AlertDialog.Builder successAlertBuilder = new AlertDialog.Builder(this);
        successAlertBuilder.setTitle(R.string.success);
        successAlertBuilder.setMessage(R.string.finish_description);
        successAlertBuilder.setPositiveButton(R.string.finish, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            Intent activity = new Intent(KeyboardActivity.this, MainActivity.class);
            activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(activity);
            finish();
        });
        successAlertDialog = successAlertBuilder.create();

        keyboardTask = new KeyboardTask(this, () -> successAlertDialog.show());
    }
}