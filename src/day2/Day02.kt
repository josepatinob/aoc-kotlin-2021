package day2

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0

        input.forEach {
            val action = it.substringBefore(" ")
            val units = it.substringAfter(" ").toInt()

            when (action) {
                "forward" -> horizontalPosition += units
                "down" -> depth += units
                "up" -> depth -= units
                else -> println("something might be wrong")
            }
        }

        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        input.forEach {
            val action = it.substringBefore(" ")
            val units = it.substringAfter(" ").toInt()

            when (action) {
                "forward" -> {
                    horizontalPosition += units
                    depth += aim * units
                }
                "down" -> aim += units
                "up" -> aim -= units
                else -> println("something might be wrong")
            }
        }

        return horizontalPosition * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test", 2)
    println("Day02_part1_test_result: " + part1(testInput))
    check(part1(testInput) == 150)

    // test if implementation of part two meets criteria from the description
    println("Day02_part2_test_result: " + part2(testInput))
    check(part2(testInput) == 900)

    val input = readInput("Day02", 2)
    println("Day02_part1_result: " + part1(input))
    println("Day02_part2_result: " + part2(input))
}