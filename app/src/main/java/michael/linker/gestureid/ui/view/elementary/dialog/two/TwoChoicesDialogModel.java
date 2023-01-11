package michael.linker.gestureid.ui.view.elementary.dialog.two;

public class TwoChoicesDialogModel {
    final String title, message, acceptButtonText, rejectButtonText;

    public TwoChoicesDialogModel(
            final String title,
            final String message,
            final String acceptButtonText,
            final String rejectButtonText) {
        this.title = title;
        this.message = message;
        this.acceptButtonText = acceptButtonText;
        this.rejectButtonText = rejectButtonText;
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

    public String getRejectButtonText() {
        return rejectButtonText;
    }
}
