package michael.linker.gestureid.analyzer.calculator.dispersion;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.SquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.SquareTablePointer;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.ValuedSquareTablePointer;
import michael.linker.gestureid.analyzer.calculator.CalculatorFailedException;
import michael.linker.gestureid.analyzer.calculator.CalculatorType;
import michael.linker.gestureid.analyzer.calculator.ICalculator;
import michael.linker.gestureid.analyzer.calculator.dispersion.result.DispersionCalculationTable;
import michael.linker.gestureid.analyzer.config.CalculationsConfiguration;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class DispersionCalculator implements ICalculator {
    private static final Logger log = LogManager.getLogger(DispersionCalculator.class);
    private static final CalculatorType CALCULATOR_TYPE = CalculatorType.INTERSECTION;
    private static final int TABLE_DEFAULT_VALUE = 0;
    private List<UserModel> userModels;

    @Override
    public void calculate(List<UserModel> userModels) throws CalculatorFailedException {
        log.info("The calculation of dispersions has begun.");
        this.userModels = userModels;

        for (Double dispersion : CalculationsConfiguration.getDispersions()) {
            DispersionCalculationTable intersectionTable = calculateIntersectionForDispersion(dispersion);
            writeResults(intersectionTable);
            DispersionCalculationTable allIntersectionTable = calculateAllIntersectionForDispersion(dispersion);
            writeResults(allIntersectionTable);
        }
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
    private DispersionCalculationTable calculateIntersectionForDispersion(Double dispersion) {
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
        return new DispersionCalculationTable(dispersion, intersectionTable);
    }

    /**
     * Calculate number of all intersections between user models.
     *
     * @param dispersion A floating-point number [0, 1).
     * @return Table with results and dispersion value.
     */
    private DispersionCalculationTable calculateAllIntersectionForDispersion(Double dispersion) {
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
                                new ValuedSquareTablePointer<>(pointer,
                                        comparedModel.getModelSize() * comparedModel.getModelSize())
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
        return new DispersionCalculationTable(dispersion, intersectionTable);
    }

    private void writeResults(DispersionCalculationTable table) throws RuntimeException {
        // TODO: Write to file
        for (String row : table.table().getRows()) {
            StringBuilder builder = new StringBuilder();
            for (String column : table.table().getColumns()) {
                builder.append(table.table().getValue(new SquareTablePointer<>(row, column))).append(" ");
            }
            log.info(row + " : " + builder);
        }
        log.info("The calculation results are recorded for a dispersion of " + table.dispersion().toString());
    }
}
