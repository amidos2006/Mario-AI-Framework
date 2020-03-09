package cz.cuni.gamedev.nail123.mariolevelgeneration.metrics

import engine.core.MarioLevelGenerator
import engine.core.MarioLevelModel
import engine.core.MarioTimer
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File

class MetricsEvaluator(val generator: MarioLevelGenerator, val metrics: List<AbstractMetric>) {
    /**
     * Generates a CSV file at given coordinates.
     */
    fun generateCSV(path: String, iterations: Int = 100) {
        val file = File(path)
        file.parentFile.mkdirs()
        val writer = file.bufferedWriter()

        val columnNames = arrayOf("run") + metrics.map { it.name }.toTypedArray()
        val csv = CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(*columnNames))

        for (i in 1 .. iterations) {
            print("Performing run $i... ")
            val level = generator.getGeneratedLevel(
                MarioLevelModel(150, 16),
                MarioTimer(5 * 60 * 60 * 1000)
            )

            val row = arrayOf(i.toString()) + metrics.map { it.getValue(level) }
            csv.printRecord(*row)
            println("done")
        }

        csv.close(true)
    }
}