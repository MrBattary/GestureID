package michael.linker.gestureid.analyzer.user.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import michael.linker.gestureid.analyzer.file.input.IInputFile;
import michael.linker.gestureid.analyzer.file.input.InputFile;
import michael.linker.gestureid.analyzer.file.input.exception.InputFileReadingFailedException;
import michael.linker.gestureid.analyzer.user.model.exception.UserModelCreationFailedException;
import michael.linker.gestureid.analyzer.user.model.node.UserModelNode;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
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
        } catch (InputFileReadingFailedException | JsonSyntaxException e) {
            throw new UserModelCreationFailedException(modelPath, e);
        }
    }

    public List<UserModelNode> getUserModelNodes() {
        return userModelNodes;
    }
}
