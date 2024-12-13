import kotlin.random.Random

fun getRandomHexColor(): String {
    val hexChars = "0123456789ABCDEF"
    val hexColor = "#${(1..6).map { hexChars[Random.nextInt(hexChars.length)] }.joinToString("")}"
    return hexColor
}