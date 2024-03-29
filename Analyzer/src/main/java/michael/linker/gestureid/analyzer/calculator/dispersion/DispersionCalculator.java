package michael.linker.gestureid.analyzer.calculator.dispersion;

import michael.linker.gestureid.analyzer.addons.csv.Csv;
import michael.linker.gestureid.analyzer.addons.file.exception.FileCreationFaultException;
import michael.linker.gestureid.analyzer.addons.file.output.IOutputFile;
import michael.linker.gestureid.analyzer.addons.file.output.exception.OutputFileWritingFailedException;
import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.SquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.SquareTablePointer;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.ValuedSquareTablePointer;
import michael.linker.gestureid.analyzer.calculator.CalculatorFailedException;
import michael.linker.gestureid.analyzer.calculator.CalculatorType;
import michael.linker.gestureid.analyzer.calculator.CalculatorUtils;
import michael.linker.gestureid.analyzer.calculator.ICalculator;
import michael.linker.gestureid.analyzer.calculator.addons.intersection.DispersionIntersector;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.DispersionTable;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.FullIntersectionDispersionTable;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.NormalIntersectionDispersionTable;
import michael.linker.gestureid.analyzer.config.CalculationsConfiguration;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DispersionCalculator implements ICalculator {
    private static final Logger log = LogManager.getLogger(DispersionCalculator.class);
    private static final CalculatorType CALCULATOR_TYPE = CalculatorType.INTERSECTION;
    private static final String DOT_OF_FLOATING_POINT_NUMBER = ".";
    private static final String FIRST_COLUMN_FIRST_ROW = "Model";
    private static final int TABLE_DEFAULT_VALUE = 0;

    @Override
    public void calculate(List<UserModel> userModels) throws CalculatorFailedException {
        log.info("The calculation of dispersions has begun.");
        try {
            for (Double dispersion : CalculationsConfiguration.getDispersions()) {
                DispersionTable intersectionTable = calculateIntersectionForDispersion(userModels, dispersion);
                writeResults(intersectionTable);
                DispersionTable fullIntersectionTable = calculateFullIntersectionForDispersion(userModels, dispersion);
                writeResults(fullIntersectionTable);
            }
            log.info("The calculation of dispersions has ended.");
        } catch (FileCreationFaultException | OutputFileWritingFailedException e) {
            throw new CalculatorFailedException(e);
        }
    }

    @Override
    public CalculatorType getType() {
        return CALCULATOR_TYPE;
    }

    /**
     * Calculate number of intersections between user models.
     *
     * @param userModels List of user models.
     * @param dispersion A floating-point number [0, 1).
     * @return Table with results and dispersion value.
     */
    public DispersionTable calculateIntersectionForDispersion(List<UserModel> userModels, Double dispersion) {
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
     * @param userModels List of user models.
     * @param dispersion A floating-point number [0, 1).
     * @return Table with results and dispersion value.
     */
    public DispersionTable calculateFullIntersectionForDispersion(List<UserModel> userModels, Double dispersion) {
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

    private void writeResults(DispersionTable resultTable)
            throws FileCreationFaultException, OutputFileWritingFailedException {
        IOutputFile outputFile = CalculatorUtils.createCalculationResultFile(CALCULATOR_TYPE,
                resultTable.getType().toString() +
                        resultTable.getDispersion().toString().replace(DOT_OF_FLOATING_POINT_NUMBER, ""));

        Deque<String> firstRow = new LinkedList<>(resultTable.getTable().getColumns());
        firstRow.offerFirst(FIRST_COLUMN_FIRST_ROW);
        outputFile.writeLine(Csv.format(firstRow));

        for (String row : resultTable.getTable().getRows()) {
            Deque<String> rowValues = new LinkedList<>();
            rowValues.add(row);
            for (Integer rowValue : resultTable.getTable().getValuesForRow(row)) {
                rowValues.add(rowValue.toString());
            }
            outputFile.writeLine(Csv.format(rowValues));
        }
        log.info(String.format("The calculation results are recorded to %1$s for a dispersion of %2$s",
                outputFile.getPath(), resultTable.getDispersion().toString()));
    }
}
