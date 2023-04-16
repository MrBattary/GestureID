package michael.linker.gestureid.data.system.benchmark.frr;

import java.util.LinkedList;
import java.util.List;

public class FrrBenchmark implements IFrrBenchmark {
    private final List<Double> ratioList;
    private int accepted, rejected;

    public FrrBenchmark() {
        this.accepted = 0;
        this.rejected = 0;
        this.ratioList = new LinkedList<>();
    }

    @Override
    public void recordAccepted() {
        accepted += 1;
        addNewValueToList();
    }

    @Override
    public void recordRejected() {
        rejected += 1;
        addNewValueToList();
    }

    private void addNewValueToList() {
        double frr = (double) rejected / (rejected + accepted);
        ratioList.add(frr);
    }

    @Override
    public List<Double> getResults() {
        return ratioList;
    }
}
