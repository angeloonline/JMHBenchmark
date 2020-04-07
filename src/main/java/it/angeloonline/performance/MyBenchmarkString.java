package it.angeloonline.performance;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
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
public class MyBenchmarkString {
	public int iterations = 1000;
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(MyBenchmarkString.class.getSimpleName())
				.build();
		new Runner(opt).run();
		}

	@Benchmark
	public String testHelloWorldBuilder() {
	  StringBuilder builder = new StringBuilder();
	  for(int i = 0; i < iterations; i++)
		  builder.append(i);
	  return builder.toString();
	}
	
	@Benchmark
	public String testHelloWorldBuffer() {
	  StringBuffer buffer = new StringBuffer();
	  for(int i = 0; i < iterations; i++)
		  buffer.append(i);
	  return buffer.toString();
	}
	
	@Benchmark
	public String testHelloWorldPlainString() {
	  String buffer = new String();
	  for(int i = 0; i < iterations; i++)
		  buffer+=i;
	  return buffer.toString();
	}

}
