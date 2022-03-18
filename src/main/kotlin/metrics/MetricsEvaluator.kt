package metrics

import engine.core.MarioLevelGenerator
import engine.core.MarioLevelModel
import engine.core.MarioTimer
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File

class MetricsEvaluator(val generator: MarioLevelGenerator, val metrics: List<AbstractMetric>) {
    val maxEvaluationAttempts = 10

    /**
     * Generates a CSV file at given coordinates.
     */
    fun generateCSV(path: String, iterations: Int = 100) {
        val file = File(path)
        file.parentFile.mkdirs()
        val writer = file.bufferedWriter()

        val columnNames = arrayOf("run") + metrics.map { it.name }.toTypedArray()
        val csv = CSVPrinter(writer, CSVFormat.DEFAULT)
        csv.printRecord(*columnNames)

        for (i in 1 .. iterations) {
            print("Performing run $i... ")

            val row = arrayOf(i.toString()) + evaluateWithRetries()

            csv.printRecord(*row)
            println("done")
        }

        csv.close(true)
    }

    private fun evaluateWithRetries(): List<String> {
        var lastException: Throwable? = null
        for (attempt in 1 .. maxEvaluationAttempts) {
            try {
                return performEvaluation()
            } catch (e: Throwable) {
                lastException = e
                println(e.message)
                print("Retrying, attempt #$attempt ...")
            }
        }
        throw lastException!!
    }

    private fun performEvaluation(): List<String> {
        val level = generator.getGeneratedLevel(
            MarioLevelModel(150, 16),
            MarioTimer(5 * 60 * 60 * 1000)
        )

        return metrics.map { it.getValue(level) }
    }
}