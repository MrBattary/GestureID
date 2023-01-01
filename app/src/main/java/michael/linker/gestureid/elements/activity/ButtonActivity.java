package michael.linker.gestureid.elements.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestureid.R;
import michael.linker.gestureid.elements.config.TestLoopConfiguration;
import michael.linker.gestureid.elements.task.ButtonTask;
import michael.linker.gestureid.elements.task.ITask;
import michael.linker.gestureid.stream.output.model.FileOutputModel;
import michael.linker.gestureid.world.IWorld;
import michael.linker.gestureid.world.singleton.WorldSingleton;

public class ButtonActivity extends AppCompatActivity {
    private static final String TEST_FILE = "ButtonTest.txt";
    private final IWorld world = WorldSingleton.getInstance();
    private AlertDialog descriptionAlertDialog;
    private AlertDialog successAlertDialog;
    private ITask buttonTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        openFileForRecording();
        buildDescriptionDialog();
        buildSuccessDialog();
        descriptionAlertDialog.show();

        buttonTask = new ButtonTask(this, this::onTaskEnd);
    }

    private void openFileForRecording() {
        FileOutputModel fileOutputModel = new FileOutputModel(
                TestLoopConfiguration.getDestination(),
                TestLoopConfiguration.getTestLoopFolder(),
                TEST_FILE);
        world.setNewOutputStream(fileOutputModel);
    }

    private void buildDescriptionDialog() {
        AlertDialog.Builder descriptionAlertBuilder = new AlertDialog.Builder(this);
        descriptionAlertBuilder.setTitle(R.string.description);
        descriptionAlertBuilder.setMessage(R.string.button_test_description);
        descriptionAlertBuilder.setPositiveButton(R.string.start, (dialogInterface, i) -> {
            buttonTask.start();
            world.unsuppressRegistering();
            dialogInterface.dismiss();
        });
        descriptionAlertDialog = descriptionAlertBuilder.create();
    }

    private void buildSuccessDialog() {
        AlertDialog.Builder successAlertBuilder = new AlertDialog.Builder(this);
        successAlertBuilder.setTitle(R.string.success);
        successAlertBuilder.setMessage(R.string.success_description);
        successAlertBuilder.setPositiveButton(R.string.next, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            Intent activity = new Intent(ButtonActivity.this, SwipeActivity.class);
            activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(activity);
            finish();
        });
        successAlertDialog = successAlertBuilder.create();
    }

    private void onTaskEnd() {
        world.suppressRegistering();
        world.closeOutputStream();
        successAlertDialog.show();
    }
}