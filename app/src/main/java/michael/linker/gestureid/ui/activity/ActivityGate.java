package michael.linker.gestureid.ui.activity;

import android.app.Activity;
import android.content.Intent;

import michael.linker.gestureid.ui.activity.intent.playground.PlaygroundSettingsIntent;
import michael.linker.gestureid.ui.activity.intent.playground.PlaygroundSettingsParcelable;

public class ActivityGate {
    public static void moveToMainActivity(final Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        ActivityGate.moveToActivityWithoutHistory(activity, intent);
    }

    public static void moveToPlaygroundActivity(final Activity activity,
            PlaygroundSettingsParcelable settings) {
        Intent intent = new Intent(activity, PlaygroundActivity.class);
        ActivityGate.moveToActivityWithoutHistory(activity, PlaygroundSettingsIntent.pack(settings, intent));
    }

    public static void moveToTestActivity(final Activity activity) {
        Intent intent = new Intent(activity, TestActivity.class);
        ActivityGate.moveToActivityKeepHistory(activity, intent);
    }

    /**
     * After executing the function, the activity will be closed, but the processes associated
     * with the App will remain in the system, such as connecting to the database and so on.
     *
     * @param activity active activity
     */
    public static void finishApplication(final Activity activity) {
        activity.finishAndRemoveTask();
    }

    /**
     * After executing the function, the activity will be closed and all allocated memory will be
     * freed, connection to the database will be closed and so on.
     *
     * @param activity active activity
     */
    public static void shutdownApplication(final Activity activity) {
        activity.finishAffinity();
        System.exit(0);
    }

    private static void moveToActivityWithoutHistory(final Activity activity, final Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        activity.startActivity(intent);
        activity.finish();
    }

    private static void moveToActivityKeepHistory(final Activity activity, final Intent intent) {
        activity.startActivity(intent);
        activity.finish();
    }
}
