package michael.linker.gestrudeid.elements.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestrudeid.R;
import michael.linker.gestrudeid.elements.config.TestLoopConfiguration;
import michael.linker.gestrudeid.elements.task.ButtonTask;
import michael.linker.gestrudeid.elements.task.ITask;
import michael.linker.gestrudeid.stream.output.model.FileOutputModel;
import michael.linker.gestrudeid.world.IWorld;
import michael.linker.gestrudeid.world.singleton.WorldSingleton;

public class ButtonActivity extends AppCompatActivity {
    private static final String TEST_FILE = "ButtonTest.txt";
    private final IWorld world = WorldSingleton.getInstance();
    private AlertDialog successAlertDialog;
    private ITask buttonTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        FileOutputModel fileOutputModel = new FileOutputModel(
                TestLoopConfiguration.getDestination(),
                TestLoopConfiguration.getTestLoopFolder(),
                TEST_FILE);
        world.setNewOutputStream(fileOutputModel);

        AlertDialog.Builder descriptionAlertBuilder = new AlertDialog.Builder(this);
        descriptionAlertBuilder.setTitle(R.string.description);
        descriptionAlertBuilder.setMessage(R.string.button_test_description);
        descriptionAlertBuilder.setPositiveButton(R.string.start, (dialogInterface, i) -> {
            buttonTask.start();
            world.unsuppressRegistering();
            dialogInterface.dismiss();
        });
        AlertDialog descriptionAlertDialog = descriptionAlertBuilder.create();
        descriptionAlertDialog.show();

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

        buttonTask = new ButtonTask(this, this::endActivity);
    }

    private void endActivity() {
        world.suppressRegistering();
        successAlertDialog.show();
    }
}