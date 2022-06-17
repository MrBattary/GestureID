package michael.linker.gestrudeid.elements.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestrudeid.R;
import michael.linker.gestrudeid.elements.config.TestLoopConfiguration;
import michael.linker.gestrudeid.elements.task.ITask;
import michael.linker.gestrudeid.elements.task.SwipeTask;
import michael.linker.gestrudeid.stream.output.model.FileOutputModel;
import michael.linker.gestrudeid.world.IWorld;
import michael.linker.gestrudeid.world.singleton.WorldSingleton;

public class SwipeActivity extends AppCompatActivity {
    private static final String TEST_FILE = "SwipeTest.txt";
    private final IWorld world = WorldSingleton.getInstance();
    private AlertDialog descriptionAlertDialog;
    private AlertDialog successAlertDialog;
    private boolean isGesturesRegisters = false;
    private ITask swipeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        openFileForRecording();
        buildDescriptionDialog();
        buildSuccessDialog();
        descriptionAlertDialog.show();
        swipeTask = new SwipeTask(this, this::onTaskEnd);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isGesturesRegisters) {
            return super.onTouchEvent(event);
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            swipeTask.proceed();
            return true;
        }
        return super.onTouchEvent(event);
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
        descriptionAlertBuilder.setMessage(R.string.swipe_test_description);
        descriptionAlertBuilder.setPositiveButton(R.string.start, (dialogInterface, i) -> {
            swipeTask.start();
            isGesturesRegisters = true;
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
            Intent activity = new Intent(SwipeActivity.this, KeyboardActivity.class);
            activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(activity);
            finish();
        });
        successAlertDialog = successAlertBuilder.create();
    }

    private void onTaskEnd() {
        world.suppressRegistering();
        isGesturesRegisters = false;
        successAlertDialog.show();
    }
}