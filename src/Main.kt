fun printBothFields(player: Player, enemy: Player, showEnemyShips: Boolean = false) {
    println("=== Поле игрока ${player.name} ===        === Поле игрока ${enemy.name} ===")
    print("   ")
    for (c in 0..9) print("$c ")
    print("      ")
    print("   ")
    for (c in 0..9) print("$c ")
    println()
    for (r in 0..9) {
        print("$r  ")
        for (c in 0..9) print("${player.field()[r][c].toChar()} ")
        print("      ")
        print("$r  ")
        for (c in 0..9) {
            print("${enemy.field()[r][c].toChar()}")
        }
        println()
    }
}

fun main() {
    for (level in Difficulty.entries) {
        println("${level.name}: ${level.describe()}")
    }
    println()
    for (type in ShipType.entries) {
        println("${type.name}: размер ${type.size}")
    }
    println()
    val player = Player("Вы")
    val enemy = Player("Компьютер")

    player.placeShip(3, 2)
    player.placeShip(3, 3)
    player.placeShip(3, 4)
    player.placeShip(3, 5)

    enemy.placeShip(5, 7)
    enemy.placeShip(6, 7)
    enemy.placeShip(7, 7)

    player.markShot(3, 3, true)
    player.markShot(4, 4, false)

    printBothFields(player, enemy)
    println()

    player.registerShot(true)
    player.registerShot(false)
    player.registerShot(true)

    println(player.status())
    // Игрок Вы: палуб осталось 20, выстрелов 3, попаданий 2, точность 66.7%

    println("Выстрелов: ${player.shots}")
    println("Попаданий: ${player.hits}")

    player.takeDamage()
    player.takeDamage()
    println(player.shipsLeft)    // 18
    println(player.isAlive())    // true

    player.reset()
    println(player.status())
}
