
    fun parseTriple(line: String): Triple<Long, Long, Long> {
        val parts = line.split(" ").map { it.toLong() }
        return Triple(parts[0], parts[1], parts[2])
    }

    fun processLines(lines: List<String>): Pair<List<Long>, List<List<Triple<Long, Long, Long>>>> {
        val seedList = mutableListOf<Long>()
        val mappings = mutableListOf<List<Triple<Long, Long, Long>>>()

        var currentMapping = mutableListOf<Triple<Long, Long, Long>>()
        lines.filter { it.isNotBlank() }.forEach { line ->
            when {
                line.startsWith("seeds:") -> seedList.addAll(line.removePrefix("seeds:").trim().split(" ").mapNotNull { it.toLongOrNull() })
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

        return Pair(seedList, mappings)
    }

    fun mapSourceToDest(source: Long, mapping: List<Triple<Long, Long, Long>>): Long {
        mapping.forEach { (startDest, startSource, length) ->
            if (source in startSource until startSource + length) {
                return startDest + (source - startSource)
            }
        }
        return source
    }

    fun findMinimumLocation(
        seedList: List<Long>,
        seedToSoil: List<Triple<Long, Long, Long>>,
        soilToFertilizer: List<Triple<Long, Long, Long>>,
        fertilizerToWater: List<Triple<Long, Long, Long>>,
        waterToLight: List<Triple<Long, Long, Long>>,
        lightToTemperature: List<Triple<Long, Long, Long>>,
        temperatureToHumidity: List<Triple<Long, Long, Long>>,
        humidityToLocation: List<Triple<Long, Long, Long>>
    ): Long {
        val locations = seedList.map { seed ->
            val soil = mapSourceToDest(seed, seedToSoil)
            val fertilizer = mapSourceToDest(soil, soilToFertilizer)
            val water = mapSourceToDest(fertilizer, fertilizerToWater)
            val light = mapSourceToDest(water, waterToLight)
            val temperature = mapSourceToDest(light, lightToTemperature)
            val humidity = mapSourceToDest(temperature, temperatureToHumidity)
            mapSourceToDest(humidity, humidityToLocation)
        }
        return locations.minOrNull() ?: Long.MAX_VALUE
    }

    fun main() {
        val input = readInput("inputs/Day5")
        val (seedList, mappings) = processLines(input)

        val result = findMinimumLocation(
            seedList,
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