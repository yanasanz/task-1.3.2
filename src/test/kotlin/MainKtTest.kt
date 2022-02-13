import org.junit.Assert.*
import org.junit.Test

internal class MainKtTest {

    @Test
    fun countFee_if_cardType_is_Mastercard_or_Maestro_while_transfer_is_under_7500000_and_monthLimit_is_not_over() {
        val newTransfer = 5000000
        val card = "Mastercard"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(0,result)
    }

    @Test
    fun countFee_if_cardType_is_Mastercard_or_Maestro_while_transfer_is_between_7500001_and_transferLimit_and_monthLimit_is_not_over() {
        val newTransfer = 10000000
        val card = "Maestro"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(62000,result)
    }

    @Test
    fun countFee_if_cardType_is_Mastercard_or_Maestro_while_monthLimit_is_over() {
        val newTransfer = 5000000
        val card = "Mastercard"
        val previousSum = 68000000

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(-1,result)
    }

    @Test
    fun countFee_if_cardType_is_Mastercard_or_Maestro_and_transferLimit_is_over() {
        val newTransfer = 20000000
        val card = "Maestro"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(-2,result)
    }

    @Test
    fun countFee_if_cardType_is_Visa_or_Mir_while_transfer_is_under_466667_and_monthLimit_is_not_over() {
        val newTransfer = 400000
        val card = "Visa"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(3500,result)
    }

    @Test
    fun countFee_if_cardType_is_Visa_or_Mir_while_transfer_is_between_466668_and_transferLimit_and_monthLimit_is_not_over() {
        val newTransfer = 10000000
        val card = "Мир"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(75000,result)
    }

    @Test
    fun countFee_if_cardType_is_Visa_or_Mir_and_monthLimit_is_over() {
        val newTransfer = 5000000
        val card = "Visa"
        val previousSum = 68000000

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(-1,result)
    }

    @Test
    fun countFee_if_cardType_is_Visa_or_Mir_and_transferLimit_is_over() {
        val newTransfer = 20000000
        val card = "Мир"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(-2,result)
    }

    @Test
    fun countFee_if_cardType_is_VKPay_while_transferLimit_and_monthLimit_are_not_over() {
        val newTransfer = 200000
        val card = "VK Pay"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(0,result)
    }

    @Test
    fun countFee_if_cardType_is_VKPay_and_monthLimit_is_over() {
        val newTransfer = 200000
        val card = "VK Pay"
        val previousSum = 4000000

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(-1,result)
    }

    @Test
    fun countFee_if_cardType_is_VKPay_and_transferLimit_is_over() {
        val newTransfer = 1600000
        val card = "VK Pay"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(-2,result)
    }

    @Test
    fun countFee_if_cardType_is_wrong() {
        val newTransfer = 1600000
        val card = "Wrong type"
        val previousSum = 0

        val result = countFee(
            currentTransfer = newTransfer,
            cardType = card,
            previousTransfers = previousSum
        )

        assertEquals(-3,result)
    }
}