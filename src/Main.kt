fun main() {
    val player = Player()
    player.name = "Вы"

    player.registerShot(true)    // попал
    player.registerShot(false)   // промах
    player.registerShot(true)    // попал

    println(player.status())
    // Игрок Вы: палуб осталось 20, выстрелов 3, попаданий 2, точность 66.7%

    player.takeDamage()
    player.takeDamage()
    println(player.shipsLeft)    // 18
    println(player.isAlive())    // true

    player.reset()
    println(player.status())
    // Игрок Вы: палуб осталось 20, выстрелов 0, попаданий 0, точность 0.0%

    // Второй игрок с другой статистикой
    val enemy = Player()
    enemy.name = "Компьютер"
    enemy.shots = 12
    enemy.hits = 7
    enemy.shipsLeft = 15
    println(enemy.status())
    // Игрок Компьютер: палуб осталось 15, выстрелов 12, попаданий 7, точность 58.3%

    // Проверка, что takeDamage возвращает false, когда игрок убит
    enemy.shipsLeft = 1
    println(enemy.takeDamage())  // false
    println(enemy.isAlive())     // false
}
