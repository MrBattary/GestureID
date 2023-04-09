package michael.linker.gestureid.analyzer.addons.csv;

import java.util.Collection;

public class Csv {
    private static final String DELIMITER = ",";

    public static String format(Collection<String> collection) {
        return String.join(DELIMITER, collection);
    }
}
