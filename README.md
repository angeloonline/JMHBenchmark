#Java micro benchmarks examples with JMH toolkit

# How to run this project

```shell script
mvn clean compile
```
after that you can run each of the test as following:

for Stream (for loop) benchmark:

```shell script
java -jar target\JMHBenchmarks.jar MyBenchmarkStream
```
for String benchmark:

```shell script
java -jar target\JMHBenchmarks.jar MyBenchmarkString
```
for Collection Initialization benchmark:

```shell script
java -jar target\JMHBenchmarks.jar MyBenchmarkCollectionsInitialization
```