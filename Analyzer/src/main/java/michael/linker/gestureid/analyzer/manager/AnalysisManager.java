package michael.linker.gestureid.analyzer.manager;

import michael.linker.gestureid.analyzer.addons.file.utils.FileUtils;
import michael.linker.gestureid.analyzer.calculator.ICalculator;
import michael.linker.gestureid.analyzer.calculator.amount.AmountCalculator;
import michael.linker.gestureid.analyzer.calculator.dispersion.DispersionCalculator;
import michael.linker.gestureid.analyzer.calculator.far.FarCalculator;
import michael.linker.gestureid.analyzer.config.FileConfiguration;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AnalysisManager implements IAnalysisManager {
    private static final Logger log = LogManager.getLogger(AnalysisManager.class);
    private final List<ICalculator> calculators;
    private final List<UserModel> userModels;

    public AnalysisManager() {
        this.calculators = new ArrayList<>();
        calculators.add(new DispersionCalculator());
        calculators.add(new FarCalculator());
        calculators.add(new AmountCalculator());
        this.userModels = new ArrayList<>();
    }

    @Override
    public void analyze() throws AnalysisManagerFailedException {
        try {
            loadSources();
            removePreviousResults();
            calculate();
        } catch (RuntimeException e) {
            throw new AnalysisManagerFailedException(e);
        }
    }

    private void loadSources() throws RuntimeException {
        log.info("The loading of user models from files has started.");
        List<Path> userModelPaths = FileUtils.getPathsFromDirectory(FileConfiguration.getResultsSourceDirectoryPath());
        for (Path userModelPath : userModelPaths) {
            userModels.add(new UserModel(userModelPath));
        }
        log.info("The loading of user models from files has ended.");
    }

    private void removePreviousResults() throws RuntimeException {
        String resultDirectoryPath = FileConfiguration.getResultsDestinationDirectoryPath();
        FileUtils.deleteFolder(resultDirectoryPath);
        log.info(String.format("The previous results from directory %s have been deleted.", resultDirectoryPath));
    }

    private void calculate() throws RuntimeException {
        log.info("The calculation of values for user models has started.");
        for (ICalculator calculator : calculators) {
            calculator.calculate(userModels);
        }
        log.info("The calculation of values for user models has ended.");
    }
}
