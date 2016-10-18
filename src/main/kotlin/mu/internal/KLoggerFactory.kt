package mu.internal

import mu.KLoggable
import mu.KLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.spi.LocationAwareLogger

/**
 * factory methods to obtain a [Logger]
 */
@Suppress("NOTHING_TO_INLINE")
internal object KLoggerFactory {

    /**
     * get logger for the class
     */
    inline internal fun logger(loggable: KLoggable): KLogger =
            wrapJLogger(jLogger(KLoggerNameResolver.name(loggable.javaClass)))

    /**
     * get logger by explicit name
     */
    inline internal fun logger(name: String): KLogger = wrapJLogger(jLogger(name))

    /**
     * get a java logger by name
     */
    inline internal fun jLogger(name: String): Logger = LoggerFactory.getLogger(name)

    /**
     * wrap java logger based on location awareness
     */
    inline private fun wrapJLogger(jLogger: Logger): KLogger =
            if (jLogger is LocationAwareLogger)
                LocationAwareKLogger(jLogger)
            else
                LocationIgnorantKLogger(jLogger)


}

