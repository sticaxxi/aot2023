fun main() {
    val schema = readInput("inputs/Day3")
    val symbols = setOf('*')
    var total = 0

    val directions = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(-1, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(1, -1),
            intArrayOf(1, 0),
            intArrayOf(1, 1)
    )

    for ((y) in schema.withIndex()) {
        var x = 1
        var firstNumber = 0
        var secondNumber = 0
        while (x < schema[y].length - 1) {
            if (symbols.contains(schema[y][x])) {
                for (dir in directions) {
                    val nextY = y + dir[0]
                    val nextX = x + dir[1]
                    if (nextY in schema.indices && nextX in schema[nextY].indices && schema[nextY][nextX].isDigit()) {
                        val generateNumber = number(schema[nextY], nextX)
                        when {
                            firstNumber == 0 -> firstNumber = generateNumber
                            firstNumber != generateNumber -> secondNumber = generateNumber
                        }
                    }
                }
            }
            if (firstNumber != 0 && secondNumber != 0){
                total += (firstNumber * secondNumber)
            }
            firstNumber = 0
            secondNumber = 0
            x++
        }
    }
    println(total)
}

fun number(schema: String, y: Int, x: Int): Int {
    var number = ""
    if (x > 1 && schema[x-2].isDigit()){
        number = schema[x-2] + number
        if (x > 2 && schema[x-3].isDigit()){
            number = schema[x-3] + number
        }
    }
    var xsecondo = x
    while (xsecondo in schema.indices && schema[xsecondo].isDigit()) {
        number += schema[xsecondo]
        xsecondo++
    }
    return number.toInt()
}