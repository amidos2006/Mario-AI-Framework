package metrics

import engine.core.MarioLevel
import engine.core.MarioResult

abstract class AbstractMetric {
    /**
     * Name of this metric - will be used and further shown in graphs.
     */
    abstract val name: String

    /**
     * Evaluates a given level by this metric.
     *
     * @param level Mario level in String format.
     * @return String representation of this metric - will be further parsed by a python script.
     */
    abstract fun getValue(level: String): String
}