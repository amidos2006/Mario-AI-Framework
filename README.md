A fork of the Mario AI framework (marioai.org) for use in NAIL123 - Procedural Content Generation in Computer games course at
Charles University. Notable additions:

- Using Gradle
- Added functionality to plot metrics of level generators

There are two Gradle executable targets:

- singleRun (runs RunGame.kt)
- generateCSV (runs generateCSV.kt)

## Task 1 - create a Generator

Generators are placed in src/main/(kotlin|java)/levelGenerators.

Copy any one of them and create your own.

Modify src/main/kotlin/RunGame.kt to use your generator and try it out.

## Task 2 - create a Metric

Example (very simple) Metrics are in:

- Kotlin - src/main/kotlin/metrics/JumpMetric
- Java - src/main/java/metrics/JumpMetricJava

Again, copy one of them and create your own!

## Task 3 - create plots

Add your metric to src/main/kotlin/GatherMetrics.kt, and run it.

This will create a CSV in src/main/python/data/metrics.csv.

To plot from the CSV, run python3 make_graphs in the src/main/python directory.

They will be put in src/main/python/out.