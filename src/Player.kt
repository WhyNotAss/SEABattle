
class Player(val name: String) {

    private val _field = Array(10) { Array(10) { CellState.EMPTY } }
    private var shots = 0
    private var hits = 0

    fun field(): Array<Array<CellState>> = _field

    fun registerShot(hit: Boolean) {
        shots++
        if (hit) hits++
    }

    fun status() {
        println("$name: выстрелов $shots, попаданий $hits")
    }

    fun countShips(): Int {
        var count = 0
        for (r in 0..9) {
            for (c in 0..9) {
                if (_field[r][c] == CellState.SHIP) {
                    val leftIsShip = c > 0 && _field[r][c - 1] == CellState.SHIP
                    val upIsShip = r > 0 && _field[r - 1][c] == CellState.SHIP
                    if (!leftIsShip && !upIsShip) count++
                }
            }
        }
        return count
    }

    fun reset() {
        for (r in 0..9) for (c in 0..9) _field[r][c] = CellState.EMPTY
        shots = 0
        hits = 0
    }

    // 3.1 Координата внутри поля?
    fun isInside(row: Int, col: Int): Boolean {
        return row in 0..9 && col in 0..9
    }

    // 3.2 Можно ли поставить корабль?
    fun canPlaceShip(startRow: Int, startCol: Int, size: Int, horizontal: Boolean): Boolean {
        for (i in 0 until size) {
            val r = if (horizontal) startRow else startRow + i
            val c = if (horizontal) startCol + i else startCol

            if (!isInside(r, c)) return false
            if (_field[r][c] != CellState.EMPTY) return false

            for (dr in -1..1) {
                for (dc in -1..1) {
                    if (dr == 0 && dc == 0) continue
                    val nr = r + dr
                    val nc = c + dc
                    if (isInside(nr, nc) && _field[nr][nc] == CellState.SHIP) {
                        return false
                    }
                }
            }
        }
        return true
    }

    // 3.3 Остались ли неподбитые палубы?
    fun hasShips(): Boolean {
        for (r in 0..9) {
            for (c in 0..9) {
                if (_field[r][c] == CellState.SHIP) return true
            }
        }
        return false
    }

    // 4. Поставить целый корабль
    fun placeShip(startRow: Int, startCol: Int, size: Int, horizontal: Boolean): Boolean {
        if (!canPlaceShip(startRow, startCol, size, horizontal)) {
            return false
        }
        for (i in 0 until size) {
            val r = if (horizontal) startRow else startRow + i
            val c = if (horizontal) startCol + i else startCol
            _field[r][c] = CellState.SHIP
        }
        return true
    }

    // 5. Выстрел по полю этого игрока
    fun shoot(row: Int, col: Int): Boolean {
        if (!isInside(row, col)) {
            println("Выстрел за границей поля!")
            return false
        }

        val current = _field[row][col]
        if (current == CellState.HIT || current == CellState.MISS) {
            println("Сюда уже стреляли!")
            return false
        }

        val hit = (current == CellState.SHIP)
        _field[row][col] = if (hit) CellState.HIT else CellState.MISS

        registerShot(hit)
        return hit
    }
}