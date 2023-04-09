package michael.linker.gestureid.analyzer.calculator;

import michael.linker.gestureid.analyzer.addons.csv.Csv;
import michael.linker.gestureid.analyzer.addons.file.exception.FileCreationFaultException;
import michael.linker.gestureid.analyzer.addons.file.output.IOutputFile;
import michael.linker.gestureid.analyzer.addons.file.output.OutputFile;
import michael.linker.gestureid.analyzer.addons.file.utils.FileUtils;
import michael.linker.gestureid.analyzer.config.FileConfiguration;

import java.nio.file.Path;

public class CalculatorUtils {
    public static IOutputFile createCalculationResultFile(CalculatorType calculatorType, String additionalName)
            throws FileCreationFaultException {
        String fileName = calculatorType.toString() +
                additionalName +
                FileConfiguration.getResultsDestinationFileExtension();
        Path filePath = FileUtils.createFile(FileConfiguration.getResultsDestinationDirectoryPath(), fileName);
        return new OutputFile(filePath);
    }
}
