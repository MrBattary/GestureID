package michael.linker.gestureid.data.res;

import android.content.res.Resources;

import java.util.Map;

import michael.linker.gestureid.R;
import michael.linker.gestureid.core.App;
import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;

public class ColorsProvider {
    private static final Resources RESOURCES = App.getRes();
    private static final Resources.Theme THEME = App.getInstance().getTheme();

    /**
     * Retrieve a color for a particular resource ID.
     *
     * @param id resource ID
     * @return resource value
     */
    public static int getColor(final int id) {
        return RESOURCES.getColor(id, THEME);
    }

    public static class Axis {
        private static final Map<SensorAxisType, Integer> AXIS_COLOR_MAP;
        private static final int AXIS_COLOR_DEFAULT;

        static {
            AXIS_COLOR_MAP = Map.of(
                    SensorAxisType.X, getColor(R.color.axis_x),
                    SensorAxisType.Y, getColor(R.color.axis_y),
                    SensorAxisType.Z, getColor(R.color.axis_z),
                    SensorAxisType.W, getColor(R.color.axis_w)
            );
            AXIS_COLOR_DEFAULT = getColor(R.color.axis_default);
        }

        /**
         * Get color ID associated with the provided axis type.
         *
         * @param axisType provided axis type.
         * @return ID of the color in the res.
         */
        public static Integer getColorForAxis(SensorAxisType axisType) {
            return AXIS_COLOR_MAP.getOrDefault(axisType, AXIS_COLOR_DEFAULT);
        }
    }
}
