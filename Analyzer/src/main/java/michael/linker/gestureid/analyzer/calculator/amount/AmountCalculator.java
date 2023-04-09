package michael.linker.gestureid.analyzer.calculator.amount;

import michael.linker.gestureid.analyzer.addons.csv.Csv;
import michael.linker.gestureid.analyzer.addons.file.output.IOutputFile;
import michael.linker.gestureid.analyzer.calculator.CalculatorFailedException;
import michael.linker.gestureid.analyzer.calculator.CalculatorType;
import michael.linker.gestureid.analyzer.calculator.CalculatorUtils;
import michael.linker.gestureid.analyzer.calculator.ICalculator;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class AmountCalculator implements ICalculator {
    private static final Logger log = LogManager.getLogger(AmountCalculator.class);
    private static final CalculatorType CALCULATOR_TYPE = CalculatorType.AMOUNT;

    @Override
    public void calculate(List<UserModel> userModels) throws CalculatorFailedException {
        log.info("The calculation of amount has begun.");

        IOutputFile outputFile = CalculatorUtils.createCalculationResultFile(CALCULATOR_TYPE, "");

        for (UserModel userModel : userModels) {
            outputFile.writeLine(
                    Csv.format(Arrays.asList(userModel.getModelName(),
                            String.valueOf(userModel.getModelSize())))
            );
        }
        log.info(String.format("The amount calculation results are recorded to the %1$s file", outputFile.getPath()));
        log.info("The calculation of amount has ended.");
    }

    @Override
    public CalculatorType getType() {
        return CALCULATOR_TYPE;
    }
}
