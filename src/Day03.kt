fun main() {
    val schema = readInput("inputs/Day3")

    val symbols = setOf('#', '*', '+', '$', '%','/', '&', '=', '-', '@')

    var total = 0
    for ((y) in schema.withIndex()) {
        var x = 1
        while (x < schema[y].length - 1) {
            if (schema[y][x].isDigit() && (
                            symbols.contains(schema[y][x + 1])
                            || symbols.contains(schema[y][x - 1])
                            || (y > 0 && symbols.contains(schema[y - 1][x - 1]))
                            || (y > 0 && x <140 && symbols.contains(schema[y - 1][x]))
                            || (y > 0 && x<139 && symbols.contains(schema[y - 1][x + 1]))
                            || (y < schema.size - 1 && symbols.contains(schema[y + 1][x - 1]))
                            || (y < schema.size - 1 && x <140 && symbols.contains(schema[y + 1][x]))
                            || (y < schema.size - 1 && x <139 && symbols.contains(schema[y + 1][x + 1]))
                            )){
                var numero = ""
                if (schema[y][x-1].isDigit()){
                    numero = schema[y][x-1] +numero
                    if (schema[y][x-2].isDigit()){
                        numero = schema[y][x-2] +numero
                    }
                }
                while (x <140 && schema[y][x].isDigit()) {
                    numero += schema[y][x]
                    x++
                }
                total += numero.toInt()
            }
            x++
        }
    }
    println(total)
}