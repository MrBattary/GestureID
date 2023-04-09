package michael.linker.gestureid.analyzer.calculator;

import michael.linker.gestureid.analyzer.user.model.UserModel;

import java.util.List;

public interface ICalculator {
    /**
     * Run specific calculations for the provided custom models.
     *
     * @param userModels List of users models.
     * @throws CalculatorFailedException If the calculation is not performed due to an error.
     */
    void calculate(List<UserModel> userModels) throws CalculatorFailedException;

    CalculatorType getType();
}
