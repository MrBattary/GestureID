package michael.linker.gestureid.core.sensor.manager;

import android.content.Context;
import android.hardware.SensorManager;

import michael.linker.gestureid.core.App;

public class HardwareSensorManagerSingleton {
    private static AHardwareSensorManager hardwareSensorManager = null;

    public static AHardwareSensorManager getInstance() {
        if (hardwareSensorManager == null) {
            SensorManager hardwareSensorManager = (SensorManager)
                    App.getInstance().getSystemService(Context.SENSOR_SERVICE);
            HardwareSensorManagerSingleton.hardwareSensorManager =
                    new HardwareSensorManagerWrapper(hardwareSensorManager);
        }
        return hardwareSensorManager;
    }
}
