import com.sun.org.apache.xalan.internal.lib.ExsltMath
typealias Card = Pair<List<Int>, List<Int>>

fun main() {
    val input = readInput("inputs/Day4")
    val cards = mutableListOf<Card>()

    for (i in input) {
        val numbers = i.substringAfter(":").trim()
        val (winningNumbers, myNumbers) = numbers.split("|")
            .map { it ->
                it.trim().split("\\s+".toRegex())
                    .filter { it.isNotEmpty() }.map { it.toInt() }
            }
        val card: Card = Pair(winningNumbers, myNumbers)
        cards.add(card)
    }

    println(numTotalCards(cards))

}

fun numTotalCards(cards: List<Card>): Int {
    val map = cards.mapIndexed { index, card -> index to card }.toMap().toMutableMap()
    val queue = ArrayDeque<Int>().apply { cards.indices.forEach { add(it) } }
    var totalCards = 0

    while (queue.isNotEmpty()) {
        val index = queue.removeFirst()
        totalCards++

        val (winningNumbers, cardNumbers) = map[index]!!
        var matches = 0

        for (num in winningNumbers) {
            if (num in cardNumbers) matches++
        }

        for (i in 1..matches) {
            val nextIndex = index + i
            if (nextIndex in map) {
                queue.add(nextIndex)
            }
        }
    }

    return totalCards
}