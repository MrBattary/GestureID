package michael.linker.gestureid.analyzer;

import michael.linker.gestureid.analyzer.manager.AnalysisManager;
import michael.linker.gestureid.analyzer.manager.AnalysisManagerFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            new AnalysisManager().analyze();
        } catch (AnalysisManagerFailedException e) {
            log.error(e.getMessage(), e);
        }
    }
}