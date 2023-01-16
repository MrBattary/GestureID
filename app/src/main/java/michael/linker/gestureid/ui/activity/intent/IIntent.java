package michael.linker.gestureid.ui.activity.intent;

import android.content.Intent;

/**
 * Parametrized Intent
 */
public abstract class IIntent {
    /**
     * Pack data from model to the bundle.
     *
     * @param <T>        type of the model
     * @param parcelable parcelable with data.
     * @param intent     storage for data.
     * @return intent with packed data.
     */
    protected static <T> Intent pack(T parcelable, Intent intent) {
        throw new RuntimeException("Implement me!");
    }

    /**
     * Unpack data from intent.
     *
     * @param <T>    type of the model
     * @param intent intent with packed data.
     * @return model with data.
     */
    protected static <T> T unpack(final Intent intent) {
        throw new RuntimeException("Implement me!");
    }
}