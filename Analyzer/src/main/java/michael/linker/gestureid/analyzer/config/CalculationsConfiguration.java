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
    public static String getDispersionModeFirstMatch() {
        return PropertiesProvider.getStringProperty(PropertiesKey.ANALYSIS_CALCULATION_DISPERSION_MODE_FIRST_MATCH);
    }
    public static String getDispersionModeFull() {
        return PropertiesProvider.getStringProperty(PropertiesKey.ANALYSIS_CALCULATION_DISPERSION_MODE_FULL);
    }
    public static List<Double> getDispersions() {
        return PropertiesProvider.getDoubleListProperty(PropertiesKey.ANALYSIS_CALCULATION_DISPERSIONS);
    }
}
