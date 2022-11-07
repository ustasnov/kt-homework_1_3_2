import kotlin.math.max

fun calculateCommission(cardType: String, amountOfPreviousTransfers: Int = 0, amount: Int): Int {
    val actionLimit = 75_000
    val minCommission = 35
    val maestroCommissionPercentage: Float = 0.6f
    val visaCommissionPercentage: Float = 0.75f
    val totalAmount = amountOfPreviousTransfers + amount;
    val result = when (cardType) {
        "Mastercard", "Maestro" -> if (totalAmount > actionLimit) (amount * maestroCommissionPercentage / 100).toInt() + 20 else 0
        "Visa", "Мир" -> max((amount * visaCommissionPercentage / 100).toInt(), minCommission)
        else -> 0
    }
    return result
}

fun printCalculation(cardType: String, amountOfPreviousTransfers: Int = 0, amount: Int) {
    println(
        "Сумма перевода: $amount руб. Тип карты: $cardType Сумма предыдущих переводов: $amountOfPreviousTransfers руб. Комиссия: ${
            calculateCommission(
                cardType,
                amountOfPreviousTransfers,
                amount
            )
        } руб."
    )
}

fun main() {
    printCalculation("Mastercard", 0, 50_000)
    printCalculation("Mastercard", 0, 90_000)
    printCalculation("Maestro", 30_000, 50_000)
    printCalculation("Visa", 0, 50_000)
    printCalculation("Мир", 30_000, 50_000)
    printCalculation("Мир", 0, 100)
    printCalculation("VK Pay", 10_000, 12_000)
}