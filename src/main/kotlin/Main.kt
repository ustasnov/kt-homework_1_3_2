import kotlin.math.max

fun calculateCommission(amount: Int, cardType: String = "VK Pay", amountOfPreviousTransfers: Int = 0): Int {
    val actionLimit = 75_000
    val minCommission = 35
    val totalAmount = amountOfPreviousTransfers + amount;
    val result = when (cardType) {
        "Mastercard", "Maestro" -> if (totalAmount > actionLimit) (amount * 0.006).toInt() + 20 else 0
        "Visa", "Мир" -> max((amount * 0.0075).toInt(), minCommission)
        else -> 0
    }
    return result
}

fun printCalculation(amount: Int, cardType: String = "VK Pay", amountOfPreviousTransfers: Int = 0) {
    println(
        "Сумма перевода: $amount руб. Тип карты: $cardType Сумма предыдущих переводов: $amountOfPreviousTransfers руб. Комиссия: ${
            calculateCommission(
                amount,
                cardType,
                amountOfPreviousTransfers
            )
        } руб."
    )
}

fun main() {
    printCalculation(50_000, "Mastercard")
    printCalculation(90_000, "Mastercard")
    printCalculation(50_000, "Maestro", 30_000)
    printCalculation(50_000, "Visa")
    printCalculation(50_000, "Мир", 30_000)
    printCalculation(100, "Мир")
    printCalculation(10_000)
}