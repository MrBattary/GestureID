package michael.linker.gestureid.analyzer.config;

import michael.linker.gestureid.analyzer.config.properties.PropertiesKey;
import michael.linker.gestureid.analyzer.config.properties.PropertiesProvider;

import java.util.List;

public class CalculationsConfiguration {
    public static List<Double> getDispersions() {
        return PropertiesProvider.getDoubleListProperty(PropertiesKey.ANALYSIS_CALCULATION_DISPERSIONS);
    }
}
