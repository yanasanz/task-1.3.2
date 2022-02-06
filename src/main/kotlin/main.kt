import kotlin.math.roundToInt

fun main() {
    printMessage(countFee(1000000))
    printMessage(countFee(8500000, "Mastercard", 50000000))
    printMessage(countFee(15500000, "Maestro", 30000000))
    printMessage(countFee(1000000, "Visa"))
    printMessage(countFee(7000000, "Мир", 55000000))
}

fun countFee(currentTransfer: Int, cardType: String = "VK Pay", previousTransfers: Int = 0): Int {
    return when (cardType) {
        "Mastercard", "Maestro" -> when {
            (currentTransfer < 7500000 && previousTransfers + currentTransfer < 60000000) -> 0
            (currentTransfer in 7500001..15000000 && previousTransfers + currentTransfer < 60000000) -> (currentTransfer * 0.006 + 2000).roundToInt()
            (previousTransfers + currentTransfer > 60000000) -> -1
            else -> -2
        }
        "Visa", "Мир" -> when {
            (currentTransfer < 466667 && previousTransfers + currentTransfer < 60000000) -> 3500
            (currentTransfer in 466668..15000000 && previousTransfers + currentTransfer < 60000000) -> (currentTransfer * 0.0075).roundToInt()
            (previousTransfers + currentTransfer > 60000000) -> -1
            else -> -2
        }
        "VK Pay" -> when {
            (currentTransfer < 1500000 && previousTransfers + currentTransfer < 4000000) -> 0
            (previousTransfers + currentTransfer > 4000000) -> -1
            else -> -2
        }
        else -> -3
    }
}

fun printMessage(fee: Int) {
    when {
        fee >= 0 -> println("Комиссия составит ${fee / 100} руб. ${fee % 100} коп.")
        fee == -1 -> println("Лимит на переводы в этом месяце будет превышен, попробуйте уменьшить сумму перевода")
        fee == -2 -> println("Сумма перевода выше максимальной, нужно уменьшить")
        else -> println("Платежная система введена неверно")
    }
}