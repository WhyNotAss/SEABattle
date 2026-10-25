enum class Difficulty {
    EASY,
    NORMAL,
    HARD;

    fun describe(): String = when(this) {
        EASY -> "Лёгкий: ИИ стреляет случайно"
        NORMAL -> "Средний: ИИ добивает корабль после попадания"
        HARD -> "Сложный: ИИ стреляет с использованием теории вероятности и учитывает размеры кораблей"
    }
}