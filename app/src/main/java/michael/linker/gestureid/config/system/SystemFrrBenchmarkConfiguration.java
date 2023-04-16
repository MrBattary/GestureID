package michael.linker.gestureid.config.system;

import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.system.bean.SystemFrrBenchmarkBean;
import michael.linker.gestureid.data.system.benchmark.frr.FrrBenchmark;
import michael.linker.gestureid.data.system.benchmark.frr.IFrrBenchmark;

public class SystemFrrBenchmarkConfiguration implements IConfiguration {
    private static ConfigurationBean<IFrrBenchmark> frrBenchmarkBean = null;

    public static IFrrBenchmark getFrrBenchmark() {
        if (frrBenchmarkBean == null) {
            frrBenchmarkBean = new SystemFrrBenchmarkBean(new FrrBenchmark());
        }
        return (IFrrBenchmark) frrBenchmarkBean;
    }

    @Override
    public void configure() {
        if (SystemConfiguration.Build.Benchmark.shouldCalculateFrrBenchmark()) {
            frrBenchmarkBean = new SystemFrrBenchmarkBean(new FrrBenchmark());
        } else {
            frrBenchmarkBean = new SystemFrrBenchmarkBean(null);
        }
    }
}
