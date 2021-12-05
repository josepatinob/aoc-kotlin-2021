package day5

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val seaFloor = Array(1000) { Array(1000) { 0 } }
        var dangerousAreas = 0

        input.forEach {
            val start = it.substringBefore("-").trim()
            val end = it.substringAfter(">").trim()
            val x1 = start.substringBefore(",").toInt()
            val y1 = start.substringAfter(",").toInt()
            val x2 = end.substringBefore(",").toInt()
            val y2 = end.substringAfter(",").toInt()

            if (x1 == x2) {
                if (y1 < y2) {
                    for (i in y1..y2) {
                        seaFloor[i][x1] += 1
                    }
                }

                if (y1 > y2) {
                    for (i in y2..y1) {
                        seaFloor[i][x1] += 1
                    }
                }
            }

            if (y1 == y2) {
                if (x1 < x2) {
                    for (i in x1..x2) {
                        seaFloor[y1][i] += 1
                    }
                }

                if (x1 > x2) {
                    for (i in x2..x1) {
                        seaFloor[y1][i] += 1
                    }
                }
            }
        }

        for (x in 0 until seaFloor.size) {
            for (y in 0 until seaFloor.size) {
                if (seaFloor[x][y] >= 2) {
                    dangerousAreas++
                }
            }
        }

        return dangerousAreas
    }

    fun part2(input: List<String>): Int {
        val seaFloor = Array(1000) { Array(1000) { 0 } }
        var dangerousAreas = 0

        input.forEach {
            val start = it.substringBefore("-").trim()
            val end = it.substringAfter(">").trim()
            val x1 = start.substringBefore(",").toInt()
            val y1 = start.substringAfter(",").toInt()
            val x2 = end.substringBefore(",").toInt()
            val y2 = end.substringAfter(",").toInt()

            when {
                x1 == x2 -> {
                    if (y1 < y2) {
                        for (i in y1..y2) {
                            seaFloor[i][x1] += 1
                        }
                    }

                    if (y1 > y2) {
                        for (i in y2..y1) {
                            seaFloor[i][x1] += 1
                        }
                    }
                }
                y1 == y2 -> {
                    if (x1 < x2) {
                        for (i in x1..x2) {
                            seaFloor[y1][i] += 1
                        }
                    }

                    if (x1 > x2) {
                        for (i in x2..x1) {
                            seaFloor[y1][i] += 1
                        }
                    }
                }
                else -> {
                    if (x1 > x2 && y1 < y2) {
                        var counter = y1
                        for (i in x1 downTo x2) {
                            seaFloor[counter][i] += 1
                            counter++
                        }
                    }
                    if (x1 < x2 && y1 > y2) {
                        var counter = y1
                        for (i in x1..x2) {
                            seaFloor[counter][i] += 1
                            counter--
                        }
                    }
                    if (x1 > x2 && y1 > y2) {
                        var counter = y1
                        for (i in x1 downTo x2) {
                            seaFloor[counter][i] += 1
                            counter--
                        }
                    }
                    if (x1 < x2 && y1 < y2) {
                        var counter = y1
                        for (i in x1..x2) {
                            seaFloor[counter][i] += 1
                            counter++
                        }
                    }
                }
            }
        }

        for (element in seaFloor) {
            for (y in seaFloor.indices) {
                if (element[y] >= 2) {
                    dangerousAreas++
                }
            }
        }

        return dangerousAreas
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test", 5)
    //println("Day05_part1_test_result: " + part1(testInput))
    //check(part1(testInput) == 5)

    // test if implementation of part two meets criteria from the description
    //println("Day01_part2_test_result: " + part2(testInput))
    //check(part2(testInput) == 12)

    val input = readInput("Day05", 5)
    //println("Day05_part1_result: " + part1(input))
    println("Day05_part2_result: " + part2(input))
}
