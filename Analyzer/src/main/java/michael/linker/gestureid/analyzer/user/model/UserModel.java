package michael.linker.gestureid.analyzer.user.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import michael.linker.gestureid.analyzer.addons.file.input.IInputFile;
import michael.linker.gestureid.analyzer.addons.file.input.InputFile;
import michael.linker.gestureid.analyzer.addons.file.input.exception.InputFileReadingFailedException;
import michael.linker.gestureid.analyzer.config.UserModelConfiguration;
import michael.linker.gestureid.analyzer.user.model.exception.UserModelCreationFailedException;
import michael.linker.gestureid.analyzer.user.model.node.UserModelNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private static final Logger log = LogManager.getLogger(UserModel.class);
    private static final String MODEL_MARK = UserModelConfiguration.getUserModelMark();
    private static final int BEGIN_OF_FILENAME = 0;
    private static final char FILENAME_EXTENSION_DOT = '.';
    private final String modelName;
    private final List<UserModelNode> userModelNodes;

    /**
     * Constructor of User model from the File.
     *
     * @param modelPath Path to the User model file.
     */
    public UserModel(Path modelPath) throws UserModelCreationFailedException {
        userModelNodes = new ArrayList<>();
        Gson gson = new Gson();

        try {
            IInputFile inputFile = new InputFile(modelPath);
            for (String nodeAsJson : inputFile.readAllLines()) {
                UserModelNode node = gson.fromJson(nodeAsJson, UserModelNode.class);
                userModelNodes.add(node);
            }
            String filename = modelPath.getFileName().toString();
            modelName = MODEL_MARK + filename.substring(BEGIN_OF_FILENAME, filename.lastIndexOf(FILENAME_EXTENSION_DOT));
            log.info(String.format("User model %1$s was built from the file %2$s", modelName, modelPath));
        } catch (InputFileReadingFailedException | JsonSyntaxException e) {
            throw new UserModelCreationFailedException(modelPath, e);
        }
    }

    public List<UserModelNode> getNodes() {
        return userModelNodes;
    }

    public int getModelSize() {
        return userModelNodes.size();
    }

    public String getModelName() {
        return modelName;
    }
}
