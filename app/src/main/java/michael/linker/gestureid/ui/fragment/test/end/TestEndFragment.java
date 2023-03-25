package michael.linker.gestureid.ui.fragment.test.end;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.test.TestConfiguration;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.ui.activity.ActivityGate;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceDialogModel;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceInfoDialog;

public class TestEndFragment extends Fragment {
    private static final String TAG =
            TestEndFragment.class.getCanonicalName();

    private final ActivityResultLauncher<Intent> createDocumentResultLauncher =
            initCreateDocumentLauncher();
    private final Intent createDocumentIntent = initCreateDocumentIntent();

    private MaterialButton endTestButton;
    private IDialog writingErrorDialog, writingSuccessDialog;

    private TestEndViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(
                R.id.navigation_test);
        viewModel = new ViewModelProvider(viewModelStoreOwner).get(TestEndViewModel.class);

        return inflater.inflate(R.layout.fragment_test_end, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFields(view);
        initDialogs();
        initButtons();
    }

    private void initFields(final View view) {
        endTestButton = view.findViewById(R.id.test_end_finish_button);
    }

    private void initButtons() {
        endTestButton.setOnClickListener(
                l -> createDocumentResultLauncher.launch(createDocumentIntent));
    }

    private void initDialogs() {
        writingErrorDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_error_title),
                        StringsProvider.getString(R.string.dialog_test_result_writing_error_text),
                        StringsProvider.getString(R.string.button_test_to_home)
                ),
                (dialogInterface, i) -> {
                    writingErrorDialog.dismiss();
                    ActivityGate.moveToMainActivity(requireActivity());
                });

        writingSuccessDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_success_title),
                        StringsProvider.getString(R.string.dialog_test_result_writing_success_text),
                        StringsProvider.getString(R.string.button_test_to_home)
                ),
                (dialogInterface, i) -> {
                    writingErrorDialog.dismiss();
                    ActivityGate.moveToMainActivity(requireActivity());
                });
    }

    private Intent initCreateDocumentIntent() {
        Intent createDocumentIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        createDocumentIntent.addCategory(Intent.CATEGORY_OPENABLE);
        createDocumentIntent.setType("text/plain");
        createDocumentIntent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,
                Uri.parse(TestConfiguration.Build.getTestResultFolderPath()));
        createDocumentIntent.putExtra(Intent.EXTRA_TITLE,
                Long.toString(Instant.now().toEpochMilli()));
        return createDocumentIntent;
    }

    private ActivityResultLauncher<Intent> initCreateDocumentLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        final Uri filepath = result.getData().getData();
                        try (OutputStreamWriter bufferedOutputStreamWriter =
                                     new OutputStreamWriter(
                                             requireContext().getContentResolver()
                                                     .openOutputStream(filepath),
                                             StandardCharsets.UTF_8)) {
                            viewModel.writeDataFromNetworkToStream(bufferedOutputStreamWriter);

                            writingSuccessDialog.show();
                        } catch (IOException e) {
                            Log.e(TAG,
                                    "An error occurred during writing test results to the output "
                                            + "file.",
                                    e);
                            writingErrorDialog.show();
                        }
                    } else {
                        Log.e(TAG, "Failed to start recording test results due to user choice.");
                        writingErrorDialog.show();
                    }
                });
    }
}