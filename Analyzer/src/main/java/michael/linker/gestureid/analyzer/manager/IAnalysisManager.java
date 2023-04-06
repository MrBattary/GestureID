package michael.linker.gestureid.analyzer.manager;

public interface IAnalysisManager {
    /**
     * Reads the source data, performs analysis, records the results.
     *
     * @throws AnalysisManagerFailedException If the analysis failed for any reason.
     */
    void analyze() throws AnalysisManagerFailedException;
}
