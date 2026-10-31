fun isGameOver(player: Player, enemy: Player): Boolean {
    return !player.hasShips() || !enemy.hasShips()
}

fun main() {
    val player = Player("Вы")
    val enemy = Player("Компьютер")

    printRules()

    println("=== РАССТАНОВКА ВАШИХ КОРАБЛЕЙ ===")
    placePlayerShips(player)

    println()
    println("=== КОМПЬЮТЕР РАССТАНОВЛЯЕТ КОРАБЛИ ===")
    placeEnemyShips(enemy)

    println()
    println("=== ИГРА НАЧАЛАСЬ ===")

    val ai = EnemyAI(player)
    var playerTurn = true

    while (!isGameOver(player, enemy)) {
        printBothFields(player, enemy)
        println()

        if (playerTurn) {
            println("Ваш ход. Введите координаты выстрела (row col):")
            val input = readln().trim().split(" ")
            if (input.size != 2) {
                println("Ошибка: нужно два числа через пробел.")
                continue
            }
            val r = input[0].toIntOrNull()
            val c = input[1].toIntOrNull()
            if (r == null || c == null) {
                println("Ошибка: координаты должны быть числами.")
                continue
            }
            val hit = enemy.shoot(r, c)
            if (hit) {
                println("Попадание!")
            } else {
                println("Промах.")
                playerTurn = false
            }
        } else {
            println("Ход компьютера...")
            val (r, c) = ai.chooseShot()
            val hit = player.shoot(r, c)
            ai.onShotResult(r, c, hit)
            if (hit) {
                println("Компьютер попал в ($r, $c)!")
            } else {
                println("Компьютер промахнулся в ($r, $c).")
                playerTurn = true
            }
        }
        println()
    }

    if (enemy.hasShips()) {
        println("Победил компьютер!")
    } else {
        println("Вы победили!")
    }
}
