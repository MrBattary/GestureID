package michael.linker.gestureid.ui.activity.intent.playground;

import android.os.Parcel;
import android.os.Parcelable;

import michael.linker.gestureid.data.system.metric.type.SystemMode;

public class PlaygroundSettingsParcelable implements Parcelable {
    private SystemMode mode;

    public PlaygroundSettingsParcelable() {
        mode = SystemMode.CONTROL;
    }

    public PlaygroundSettingsParcelable(SystemMode mode) {
        this.mode = mode;
    }

    protected PlaygroundSettingsParcelable(Parcel source) {
        mode = SystemMode.fromBoolean(source.readBoolean());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int i) {
        destination.writeBoolean(mode.toBoolean());
    }

    public void setMode(SystemMode mode) {
        this.mode = mode;
    }

    public SystemMode getMode() {
        return mode;
    }

    public static final Creator<PlaygroundSettingsParcelable> CREATOR = new Creator<>() {
        @Override
        public PlaygroundSettingsParcelable createFromParcel(Parcel source) {
            return new PlaygroundSettingsParcelable(source);
        }

        @Override
        public PlaygroundSettingsParcelable[] newArray(int size) {
            return new PlaygroundSettingsParcelable[size];
        }
    };
}
