package day10

import readInput

val pointMap = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137
)

val pointMap2 = mapOf(
    ')' to 1,
    ']' to 2,
    '}' to 3,
    '>' to 4
)

data class CharType(
    val matchingChar: Char,
    val type: String,
)

val charTypeMap = mapOf(
    '(' to CharType(')', "open"),
    '[' to CharType(']', "open"),
    '{' to CharType('}', "open"),
    '<' to CharType('>', "open"),
    ')' to CharType('(', "close"),
    ']' to CharType('[', "close"),
    '}' to CharType('{', "close"),
    '>' to CharType('<', "close"),
)

fun main() {
    fun part1(input: List<String>): Int {
        val illegalCharCounts = mutableMapOf<Char, Int>()

        input.forEach {
            val characters = it.toCharArray()
            val openingChars = mutableListOf<Char>()
            for (c in characters) {
                val char = charTypeMap[c]
                if (openingChars.isEmpty() || char?.type == "open") {
                    openingChars.add(c)
                } else if (char?.type == "close" && openingChars.last() == char.matchingChar) {
                    openingChars.removeLast()
                } else if (char?.type == "close" && openingChars.last() != char.matchingChar) {
                    val occurrences = illegalCharCounts[c]
                    if (occurrences != null) {
                        illegalCharCounts.replace(c, occurrences + 1)
                    } else {
                        illegalCharCounts.put(c, 1)
                    }
                    break
                }
            }
        }

        return illegalCharCounts.map { (k, v) ->
            pointMap[k]!! * v
        }.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val finalScores = mutableListOf<Int>()

        for (item in input) {
            val closingChars = mutableListOf<Char>()
            var corruptedLine = false
            var totalScore = 0
            val characters = item.toCharArray()
            val openingChars = mutableListOf<Char>()
            for (c in characters) {
                val char = charTypeMap[c]
                if (openingChars.isEmpty() || char?.type == "open") {
                    openingChars.add(c)
                } else if (char?.type == "close" && openingChars.last() == char.matchingChar) {
                    openingChars.removeLast()
                } else if (char?.type == "close" && openingChars.last() != char.matchingChar) {
                    corruptedLine = true
                    break
                }
            }

            if (corruptedLine) continue

            if (openingChars.isNotEmpty()) {
                val openingCharsReversed = openingChars.reversed()
                openingCharsReversed.forEach {
                    val char = charTypeMap[it]
                    closingChars.add(char?.matchingChar!!)
                }
            }

            closingChars.forEach {
                totalScore *= 5
                val points = pointMap2[it]
                totalScore += points!!
            }
            finalScores.add(totalScore)
        }

        val middleIndex = finalScores.size / 2

        return finalScores.sorted()[middleIndex]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test", 10)
    //println("Day10_part1_test_result: " + part1(testInput))
    //check(part1(testInput) == 26397)

    // test if implementation of part two meets criteria from the description
    println("Day10_part2_test_result: " + part2(testInput))
    check(part2(testInput) == 288957)

    val input = readInput("Day10", 10)
    //println("Day10_part1_result: " + part1(input))
    println("Day10_part2_result: " + part2(input))
}
