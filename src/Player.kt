import java.util.Locale

class Player(val name: String) {

    // Символы: '.' - пусто, '#' - палуба, 'X' - попадание, 'O' - промах
    private val _field: Array<CharArray> =
        Array(10) { CharArray(10) { '.' } }

    private var _shots: Int = 0
    private var _hits: Int = 0
    private var _shipsLeft: Int = 20

    fun field(): Array<CharArray> = _field

    val shots: Int
        get() = _shots

    val hits: Int
        get() = _hits

    val shipsLeft: Int
        get() = _shipsLeft

    fun registerShot(hit: Boolean) {
        _shots++
        if (hit) _hits++
    }

    fun placeShip(row: Int, col: Int) {
        if (row in 0..9 && col in 0..9) {
            _field[row][col] = '#'
        }
    }

    // X - попал, O - промах
    fun markShot(row: Int, col: Int, hit: Boolean) {
        if (row in 0..9 && col in 0..9) {
            _field[row][col] = if (hit) 'X' else 'O'
        }
    }

    // true - игрок ещё жив, false - убит
    fun takeDamage(): Boolean {
        _shipsLeft--
        return _shipsLeft > 0
    }

    fun reset() {
        _shots = 0
        _hits = 0
        _shipsLeft = 20
        for (r in 0..9) {
            for (c in 0..9) {
                _field[r][c] = '.'
            }
        }
    }

    fun isAlive(): Boolean = _shipsLeft > 0

    fun accuracy(): Double =
        if (_shots == 0) 0.0 else _hits * 100.0 / _shots

    fun status(): String {
        val acc = String.format(Locale.US, "%.1f", accuracy())
        return "Игрок $name: палуб осталось $_shipsLeft, выстрелов $_shots, попаданий $_hits, точность $acc%"
    }

    fun printField(showShips: Boolean = true) {
        println("=== Поле игрока $name ===")
        print("   ")
        for (c in 0..9) print("$c ")
        println()
        for (r in 0..9) {
            print("$r  ")
            for (c in 0..9) {
                val symbol = _field[r][c]
                val toPrint = if (!showShips && symbol == '#') '.' else symbol
                print("$toPrint ")
            }
            println()
        }
    }

    fun printStats() {
        println(status())
    }
}
