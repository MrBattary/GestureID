package michael.linker.gestureid.analyzer.addons.csv;

import java.util.Collection;

public class Csv {
    private static final String DELIMITER = ",";
    private static final String CSV_EXTENSION = ".csv";

    public static String format(Collection<String> collection) {
        return String.join(DELIMITER, collection);
    }

    public static String getExtension() {
        return CSV_EXTENSION;
    }
}
