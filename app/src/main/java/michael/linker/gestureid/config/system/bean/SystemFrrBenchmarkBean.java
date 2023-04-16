package michael.linker.gestureid.config.system.bean;

import java.util.ArrayList;
import java.util.List;

import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.system.benchmark.frr.IFrrBenchmark;

public class SystemFrrBenchmarkBean
        extends ConfigurationBean<IFrrBenchmark>
        implements IFrrBenchmark {

    public SystemFrrBenchmarkBean(IFrrBenchmark defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public IFrrBenchmark implement(IFrrBenchmark newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public void recordAccepted() {
        if (SystemConfiguration.Build.Benchmark.shouldCalculateFrrBenchmark()) {
            getImplementation().recordAccepted();
        }
    }

    @Override
    public void recordRejected() {
        if (SystemConfiguration.Build.Benchmark.shouldCalculateFrrBenchmark()) {
            getImplementation().recordRejected();
        }
    }

    @Override
    public List<Double> getResults() {
        if (SystemConfiguration.Build.Benchmark.shouldCalculateFrrBenchmark()) {
            return getImplementation().getResults();
        }
        return new ArrayList<>();
    }
}
