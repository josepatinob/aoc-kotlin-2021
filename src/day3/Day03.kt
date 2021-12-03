package day3

import readInput

fun String.binaryToInt() = toInt(2)

fun main() {
    fun findBinaryNumber(timesToRepeat: Int, find: String, input: List<String>): String {
        var gammaRate = ""
        var epsilonRate = ""

        for (i in 0 until timesToRepeat) {
            var zeroCount = 0
            var oneCount = 0

            input.forEach {
                if (it[i] == '0') zeroCount++ else oneCount++
            }

            val mostCommon = if (zeroCount > oneCount) "0" else "1"
            val leastCommon = if (zeroCount < oneCount) "0" else "1"

            if (find == "gamma") {
                gammaRate += mostCommon
            } else {
                epsilonRate += leastCommon
            }
        }

        return if (find == "gamma") {
            gammaRate
        } else {
            epsilonRate
        }
    }

    fun part1(input: List<String>): Int {
        val binaryNumLength = input[0].length
        val gamma = findBinaryNumber(binaryNumLength, "gamma", input)
        val epsilon = findBinaryNumber(binaryNumLength, "epsilon", input)
        return gamma.binaryToInt() * epsilon.binaryToInt()
    }

    fun findRatingValue(timesToRepeat: Int, find: String, input: List<String>): String {
        var filteredList = input

        for (i in 0 until timesToRepeat) {
            if (filteredList.size == 1) {
                return filteredList[0]
            }

            var zeroCount = 0
            var oneCount = 0

            filteredList.forEach {
                if (it[i] == '0') zeroCount++ else oneCount++
            }

            val mostCommon = if (zeroCount > oneCount) "0" else "1"
            val leastCommon = if (zeroCount < oneCount) "0" else "1"

            if (find == "o2") {
                if (filteredList.size == 2) {
                    filteredList = filteredList.filter { it[i].toString() == "1" }
                } else {
                    filteredList = filteredList.filter { it[i].toString() == mostCommon }
                }
            } else {
                if (filteredList.size == 2) {
                    filteredList = filteredList.filter { it[i].toString() == "0" }
                } else {
                    filteredList = filteredList.filter { it[i].toString() == leastCommon }
                }
            }
        }

        return filteredList[0]
    }

    fun part2(input: List<String>): Int {
        val binaryNumLength = input[0].length
        val o2GeneratorRating = findRatingValue(binaryNumLength, "o2", input)
        val co2ScrubberRating = findRatingValue(binaryNumLength, "co2", input)

        return o2GeneratorRating.binaryToInt() * co2ScrubberRating.binaryToInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test", 3)
    println("Day03_part1_test_result: " + part1(testInput))
    check(part1(testInput) == 198)

    // test if implementation of part two meets criteria from the description
    println("Day03_part2_test_result: " + part2(testInput))
    check(part2(testInput) == 230)

    val input = readInput("Day03", 3)
    println("Day03_part1_result: " + part1(input))
    println("Day03_part2_result: " + part2(input))
}