fun main() {
    val schema = readInput("inputs/Day3")

    val symbols = setOf('*')

    var total = 0
    for ((y) in schema.withIndex()) {
        var x = 1
        var firstNumber = 0
        var secondNumber = 0
        while (x < schema[y].length - 1) {
            var error = false

            if (symbols.contains(schema[y][x])){
            for (i in 0 until 8) {
                when (i) {
                    0 -> if (schema[y][x + 1].isDigit()) {
                        val generateNumber = number(schema[y], x + 1)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if ( secondNumber != 0 && secondNumber != generateNumber) error = true
                    }

                    1 -> if (schema[y][x - 1].isDigit()) {
                        val generateNumber = number(schema[y], x - 1)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if (secondNumber != 0 && secondNumber != generateNumber) error = true

                    }

                    2 -> if (y > 0 && schema[y - 1][x - 1].isDigit()) {
                        val generateNumber = number(schema[y - 1], x - 1)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if (secondNumber != 0 && secondNumber != generateNumber) error = true

                    }

                    3 -> if (y > 0 && x < 140 && schema[y - 1][x].isDigit()) {
                        val generateNumber = number(schema[y - 1], x)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if ( secondNumber != 0 && secondNumber != generateNumber) error = true

                    }

                    4 -> if (y > 0 && x < 139 && schema[y - 1][x + 1].isDigit()) {
                        val generateNumber = number(schema[y - 1], x + 1)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if (secondNumber != 0 && secondNumber != generateNumber) error = true

                    }

                    5 -> if (y < schema.size - 1 && schema[y + 1][x - 1].isDigit()) {
                        val generateNumber = number(schema[y + 1], x - 1)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if (secondNumber != 0 && secondNumber != generateNumber) error = true

                    }

                    6 -> if (y < schema.size - 1 && x < 140 && schema[y + 1][x].isDigit()) {
                        val generateNumber = number(schema[y + 1], x)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if (secondNumber != 0 && secondNumber != generateNumber) error = true

                    }

                    7 -> if (y < schema.size - 1 && x < 139 && schema[y + 1][x + 1].isDigit()) {
                        val generateNumber = number(schema[y + 1], x + 1)
                        if (firstNumber == 0) firstNumber = generateNumber
                        else if (secondNumber == 0 && firstNumber != generateNumber) secondNumber = generateNumber
                        else if (secondNumber != 0 && secondNumber != generateNumber) error = true

                    }
                }
            }
                }
            if (!error && firstNumber != 0 && secondNumber != 0){
                total += (firstNumber * secondNumber)
            }
            firstNumber = 0
            secondNumber = 0
            x++
        }
    }
    println(total)
}

fun number(schema: String, x: Int): Int {

        var numero = ""
        if (schema[x-1].isDigit()){
            numero = schema[x-1] +numero
            if (schema[x-2].isDigit()){
                numero = schema[x-2] +numero
            }
        }
    var xsecondo = x
        while (xsecondo <140 && schema[xsecondo].isDigit()) {
            numero += schema[xsecondo]
            xsecondo++
        }

return numero.toInt()
}