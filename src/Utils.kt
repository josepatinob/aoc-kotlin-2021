import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, day: Int) = File("src/day$day", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Provides elapsed time in human-readable format
 */
fun getElapsedTime(startTime: Long, endTime: Long): String {
    val duration = Duration.ofMillis(endTime - startTime)
    return String.format("%ds %dms", duration.toSecondsPart(), duration.toMillisPart())
}
