package day6

import readInput

fun main() {
    fun part1(input: List<String>, timesToRepeat: Int = 18): Long {
        val fishList = input[0].split(",").map { it.toInt() }.toMutableList()

        repeat(timesToRepeat) {
            for (i in 0 until fishList.size) {
                val fish = fishList[i]
                if (fish == 0) {
                    fishList[i] = 6
                    fishList.add(8)
                } else {
                    fishList[i] -= 1
                }
            }
        }

        return fishList.size.toLong()
    }

    fun updateFishArray(arr: Array<Long>): Array<Long> {
        val tempArray = Array(9) { 0L }
        for ((index, fish) in arr.withIndex()) {
            if (index == 0 && fish != 0L) {
                tempArray[6] += fish
                tempArray[index] = 0
                tempArray[8] += fish
            } else if (fish == 0L) {
                continue
            } else {
                tempArray[index - 1] += fish
            }
        }
        return tempArray
    }

    fun part2(input: List<String>, timesToRepeat: Int = 18): Long {
        val initialFishList = input[0].split(",").map { it.toInt() }.toMutableList()
        val fishCounter = Array(9) { 0L }

        for (i in 0 until initialFishList.size) {
            fishCounter[initialFishList[i]]++
        }

        // each repetition is another day going by
        repeat(timesToRepeat) {
            val tempArray = updateFishArray(fishCounter)
            tempArray.copyInto(fishCounter)
        }

        return fishCounter.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test", 6)
    println("Day06_part1_test_result: " + part1(testInput, 80))
    check(part1(testInput, 80) == 5934L)

    // test if implementation of part two meets criteria from the description
    println("Day06_part2_test_result: " + part2(testInput, 80))
    check(part2(testInput, 80) == 5934L)

    val input = readInput("Day06", 6)
    println("Day06_part1_result: " + part1(input))
    println("Day06_part2_result: " + part2(input, 256))
}
