fun main() {
    print(part1())
    print(part2())
}

val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun part1() {
    val testInput = readInput("day01")

    println(testInput.sumOf { line: String ->
        line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
    })
}


fun part2() {
    val lines = readInput("day01_part_2")
    println(lines.sumOf { line: String ->
        getNumber(line, StartFrom.BEGINNING) * 10 + getNumber(line, StartFrom.END)
    })
}

fun getNumber(line: String, beginning: StartFrom): Int {

    val indices = when (beginning) {
        StartFrom.BEGINNING -> line.indices
        StartFrom.END -> line.lastIndex downTo 0
    }

    for (idx in indices) {
        line[idx].digitToIntOrNull()?.let { return it }
        for ((wordIndex, word) in words.withIndex()) {
            if (line.substring(idx).startsWith(word)) {
                return (wordIndex + 1)
            }
        }
    }
    error("Unreachable")
}

fun calculateSumOfCalibrationValues(calibrationDocument: String): Int {
    // Split the document into lines
    val lines = calibrationDocument.lines()

    // Extract and sum the calibration values
    val sum = lines.map { line ->
        // Filter out non-digit characters
        val digits = line.filter { it.isDigit() }

        // Extract the first and last digits
        val firstDigit = digits.firstOrNull()
        val lastDigit = digits.lastOrNull()

        // Calculate the calibration value
        val calibrationValue = if (firstDigit != null && lastDigit != null) {
            "$firstDigit$lastDigit".toInt()
        } else {
            0
        }

        calibrationValue
    }.sum()

    return sum
}

enum class StartFrom { BEGINNING, END }