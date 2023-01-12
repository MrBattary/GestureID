package michael.linker.gestureid.ui.activity.intent.playground;

import android.content.Intent;

import michael.linker.gestureid.ui.activity.intent.IIntent;

public class PlaygroundSettingsIntent extends IIntent {
    private static final String KEY = "EnterPlaygroundIntentParcelable";

    public static Intent pack(PlaygroundSettingsParcelable parcelable, Intent intent) {
        return intent.putExtra(KEY, parcelable);
    }

    public static PlaygroundSettingsParcelable unpack(Intent intent) {
        return (PlaygroundSettingsParcelable) intent.getParcelableExtra(KEY);
    }
}
