package day9

import readInput

fun main() {
    fun Int.isGreaterThanAny(compareTo: List<Int?>): Boolean {
        compareTo.forEach { num ->
            if (num != null) {
                if (this >= num) return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val numRows = input.size
        val numColumns = input[0].length
        val lowPoints = mutableListOf<Int>()

        val heightMap: Array<IntArray> = Array(numRows) { IntArray(numColumns) }

        for (i in input.indices) {
            val heightList = input[i].toList().map { it.digitToInt() }
            for (j in heightList.indices) {
                heightMap[i][j] = heightList[j]
            }
        }

        for (i in 0 until numRows) {
            for (j in 0 until numColumns) {
                val height = heightMap[i][j]
                val upOne = if (i > 0) heightMap[i - 1][j] else null
                val downOne = if (i < numRows - 1) heightMap[i + 1][j] else null
                val leftOne = if (j > 0) heightMap[i][j - 1] else null
                val rightOne = if (j < numColumns - 1) heightMap[i][j + 1] else null

                if (height.isGreaterThanAny(listOf(upOne, downOne, leftOne, rightOne))) {
                    continue
                } else {
                    lowPoints.add(height)
                }
            }
        }

        return lowPoints.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Int {
        val numRows = input.size
        val numColumns = input[0].length
        val basinSizes = mutableListOf<Int>()

        val heightMap: Array<IntArray> = Array(numRows) { IntArray(numColumns) }

        for (i in input.indices) {
            val heightList = input[i].toList().map { it.digitToInt() }
            for (j in heightList.indices) {
                heightMap[i][j] = heightList[j]
            }
        }

        for (i in 0 until numRows) {
            for (j in 0 until numColumns) {
                val height = heightMap[i][j]
                val upOne = if (i > 0) heightMap[i - 1][j] else null
                val downOne = if (i < numRows - 1) heightMap[i + 1][j] else null
                val leftOne = if (j > 0) heightMap[i][j - 1] else null
                val rightOne = if (j < numColumns - 1) heightMap[i][j + 1] else null

                if (height.isGreaterThanAny(listOf(upOne, downOne, leftOne, rightOne))) {
                    continue
                } else {
                    // add basin size to basinSizes
                }
            }
        }

        val basinsSorted = basinSizes.sortedDescending()
        return basinsSorted[0] * basinsSorted[1] * basinsSorted[2]
    }

    fun getBasins(matrix: Array<IntArray>, start: Pair<Int, Int>, numRows: Int, numColumns: Int) {
        val i = start.first
        val j = start.second
        val height = matrix[start.first][start.second]

        val upOne = if (i > 0) matrix[i - 1][j] else null
        val downOne = if (i < numRows - 1) matrix[i + 1][j] else null
        val leftOne = if (j > 0) matrix[i][j - 1] else null
        val rightOne = if (j < numColumns - 1) matrix[i][j + 1] else null

        val heightList = listOf(upOne, downOne, leftOne, rightOne)
        heightList.filter { it != null && it > height }
    }

    fun getBasinSize(heights: List<Int?>): Int {
        var basinSize = 1
        heights.forEach { num ->
            if (num != null) {
                if (num != 9) {
                    basinSize++
                }
            }
        }
        return basinSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test", 9)
    //println("Day09_part1_test_result: " + part1(testInput))
    //check(part1(testInput) == 15)

    // test if implementation of part two meets criteria from the description
    println("Day09_part2_test_result: " + part2(testInput))
    check(part2(testInput) == 1134)

    val input = readInput("Day09", 9)
    //println("Day09_part1_result: " + part1(input))
    //println("Day09_part2_result: " + part2(input))
}
