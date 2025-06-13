package ru.netology
fun main(){

}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun calculateCommission(
    cardType: String = "VK Pay",
    previousTransfers: Int = 0,
    transferAmount: Int
): Int {
    // Проверка лимитов для VK Pay
    if (cardType == "VK Pay") {
        if (transferAmount > 15_000) return -1
        if (previousTransfers + transferAmount > 40_000) return -1
        return 0
    }

    // Проверка лимитов для банковских карт
    if (transferAmount > 150_000) return -1
    if (previousTransfers + transferAmount > 600_000) return -1

    return when (cardType) {
        "Mastercard", "Maestro" -> {
            if (transferAmount >= 300 && previousTransfers + transferAmount <= 75_000) {
                0
            } else {
                (transferAmount * 0.006).toInt() + 20
            }
        }
        "Visa", "Мир" -> {
            val commission = (transferAmount * 0.0075).toInt()
            if (commission < 35) 35 else commission
        }
        else -> 0
    }
}