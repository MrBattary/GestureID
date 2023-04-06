package michael.linker.gestureid.analyzer.manager;

import michael.linker.gestureid.analyzer.config.FileConfiguration;
import michael.linker.gestureid.analyzer.file.utils.FileUtils;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AnalysisManager implements IAnalysisManager {
    private static final Logger log = LogManager.getLogger(AnalysisManager.class);
    private List<UserModel> userModels;

    public AnalysisManager() {
        this.userModels = new ArrayList<>();
    }

    @Override
    public void analyze() throws AnalysisManagerFailedException {
        try {
            loadSources();
            calculate();
            writeResults();
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

    private void calculate() throws RuntimeException {
        log.info("The calculation of values for user models has started.");
        log.info("The calculation of values for user models has ended.");
    }

    private void writeResults() throws RuntimeException {
        log.info("The output of the calculation results has started.");
        log.info("The output of the calculation results has ended.");
    }
}
