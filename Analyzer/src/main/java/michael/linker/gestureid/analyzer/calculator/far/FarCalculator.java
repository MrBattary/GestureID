package michael.linker.gestureid.analyzer.calculator.far;

import michael.linker.gestureid.analyzer.addons.csv.Csv;
import michael.linker.gestureid.analyzer.addons.file.exception.FileCreationFaultException;
import michael.linker.gestureid.analyzer.addons.file.output.IOutputFile;
import michael.linker.gestureid.analyzer.addons.file.output.exception.OutputFileWritingFailedException;
import michael.linker.gestureid.analyzer.addons.table.TableValueNotFoundException;
import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.SquareTable;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.SquareTablePointer;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.ValuedSquareTablePointer;
import michael.linker.gestureid.analyzer.calculator.CalculatorFailedException;
import michael.linker.gestureid.analyzer.calculator.CalculatorType;
import michael.linker.gestureid.analyzer.calculator.CalculatorUtils;
import michael.linker.gestureid.analyzer.calculator.ICalculator;
import michael.linker.gestureid.analyzer.calculator.dispersion.DispersionCalculator;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.DispersionTable;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.FullIntersectionDispersionTable;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.NormalIntersectionDispersionTable;
import michael.linker.gestureid.analyzer.calculator.far.model.FarDispersionTable;
import michael.linker.gestureid.analyzer.calculator.far.model.FullIntersectionFarTable;
import michael.linker.gestureid.analyzer.calculator.far.model.NormalIntersectionFarTable;
import michael.linker.gestureid.analyzer.config.CalculationsConfiguration;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FarCalculator implements ICalculator {
    private static final Logger log = LogManager.getLogger(FarCalculator.class);
    private static final CalculatorType CALCULATOR_TYPE = CalculatorType.FAR;
    private static final double TABLE_DEFAULT_VALUE = 0.0;
    private static final String DOT_OF_FLOATING_POINT_NUMBER = ".";
    private static final String FIRST_COLUMN_FIRST_ROW = "Model";

    @Override
    public void calculate(List<UserModel> userModels) throws CalculatorFailedException {
        log.info("The calculation of FAR has begun.");

        DispersionCalculator dispersionCalculator = new DispersionCalculator();

        try {
            for (Double dispersion : CalculationsConfiguration.getFarDispersions()) {
                DispersionTable intersectionTable =
                        dispersionCalculator.calculateIntersectionForDispersion(userModels, dispersion);
                FarDispersionTable intersectionFarTable =
                        calculateFarForNormalIntersection((NormalIntersectionDispersionTable) intersectionTable);
                writeResults(intersectionFarTable);
                DispersionTable fullIntersectionTable =
                        dispersionCalculator.calculateFullIntersectionForDispersion(userModels, dispersion);
                FarDispersionTable fullIntersectionFarTable =
                        calculateFarForFullIntersection((FullIntersectionDispersionTable) fullIntersectionTable);
                writeResults(fullIntersectionFarTable);
            }
            log.info("The calculation of FAR has ended.");
        } catch (RuntimeException e) {
            throw new CalculatorFailedException(e);
        }
    }

    /**
     * Calculates FAR for normal intersection by provided formula:
     * FAR = Number of matches / The number of compared nodes.
     *
     * @param dispersionTable Normal intersection dispersion table.
     * @return Table with FAR results and dispersion value.
     */
    private FarDispersionTable calculateFarForNormalIntersection(NormalIntersectionDispersionTable dispersionTable)
            throws TableValueNotFoundException {
        log.info(String.format("FAR calculation are performed for the %1$s intersection with the dispersion %2$s",
                dispersionTable.getType().toString(), dispersionTable.getDispersion().toString()));

        ISquareTable<String, Double> farTable = new SquareTable<>(
                dispersionTable.getTable().getRows(),
                TABLE_DEFAULT_VALUE);

        for (String row : farTable.getRows()) {
            for (String column : farTable.getColumns()) {
                SquareTablePointer<String> pointer = new SquareTablePointer<>(row, column);
                int numberOfComparableModels = dispersionTable.getTable().getValue(pointer);
                // To prevent comparison with the same model or dividing by 0
                if (Objects.equals(row, column) || numberOfComparableModels == 0) {
                    farTable.setValue(new ValuedSquareTablePointer<>(pointer, TABLE_DEFAULT_VALUE));
                } else {
                    int numberOfComparedModels = dispersionTable
                            .getTable()
                            .getValue(new SquareTablePointer<>(column, column));
                    double farValue = (double) numberOfComparableModels / numberOfComparedModels;
                    farTable.setValue(new ValuedSquareTablePointer<>(pointer, farValue));
                }
            }
        }
        return new NormalIntersectionFarTable(dispersionTable.getDispersion(), farTable);
    }

    /**
     * Calculates FAR for full intersection by provided formula:
     * FAR = Number of matches / The number of comparable nodes ^ 2
     *
     * @param dispersionTable Full intersection dispersion table.
     * @return Table with FAR results and dispersion value.
     */
    private FarDispersionTable calculateFarForFullIntersection(
            FullIntersectionDispersionTable dispersionTable) throws TableValueNotFoundException {
        log.info(String.format("FAR calculation are performed for the %1$s intersection with the dispersion %2$s",
                dispersionTable.getType().toString(), dispersionTable.getDispersion().toString()));

        ISquareTable<String, Double> farTable = new SquareTable<>(
                dispersionTable.getTable().getRows(),
                TABLE_DEFAULT_VALUE);

        for (String row : farTable.getRows()) {
            int numberOfComparedModels = dispersionTable
                    .getTable()
                    .getValue(new SquareTablePointer<>(row, row));
            for (String column : farTable.getColumns()) {
                SquareTablePointer<String> pointer = new SquareTablePointer<>(row, column);
                int numberOfComparableModels = dispersionTable.getTable().getValue(pointer);
                // To prevent comparison with the same model or dividing by 0
                if (Objects.equals(row, column) || numberOfComparableModels == 0) {
                    farTable.setValue(new ValuedSquareTablePointer<>(pointer, TABLE_DEFAULT_VALUE));
                } else {
                    double farValue = (double) numberOfComparableModels /
                            (numberOfComparedModels * numberOfComparedModels);
                    farTable.setValue(new ValuedSquareTablePointer<>(pointer, farValue));
                }
            }
        }
        return new FullIntersectionFarTable(dispersionTable.getDispersion(), farTable);
    }

    private void writeResults(FarDispersionTable resultTable)
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
            for (Double rowValue : resultTable.getTable().getValuesForRow(row)) {
                rowValues.add(String.format("%.8f", rowValue));
            }
            outputFile.writeLine(Csv.format(rowValues));
        }
        log.info(String.format("The FAR calculation results are recorded to %1$s for a dispersion of %2$s",
                outputFile.getPath(), resultTable.getDispersion().toString()));
    }

    @Override
    public CalculatorType getType() {
        return CALCULATOR_TYPE;
    }
}
