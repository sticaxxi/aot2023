import com.sun.org.apache.xalan.internal.lib.ExsltMath.power

fun main() {
    val cards = readInput("inputs/Day4")
    println(solve(cards))

}

fun solve(cards: List<String>): Int {
    var totalPoints = 0
    for (card in cards) {
        val numbers = card.substringAfter(":").trim()
        val (winningNumbers, myNumbers) = numbers.split("|")
            .map { it ->
                it.trim().split("\\s+".toRegex())
                .filter { it.isNotEmpty() }.map { it.toInt() } }
        var matchedNumbers = myNumbers.count { it in winningNumbers }

        var points = 0
        if (matchedNumbers > 0) {
            matchedNumbers -= 1
            points = (power(2.0, matchedNumbers.toDouble()).toInt())
        }

        totalPoints += points
    }
    return totalPoints
}

