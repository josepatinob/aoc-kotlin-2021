package day5

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var seaFloor = Array(10) { Array(10) { "." } }

        input.forEach {
            val start = it.substringBefore("-").trim()
            val end = it.substringAfter(">").trim()
            val x_point1 = start.substringBefore(",").toInt()
            val y_point1 = start.substringAfter(",").toInt()
//            seaFloor[x_point1][y_point1] =
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test", 5)
    println("Day05_part1_test_result: " + part1(testInput))
    //check(part1(testInput) == 7)

    // test if implementation of part two meets criteria from the description
    // println("Day01_part2_test_result: " + part2(testInput))
    // check(part2(testInput) == 5)

    //val input = readInput("Day05",5)
    //println("Day05_part1_result: " + part1(input))
    //println("Day05_part2_result: " + part2(input))
}
