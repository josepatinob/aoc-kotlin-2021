package day1

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var counter = 0

        input.forEachIndexed { index, item ->
            // skips first item
            if (index == 0) {
                return@forEachIndexed
            }
            if (input[index - 1].toInt() < item.toInt()) {
                counter++
            }
        }

        return counter
    }

    fun part2(input: List<String>): Int {
        val part2Input = mutableListOf<String>()

        input.forEachIndexed { index, item ->
            if (index < input.size - 2) {
                val sum = item.toInt() + input[index + 1].toInt() + input[index + 2].toInt()
                part2Input.add(sum.toString())
            }
        }

        return part1(part2Input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test", 1)
    println("Day01_part1_test_result: " + part1(testInput))
    check(part1(testInput) == 7)

    // test if implementation of part two meets criteria from the description
    println("Day01_part2_test_result: " + part2(testInput))
    check(part2(testInput) == 5)

    val input = readInput("Day01",1)
    println("Day01_part1_result: " + part1(input))
    println("Day01_part2_result: " + part2(input))
}
