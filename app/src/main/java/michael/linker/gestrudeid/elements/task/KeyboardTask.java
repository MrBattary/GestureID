package michael.linker.gestrudeid.elements.task;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Deque;

import michael.linker.gestrudeid.R;
import michael.linker.gestrudeid.config.ActivitiesBuildConfiguration;

public class KeyboardTask implements ITask {
    private final Deque<String> wordsOrder = ActivitiesBuildConfiguration.getWordsOrder();
    private String currentWord = null;
    private final TextView textOutput;
    private final EditText textInput;
    private TextWatcher textWatcher;
    private final Runnable finishMethod;

    /**
     * Default constructor
     *
     * @param activity     KeyboardActivity or another activity that provides text input and output
     * @param finishMethod The function or lambda that will be called after completing this task
     */
    public KeyboardTask(final Activity activity, final Runnable finishMethod) {
        this.textOutput = activity.findViewById(R.id.keyboard__text);
        this.textInput = activity.findViewById(R.id.keyboard__input);
        this.finishMethod = finishMethod;
        initializeTextWatcher();
    }

    @Override
    public void start() {
        textInput.addTextChangedListener(textWatcher);
        textInput.requestFocus();
        proceed();
    }

    @Override
    public void proceed() {
        if (!wordsOrder.isEmpty()) {
            currentWord = wordsOrder.pollFirst();
            textOutput.setText(currentWord);
        } else {
            finish();
        }
    }

    private void finish() {
        textInput.removeTextChangedListener(textWatcher);
        textInput.setText("");
        textOutput.setText("");
        finishMethod.run();
    }

    private void initializeTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Stub
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals(currentWord)) {
                    textInput.getText().clear();
                    proceed();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Stub
            }
        };
    }
}
