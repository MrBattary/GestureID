package michael.linker.gestureid.analyzer.calculator.dispersion;

import michael.linker.gestureid.analyzer.addons.csv.CsvFormatter;
import michael.linker.gestureid.analyzer.addons.file.output.IOutputFile;
import michael.linker.gestureid.analyzer.addons.file.output.OutputFile;
import michael.linker.gestureid.analyzer.addons.file.utils.FileUtils;
import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.SquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.SquareTablePointer;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.ValuedSquareTablePointer;
import michael.linker.gestureid.analyzer.calculator.CalculatorFailedException;
import michael.linker.gestureid.analyzer.calculator.CalculatorType;
import michael.linker.gestureid.analyzer.calculator.ICalculator;
import michael.linker.gestureid.analyzer.calculator.dispersion.result.DispersionTable;
import michael.linker.gestureid.analyzer.calculator.dispersion.result.FullIntersectionDispersionTable;
import michael.linker.gestureid.analyzer.calculator.dispersion.result.NormalIntersectionDispersionTable;
import michael.linker.gestureid.analyzer.config.CalculationsConfiguration;
import michael.linker.gestureid.analyzer.config.FileConfiguration;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DispersionCalculator implements ICalculator {
    private static final Logger log = LogManager.getLogger(DispersionCalculator.class);
    private static final CalculatorType CALCULATOR_TYPE = CalculatorType.INTERSECTION;
    private static final String FIRST_COLUMN_FIRST_ROW = "Model";
    private static final int TABLE_DEFAULT_VALUE = 0;
    private List<UserModel> userModels;

    @Override
    public void calculate(List<UserModel> userModels) throws CalculatorFailedException {
        log.info("The calculation of dispersions has begun.");
        this.userModels = userModels;

        for (Double dispersion : CalculationsConfiguration.getDispersions()) {
            DispersionTable intersectionTable = calculateIntersectionForDispersion(dispersion);
            writeResults(intersectionTable);
            DispersionTable allIntersectionTable = calculateFullIntersectionForDispersion(dispersion);
            writeResults(allIntersectionTable);
        }
        log.info("The calculation of dispersions has ended.");
    }

    @Override
    public CalculatorType getType() {
        return CALCULATOR_TYPE;
    }

    /**
     * Calculate number of intersections between user models.
     *
     * @param dispersion A floating-point number [0, 1).
     * @return Table with results and dispersion value.
     */
    private DispersionTable calculateIntersectionForDispersion(Double dispersion) {
        log.info("Models intersection are performed for the dispersion " + dispersion.toString());
        ISquareTable<String, Integer> intersectionTable = new SquareTable<>(
                userModels
                        .stream()
                        .map(UserModel::getModelName)
                        .collect(Collectors.toSet()),
                TABLE_DEFAULT_VALUE);
        DispersionIntersector intersector = new DispersionIntersector(dispersion);
        for (UserModel comparedModel : userModels) {
            for (UserModel comparableModel : userModels) {
                final String comparedModelName = comparedModel.getModelName();
                final String comparableModelName = comparableModel.getModelName();

                SquareTablePointer<String> pointer = new SquareTablePointer<>(comparedModelName, comparableModelName);

                if (intersectionTable.getValue(pointer) == TABLE_DEFAULT_VALUE) {
                    // If the model intersects with itself
                    if (comparedModelName.equals(comparableModelName)) {
                        intersectionTable.setValue(
                                new ValuedSquareTablePointer<>(pointer, comparedModel.getModelSize())
                        );
                    } else {
                        int numberOfIntersections = intersector.intersectModels(comparedModel, comparableModel);
                        ValuedSquareTablePointer<String, Integer> valuedPointer =
                                new ValuedSquareTablePointer<>(pointer, numberOfIntersections);

                        intersectionTable.setValue(valuedPointer);
                        intersectionTable.setValue(valuedPointer.getReversed());
                    }
                }

            }
        }
        return new NormalIntersectionDispersionTable(dispersion, intersectionTable);
    }

    /**
     * Calculate number of all intersections between user models.
     *
     * @param dispersion A floating-point number [0, 1).
     * @return Table with results and dispersion value.
     */
    private DispersionTable calculateFullIntersectionForDispersion(Double dispersion) {
        log.info("All models intersection are performed for the dispersion " + dispersion.toString());
        ISquareTable<String, Integer> intersectionTable = new SquareTable<>(
                userModels
                        .stream()
                        .map(UserModel::getModelName)
                        .collect(Collectors.toSet()),
                TABLE_DEFAULT_VALUE);
        DispersionIntersector intersector = new DispersionIntersector(dispersion);
        for (UserModel comparedModel : userModels) {
            for (UserModel comparableModel : userModels) {
                final String comparedModelName = comparedModel.getModelName();
                final String comparableModelName = comparableModel.getModelName();

                SquareTablePointer<String> pointer = new SquareTablePointer<>(comparedModelName, comparableModelName);

                if (intersectionTable.getValue(pointer) == TABLE_DEFAULT_VALUE) {
                    // If the model intersects with itself
                    if (comparedModelName.equals(comparableModelName)) {
                        intersectionTable.setValue(
                                new ValuedSquareTablePointer<>(pointer, comparedModel.getModelSize())
                        );
                    } else {
                        int numberOfIntersections = intersector.intersectAllModels(comparedModel, comparableModel);
                        ValuedSquareTablePointer<String, Integer> valuedPointer =
                                new ValuedSquareTablePointer<>(pointer, numberOfIntersections);

                        intersectionTable.setValue(valuedPointer);
                        intersectionTable.setValue(valuedPointer.getReversed());
                    }
                }

            }
        }
        return new FullIntersectionDispersionTable(dispersion, intersectionTable);
    }

    private void writeResults(DispersionTable resultTable) throws RuntimeException {
        String fileName = CALCULATOR_TYPE + resultTable.getType().toString() + resultTable.getDispersion().toString();
        Path filePath = FileUtils.createFile(FileConfiguration.getResultsDestinationDirectoryPath(), fileName);
        IOutputFile outputFile = new OutputFile(filePath);

        Deque<String> firstRow = new LinkedList<>(resultTable.getTable().getColumns());
        firstRow.offerFirst(FIRST_COLUMN_FIRST_ROW);

        outputFile.writeLine(CsvFormatter.format(firstRow));
        // TODO: Write to file
        for (String row : resultTable.getTable().getRows()) {
            Deque<String> rowValues = new LinkedList<>();
            rowValues.add(row);
            for (Integer rowValue : resultTable.getTable().getValuesForRow(row)) {
                rowValues.add(rowValue.toString());
            }
            outputFile.writeLine(CsvFormatter.format(rowValues));
        }
        log.info(String.format("The calculation results are recorded to %1$s for a dispersion of %2$s",
                filePath, resultTable.getDispersion().toString()));
    }
}
