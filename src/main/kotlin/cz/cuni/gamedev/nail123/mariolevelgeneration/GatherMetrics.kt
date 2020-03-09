package cz.cuni.gamedev.nail123.mariolevelgeneration

import cz.cuni.gamedev.nail123.mariolevelgeneration.levelGenerators.sample.GapLevelGenerator
import cz.cuni.gamedev.nail123.mariolevelgeneration.metrics.AbstractMetric
import cz.cuni.gamedev.nail123.mariolevelgeneration.metrics.LinearityMetric
import cz.cuni.gamedev.nail123.mariolevelgeneration.metrics.MetricsEvaluator
import engine.core.*

// Runs an evaluation of metrics on a given generator
fun main() {
    val levelGenerator = levelGenerators.notch.LevelGenerator()
    val metrics = listOf<AbstractMetric>(
        LinearityMetric()
    )

    val metricsEvaluator = MetricsEvaluator(levelGenerator, metrics)

    metricsEvaluator.generateCSV("src/main/python/data/metrics.csv")
}