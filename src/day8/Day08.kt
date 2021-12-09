package day8

import readInput

data class Number(
    val segments: Int = -1,
    var count: Int = 0,
    val matchingCode: String? = null,
    val correspondingNumber: String? = null
)

fun main() {
    fun part1(input: List<String>): Int {
        val codeList = mutableListOf<List<String>>()
        val rules = object {
            val one = Number(segments = 2)
            val four = Number(segments = 4)
            val seven = Number(segments = 3)
            val eight = Number(segments = 7)
        }

        input.forEach {
            val line = it.substringAfter("|").trim()
            val codes = line.split(" ").filter { code -> code.isNotEmpty() }
            codeList.add(codes)
        }

        codeList.forEach {
            it.forEach { code ->
                when (code.length) {
                    rules.one.segments -> rules.one.count++
                    rules.four.segments -> rules.four.count++
                    rules.seven.segments -> rules.seven.count++
                    rules.eight.segments -> rules.eight.count++
                }
            }
        }

        return rules.one.count + rules.four.count + rules.seven.count + rules.eight.count
    }

    fun String.alphabetized() = String(toCharArray().apply { sort() })

    fun String.containsAny(arr: CharArray): Boolean {
        val strArr = toCharArray()
        for (item in arr) {
            if (strArr.contains(item)) {
                return true
            }
        }
        return false
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEach {
            val numberMap = mutableMapOf<String, String>()

            val decodingNumberLine = it.substringBefore("|").trim()
            val decodingCodes =
                decodingNumberLine.split(" ").filter { code -> code.isNotEmpty() }

            val outputLine = it.substringAfter("|").trim()
            val outputCodes = outputLine.split(" ").filter { code -> code.isNotEmpty() }

            for (code in decodingCodes) {
                if (code.length == 2) {
                    numberMap.put("1", code)
                }
                if (code.length == 4) {
                    numberMap.put("4", code)
                }
                if (code.length == 3) {
                    numberMap.put("7", code)
                }
                if (code.length == 7) {
                    numberMap.put("8", code)
                }
            }

            for (code in decodingCodes) {
                if (code.length == 6) {
                    val codeList = mutableListOf<Char>()
                    codeList.addAll(code.toCharArray().toList())
                    val leftOver = numberMap.get("8")?.toList()?.subtract(codeList)
                    if (leftOver?.isEmpty() == false && numberMap.get("1")?.contains(leftOver.first()) == true) {
                        numberMap.put("6", code)
                        continue
                    }
                    if (leftOver?.isEmpty() == false && numberMap.get("4")
                            ?.contains(leftOver.first()) == true
                    ) {
                        numberMap.put("0", code)
                        continue
                    }
                    numberMap.put("9", code)
                }
            }

            for (code in decodingCodes) {
                if (code.length == 5) {
                    val codeList = mutableListOf<Char>()
                    codeList.addAll(code.toCharArray().toList())
                    val leftOver = numberMap.get("8")?.toList()?.subtract(codeList)
                    val all = mutableListOf<Char>()
                    all.addAll(numberMap.get("6")?.toCharArray()?.toList()!!)
                    all.addAll(codeList)
                    val mySet = all.toSet()
                    var containsNone = true

                    for (item in numberMap.get("1")?.toList()!!) {
                        if (leftOver?.contains(item) == true) {
                            containsNone = false
                            break
                        }
                    }

                    if (containsNone) {
                        numberMap.put("3", code)
                        continue
                    }
                    if (mySet.size == 7) {
                        numberMap.put("2", code)
                        continue
                    }
                    numberMap.put("5", code)
                }
            }


            var number = ""
            for (code in outputCodes) {
                if (code.length == 2) {
                    number += "1"
                    continue
                }
                if (code.length == 4) {
                    number += "4"
                    continue
                }
                if (code.length == 3) {
                    number += "7"
                    continue
                }
                if (code.length == 7) {
                    number += "8"
                    continue
                }
                val codeList = code.toList()
                numberMap.map { (k, v) ->
                    val valueList = v.toList()
                    if (valueList.size == codeList.size && codeList.containsAll(valueList)) {
                        number += k
                    }
                }
            }
            sum += number.toInt()
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test", 8)
    //println("Day08_part1_test_result: " + part1(testInput))
    //check(part1(testInput) == 26)

    // test if implementation of part two meets criteria from the description
    println("Day08_part2_test_result: " + part2(testInput))
    check(part2(testInput) == 61229)

    val input = readInput("Day08", 8)
    //println("Day08_part1_result: " + part1(input))
    println("Day08_part2_result: " + part2(input))
}
