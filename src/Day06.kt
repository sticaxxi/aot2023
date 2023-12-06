data class Race(val time: Int, val distance: Int)
data class RacePT2(val time: Long, val distance: Long)

fun countPossibleWins(races: List<Race>): Int {
    return races
        .map { race ->
            (1 until race.time)
                .map { buttonPressTime -> buttonPressTime * (race.time - buttonPressTime) }
                .count { it > race.distance }
        }
        .reduce(Int::times)
}

fun countPossibleWins(race: RacePT2): Long {
    return (1L until race.time)
        .map { buttonPressTime -> buttonPressTime * (race.time - buttonPressTime) }
        .count { it > race.distance }.toLong()
}
fun parseRaces(lines: List<String>): List<Race> {
    val times = lines[0].substringAfter(":").trim().split("\\s+".toRegex())
    val distances = lines[1].substringAfter(":").trim().split("\\s+".toRegex())

    return times.zip(distances) { t, d -> Race(t.toInt(), d.toInt()) }
}

fun parseRace(lines: List<String>): RacePT2 {
    val time = lines[0].substringAfter(":").replace(" ", "").toLong()
    val distance = lines[1].substringAfter(":").replace(" ", "").toLong()

    return RacePT2(time, distance)
}

fun main() {
    val input = readInput("inputs/Day6")

    println(countPossibleWins(parseRaces(input)))
    println(countPossibleWins(parseRace(input)))
}