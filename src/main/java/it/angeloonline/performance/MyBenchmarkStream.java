package it.angeloonline.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 2)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@State(Scope.Benchmark)
public class MyBenchmarkStream {
	public int iterations = 100000;
	public List <Integer> myList;
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(MyBenchmarkStream.class.getSimpleName())
				.build();
		new Runner(opt).run();
		}

	@Setup
    public void prepare(){
		myList = new ArrayList<Integer>();
		for (int i = 0; i < iterations; i++)
			myList.add(i);
    }
	
	@Benchmark
	public int testForLoop() {
		int result = 0;
		for (int i: myList) {
			if (i % 2 == 0)
				result += i;
		}
		return result;
	}
	
	@Benchmark
	public int testStream() {
	  return myList.stream().filter(value -> value % 2 == 0).mapToInt(Integer::intValue).sum();
	}
	
	@Benchmark
	public int testParallelStream() {
	  return myList.parallelStream().filter(value -> value % 2 == 0).mapToInt(Integer::intValue).sum();
	}

}
