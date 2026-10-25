import java.util.Locale

class Player {
    var name: String = ""
    var field: Array<CharArray> = Array(10) { CharArray(10) { '.' } }
    var shots: Int = 0
    var hits: Int = 0
    var shipsLeft: Int = 20

    fun printField(showShips: Boolean = true) {
        val size = field.size
        println("=== Поле игрока $name ===")
        print("   ")
        for (c in 0 until size) print("$c ")
        println()
        for (r in 0 until size) {
            print("$r  ")
            for (c in 0 until size) {
                val symbol = field[r][c]
                val toPrint = if (!showShips && symbol == '#') '.' else symbol
                print("$toPrint ")
            }
            println()
        }
    }

    fun isAlive(): Boolean = shipsLeft > 0

    fun accuracy(): Double {
        if (shots == 0) return 0.0
        return hits * 100.0 / shots
    }

    fun status(): String {
        val acc = String.format(Locale.US, "%.1f", accuracy())
        return "Игрок $name: палуб осталось $shipsLeft, выстрелов $shots, попаданий $hits, точность $acc%"
    }

    fun printStats() {
        println(status())
    }

    fun takeDamage(): Boolean {
        shipsLeft--
        return shipsLeft > 0
    }

    fun registerShot(hit: Boolean) {
        shots++
        if (hit) hits++
    }

    fun reset() {
        shots = 0
        hits = 0
        shipsLeft = 20
        for (r in field.indices) {
            for (c in field[r].indices) {
                field[r][c] = '.'
            }
        }
    }
}
