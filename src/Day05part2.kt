

fun processLinesPartTwo(lines: List<String>): Pair<List<Pair<Long, Long>>, List<List<Triple<Long, Long, Long>>>> {
    val seedListRanges = mutableListOf<Pair<Long, Long>>()
    val mappings = mutableListOf<List<Triple<Long, Long, Long>>>()

    var currentMapping = mutableListOf<Triple<Long, Long, Long>>()
    lines.filter { it.isNotBlank() }.forEach { line ->
        when {
            line.startsWith("seeds:") -> line.removePrefix("seeds:").trim().split(" ").windowed(2, 2).forEach {
                val seedStart = it[0].toLongOrNull()
                val seedRange = it[1].toLongOrNull()
                if (seedStart != null && seedRange != null) {
                    seedListRanges.add(Pair(seedStart, seedStart + seedRange))
                }
            }
            line.endsWith("map:") -> {
                if (currentMapping.isNotEmpty()) {
                    mappings.add(currentMapping)
                    currentMapping = mutableListOf()
                }
            }
            else -> currentMapping.add(parseTriple(line))
        }
    }
    mappings.add(currentMapping)

    return Pair(seedListRanges, mappings)
}
fun findMinimumLocation(
    seedListRanges: List<Pair<Long, Long>>,
    seedToSoil: List<Triple<Long, Long, Long>>,
    soilToFertilizer: List<Triple<Long, Long, Long>>,
    fertilizerToWater: List<Triple<Long, Long, Long>>,
    waterToLight: List<Triple<Long, Long, Long>>,
    lightToTemperature: List<Triple<Long, Long, Long>>,
    temperatureToHumidity: List<Triple<Long, Long, Long>>,
    humidityToLocation: List<Triple<Long, Long, Long>>
): Long {
    var minLocation = Long.MAX_VALUE
    for((start, end) in seedListRanges) {
        for(seed in start until end) {
            val soil = mapSourceToDest(seed, seedToSoil)
            val fertilizer = mapSourceToDest(soil, soilToFertilizer)
            val water = mapSourceToDest(fertilizer, fertilizerToWater)
            val light = mapSourceToDest(water, waterToLight)
            val temperature = mapSourceToDest(light, lightToTemperature)
            val humidity = mapSourceToDest(temperature, temperatureToHumidity)
            val location = mapSourceToDest(humidity, humidityToLocation)
            minLocation = minOf(minLocation, location)
        }
    }
    return minLocation
}

fun main() {
    val input = readInput("inputs/Day5")
    val (seedListRanges, mappings) = processLinesPartTwo(input)

    val result = findMinimumLocation(
        seedListRanges,
        mappings[0],
        mappings[1],
        mappings[2],
        mappings[3],
        mappings[4],
        mappings[5],
        mappings[6]
    )
    println(result)
}