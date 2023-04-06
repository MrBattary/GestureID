package michael.linker.gestureid.analyzer.manager;

import michael.linker.gestureid.analyzer.config.FileConfiguration;
import michael.linker.gestureid.analyzer.file.utils.FileUtils;
import michael.linker.gestureid.analyzer.user.model.UserModel;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AnalysisManager implements IAnalysisManager {
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
        List<Path> userModelPaths = FileUtils.getPathsFromDirectory(FileConfiguration.getResultsSourceDirectoryPath());
        for (Path userModelPath : userModelPaths) {
            userModels.add(new UserModel(userModelPath));
        }
    }

    private void calculate() throws RuntimeException {
    }

    private void writeResults() throws RuntimeException {
    }
}
