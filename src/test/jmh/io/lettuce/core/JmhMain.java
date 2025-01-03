package io.lettuce.core;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * Manual JMH Test Launcher.
 *
 * @author Mark Paluch
 */
public class JmhMain {

    public static void main(String... args) throws RunnerException {
        runRedisClientBenchmark();
    }

    private static void runBenchmarks() throws RunnerException {
        new Runner(prepareOptions().mode(Mode.AverageTime).timeUnit(TimeUnit.NANOSECONDS).build()).run();
    }

    private static void runRedisClientBenchmark() throws RunnerException {

        new Runner(prepareOptions().mode(Mode.AverageTime).timeUnit(TimeUnit.NANOSECONDS).include(".RedisClientBenchmark.*")
                .build()).run();
    }

    private static ChainedOptionsBuilder prepareOptions() {
        return new OptionsBuilder().forks(1).warmupIterations(5).threads(1).measurementIterations(5)
                .timeout(TimeValue.seconds(2)).output("benchmark.log");
    }
}
