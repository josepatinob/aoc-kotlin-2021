package day7

import getElapsedTime
import readInput
import kotlin.math.roundToInt
import kotlin.time.Duration

data class Crab(
    var initialPosition: Int,
)

data class CrabTeam(
    var optimalFuelCost: Int = -1,
    var optimalPosition: Int = -1,
    val crabList: List<Crab>,
) {
    private val averageInitialPosition = crabList.map { it.initialPosition }.average().roundToInt()
    val rangeFrom = averageInitialPosition - averageInitialPosition
    val rangeTo = averageInitialPosition + averageInitialPosition
}

fun main() {
    fun part1(input: List<String>): Int {
        val initialList = input[0].split(",").map { it.toInt() }
        val crabList = mutableListOf<Crab>()

        initialList.forEach {
            crabList.add(
                Crab(
                    initialPosition = it
                )
            )
        }

        val team = CrabTeam(crabList = crabList)

        for (i in team.rangeFrom..team.rangeTo) {
            var totalCost = 0
            for (crab in team.crabList) {
                val cost = Math.abs(crab.initialPosition - i)
                totalCost += cost
                if (team.optimalFuelCost != -1 && team.optimalFuelCost < totalCost) break
            }

            if (team.optimalFuelCost == -1 || team.optimalFuelCost > totalCost) {
                team.optimalFuelCost = totalCost
            }
        }

        return team.optimalFuelCost
    }

    fun calculateFuelCost(initialPosition: Int, newPosition: Int): Int {
        var counter = 1
        var cost = 0

        if (initialPosition > newPosition) {
            for (i in newPosition until initialPosition) {
                cost += counter
                counter++
            }
        } else if (initialPosition == newPosition) {
            return 0
        } else {
            for (i in initialPosition + 1..newPosition) {
                cost += counter
                counter++
            }
        }

        return cost
    }

    fun part2(input: List<String>): Int {
        val start = System.currentTimeMillis()
        val initialList = input[0].split(",").map { it.toInt() }
        val crabList = mutableListOf<Crab>()

        initialList.forEach {
            crabList.add(
                Crab(
                    initialPosition = it
                )
            )
        }

        val team = CrabTeam(crabList = crabList)

        for (i in team.rangeFrom..team.rangeTo) {
            var totalCost = 0
            for (crab in team.crabList) {
                val cost = calculateFuelCost(crab.initialPosition, i)
                totalCost += cost
                if (team.optimalFuelCost != -1 && team.optimalFuelCost < totalCost) break
            }

            if (team.optimalFuelCost == -1 || team.optimalFuelCost > totalCost) {
                team.optimalFuelCost = totalCost
            }
        }
        val end = System.currentTimeMillis()
        println("Total time: ${getElapsedTime(startTime = start, endTime = end)}")
        return team.optimalFuelCost
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test", 7)
    //println("Day07_part1_test_result: " + part1(testInput))
    //check(part1(testInput) == 37)

    // test if implementation of part two meets criteria from the description
    //println("Day07_part2_test_result: " + part2(testInput))
    //check(part2(testInput) == 168)

    val input = readInput("Day07", 7)
    //println("Day07_part1_result: " + part1(input))
    println("Day07_part2_result: " + part2(input))
}
