import levelGenerators.sample.GapLevelGenerator
import metrics.AbstractMetric
import metrics.JumpMetric
import metrics.LinearityMetric
import metrics.MetricsEvaluator

// Runs an evaluation of metrics on a given generator
fun main() {
    val levelGenerator = GapLevelGenerator()
    val metrics = listOf<AbstractMetric>(
        LinearityMetric(),
        JumpMetric()
    )

    val metricsEvaluator = MetricsEvaluator(levelGenerator, metrics)

    metricsEvaluator.generateCSV("src/main/python/data/metrics.csv", 100)
}