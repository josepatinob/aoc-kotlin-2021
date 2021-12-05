package day4

import readInput

data class BingoCardNumber(
    var number: Int,
    var marked: Boolean
) {
    constructor() : this(0, false)
}

data class BingoRow(
    var numbers: List<BingoCardNumber>,
)

data class BingoColumn(
    var numbers: List<BingoCardNumber>
)

data class BingoCard(
    var rows: List<BingoRow>,
    var winningScore: Int = 0,
    var winningPosition: Int = -1,
    var columns: List<BingoColumn> = emptyList()
)

fun main() {

    fun createBingoRow(numberRow: String): BingoRow {
        val cardNumbers = mutableListOf<BingoCardNumber>()
        val numbers = numberRow.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        numbers.forEach {
            cardNumbers.add(
                BingoCardNumber(it, false)
            )
        }
        return BingoRow(cardNumbers)
    }

    fun getFinalScore(card: BingoCard, winningNum: Int): Int {
        var unmarkedSum = 0
        card.rows.forEach {
            unmarkedSum += it.numbers.filter { num -> !num.marked }.sumOf { it.number }
        }

        return unmarkedSum * winningNum
    }

    fun part1(input: List<String>): Int {
        var finalScore = 0
        val bingoNumbers = input[0].split(",").map { it.toInt() }
        val bingoCards = mutableListOf<BingoCard>()
        val bingoRowList = mutableListOf<BingoRow>()

        input.forEachIndexed { index, row ->
            // skips first and second item
            if (index in listOf(0, 1)) {
                return@forEachIndexed
            }

            if (row.isEmpty() || row.isBlank()) {
                bingoCards.add(
                    BingoCard(bingoRowList.map { it.copy() })
                )
                bingoRowList.clear()
            } else {
                bingoRowList.add(createBingoRow(row))
                if (index == input.size - 1) {
                    bingoCards.add(
                        BingoCard(bingoRowList.map { it.copy() })
                    )
                    bingoRowList.clear()
                }
            }
        }

        for (number in bingoNumbers) {
            if (finalScore != 0) break
            for (card in bingoCards) {
                for (row in card.rows) {
                    for (item in row.numbers) {
                        if (number == item.number) {
                            item.marked = true
                        }
                    }
                    if (row.numbers.all { num -> num.marked }) {
                        finalScore = getFinalScore(card, number)
                        break
                    }
                }
            }
        }

        return finalScore
    }

    fun copyList(oldList: List<BingoColumn>): List<BingoColumn> {
        val newList = mutableListOf<BingoColumn>()
        for (item in oldList) {
            val numbersList = mutableListOf<Int>()
            item.numbers.forEach {
                numbersList.add(it.number)
            }
            val bingoNumbersList = mutableListOf<BingoCardNumber>()
            numbersList.forEach {
                bingoNumbersList.add(
                    BingoCardNumber(it, false)
                )
            }
            newList.add(
                BingoColumn(
                    bingoNumbersList
                )
            )
        }
        return newList
    }

    fun fillBingoColumn(numberRow: String, columnList: List<BingoColumn>, rowIndex: Int) {
        val numbers = numberRow.split(" ").filter {
            it.isNotEmpty()
        }.map {
            it.toInt()
        }

        numbers.forEachIndexed { index, i ->
            val columnNum = columnList[index].numbers[rowIndex]
            columnNum.number = i
            columnNum.marked = false
        }
    }

    fun part2(input: List<String>): Int {
        var positionWon = 1
        val bingoNumbers = input[0].split(",").map { it.toInt() }
        val bingoCards = mutableListOf<BingoCard>()
        val bingoRowList = mutableListOf<BingoRow>()
        val bingoColumnList = mutableListOf(
            BingoColumn(
                listOf(
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber()
                )
            ),
            BingoColumn(
                listOf(
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber()
                )
            ),
            BingoColumn(
                listOf(
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber()
                )
            ),
            BingoColumn(
                listOf(
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber()
                )
            ),
            BingoColumn(
                listOf(
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber(),
                    BingoCardNumber()
                )
            )
        )
        var rowIndex = 0

        input.forEachIndexed { index, row ->
            // skips first and second item
            if (index in listOf(0, 1)) {
                return@forEachIndexed
            }

            if (row.isEmpty() || row.isBlank()) {
                bingoCards.add(
                    BingoCard(
                        rows = bingoRowList.map {
                            it.copy()
                        }, columns = copyList(bingoColumnList)
                    )
                )
                bingoRowList.clear()
                rowIndex = 0
            } else {
                bingoRowList.add(createBingoRow(row))
                fillBingoColumn(row, bingoColumnList, rowIndex)
                rowIndex++
                if (index == input.size - 1) {
                    bingoCards.add(
                        BingoCard(
                            rows = bingoRowList.map {
                                it.copy()
                            }, columns = copyList(bingoColumnList)
                        )
                    )
                    bingoRowList.clear()
                }
            }
        }

        for (number in bingoNumbers) {
            for (card in bingoCards) {
                if (card.winningPosition != -1) continue
                for (row in card.rows) {
                    for (item in row.numbers) {
                        if (number == item.number) {
                            item.marked = true
                        }
                    }
                    if (row.numbers.all { num -> num.marked }) {
                        card.winningPosition = positionWon
                        card.winningScore = getFinalScore(card, number)
                        positionWon++
                        break
                    }
                }
                for (column in card.columns) {
                    for (item in column.numbers) {
                        if (number == item.number) {
                            item.marked = true
                        }
                    }
                    if (column.numbers.all { num -> num.marked }) {
                        card.winningPosition = positionWon
                        card.winningScore = getFinalScore(card, number)
                        positionWon++
                        break
                    }
                }
            }
        }

        return bingoCards.sortedBy { it.winningPosition }.get(bingoCards.size - 1).winningScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test", 4)
    //println("Day04_part1_test_result: " + part1(testInput))
    //check(part1(testInput) == 4512)

    // test if implementation of part two meets criteria from the description
    println("Day04_part2_test_result: " + part2(testInput))
    check(part2(testInput) == 1924)

    val input = readInput("Day04", 4)
    //println("Day04_part1_result: " + part1(input))
    println("Day04_part2_result: " + part2(input))
}