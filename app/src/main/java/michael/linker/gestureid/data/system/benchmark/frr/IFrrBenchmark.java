package michael.linker.gestureid.data.system.benchmark.frr;

import java.util.List;

public interface IFrrBenchmark {
    void recordAccepted();

    void recordRejected();

    List<Double> getResults();
}
