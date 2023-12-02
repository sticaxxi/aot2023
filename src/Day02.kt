fun main() {

    data class Game(val id: Int, val maxRed: Int, val maxGreen: Int, val maxBlue: Int)
    data class CubeResult(val red: Int, val green: Int, val blue: Int)

    fun parseGame(input: String): Game {
        val splitInput = input.removePrefix("Game ").split(": ")
        val id = splitInput[0].toInt()
        val rounds = splitInput[1].split("; ")
        var maxRed = 0
        var maxGreen = 0
        var maxBlue = 0
        for (round in rounds) {
            val cubes = round.split(", ")
            for (cube in cubes) {
                val cubeSplit = cube.split(" ")
                val count = cubeSplit[0].toInt()
                when(cubeSplit[1]) {
                    "red" -> if (count > maxRed) maxRed = count
                    "green" -> if (count > maxGreen) maxGreen = count
                    "blue" -> if (count > maxBlue) maxBlue = count
                }
            }
        }
        return Game(id, maxRed, maxGreen, maxBlue)
    }


    fun checkGames(input: List<String>, red: Int, green: Int, blue: Int): Int {
        val games = input.map { parseGame(it) }
        return games.filter { it.maxRed <= red && it.maxGreen <= green && it.maxBlue <= blue }
                .sumOf { it.id }
    }

    fun power(cubes: CubeResult): Int {
        return cubes.red * cubes.green * cubes.blue
    }
    fun getMinCubes(game: Game): CubeResult {
        return CubeResult(game.maxRed, game.maxGreen, game.maxBlue)
    }
    fun minimumPossibleCubes(input: List<String>): Int {
        val games = input.map { parseGame(it) }
        return games.sumOf { power(getMinCubes(it)) }
    }




    // test if implementation meets criteria from the description, like:
    val input = readInput("inputs/Day2")
    val resultFirstGame = checkGames(input, 12, 13, 14)
    val resultSecondGame = minimumPossibleCubes(input)
    println("primo game: $resultFirstGame")
    println("secondo game : $resultSecondGame")

}
