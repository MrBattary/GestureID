package michael.linker.gestureid.analyzer.config;

import michael.linker.gestureid.analyzer.config.properties.PropertiesKey;
import michael.linker.gestureid.analyzer.config.properties.PropertiesProvider;

import java.util.List;

public class CalculationsConfiguration {
    public static String getAmountType() {
        return PropertiesProvider.getStringProperty(PropertiesKey.ANALYSIS_CALCULATION_AMOUNT_TYPE);
    }
    public static String getDispersionType() {
        return PropertiesProvider.getStringProperty(PropertiesKey.ANALYSIS_CALCULATION_DISPERSION_TYPE);
    }
    public static List<Double> getDispersions() {
        return PropertiesProvider.getDoubleListProperty(PropertiesKey.ANALYSIS_CALCULATION_DISPERSIONS);
    }

    public static String getFarType() {
        return PropertiesProvider.getStringProperty(PropertiesKey.ANALYSIS_CALCULATION_FAR_TYPE);
    }
    public static List<Double> getFarDispersions() {
        return PropertiesProvider.getDoubleListProperty(PropertiesKey.ANALYSIS_CALCULATION_FAR_DISPERSIONS);
    }

    public static String getIntersectorModeFirstMatch() {
        return PropertiesProvider.getStringProperty(PropertiesKey.ANALYSIS_CALCULATION_INTERSECTOR_MODE_FIRST_MATCH);
    }
    public static String getIntersectorModeFull() {
        return PropertiesProvider.getStringProperty(PropertiesKey.ANALYSIS_CALCULATION_INTERSECTOR_MODE_FULL);
    }
}
