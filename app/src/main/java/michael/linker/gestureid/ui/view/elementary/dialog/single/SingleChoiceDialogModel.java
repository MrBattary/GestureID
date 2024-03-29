package michael.linker.gestureid.ui.view.elementary.dialog.single;

public class SingleChoiceDialogModel {
    final String title, message, acceptButtonText;

    public SingleChoiceDialogModel(
            final String title,
            final String message,
            final String acceptButtonText) {
        this.title = title;
        this.message = message;
        this.acceptButtonText = acceptButtonText;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getAcceptButtonText() {
        return acceptButtonText;
    }
}
