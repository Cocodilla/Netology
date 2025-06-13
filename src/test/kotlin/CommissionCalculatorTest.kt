
import org.junit.Test
import org.junit.Assert.assertEquals
import ru.netology.calculateCommission

class CommissionCalculatorTest {

    @Test
    fun `Mastercard в рамках акции`() {
        val result = calculateCommission(
            cardType = "Mastercard",
            previousTransfers = 50_000,
            transferAmount = 20_000
        )
        assertEquals(0, result)
    }

    @Test
    fun `Mastercard превышение лимита акции`() {
        val result = calculateCommission(
            cardType = "Mastercard",
            previousTransfers = 70_000,
            transferAmount = 10_000
        )
        assertEquals((10_000 * 0.006).toInt() + 20, result)
    }

    @Test
    fun `Visa минимальная комиссия`() {
        val result = calculateCommission(
            cardType = "Visa",
            previousTransfers = 0,
            transferAmount = 1000
        )
        assertEquals(35, result)
    }

    @Test
    fun `Мир обычная комиссия`() {
        val result = calculateCommission(
            cardType = "Мир",
            previousTransfers = 0,
            transferAmount = 10_000
        )
        assertEquals(75, result) // 10_000 * 0.0075 = 75
    }

    @Test
    fun `VK Pay без комиссии`() {
        val result = calculateCommission(
            cardType = "VK Pay",
            previousTransfers = 30_000,
            transferAmount = 5_000
        )
        assertEquals(0, result)
    }

    @Test
    fun `Превышение суточного лимита карты`() {
        val result = calculateCommission(
            cardType = "Visa",
            previousTransfers = 0,
            transferAmount = 160_000
        )
        assertEquals(-1, result)
    }

    @Test
    fun `Превышение месячного лимита карты`() {
        val result = calculateCommission(
            cardType = "Mastercard",
            previousTransfers = 550_000,
            transferAmount = 60_000
        )
        assertEquals(-1, result)
    }

    @Test
    fun `Превышение разового лимита VK Pay`() {
        val result = calculateCommission(
            cardType = "VK Pay",
            previousTransfers = 0,
            transferAmount = 20_000
        )
        assertEquals(-1, result)
    }

    @Test
    fun `Превышение месячного лимита VK Pay`() {
        val result = calculateCommission(
            cardType = "VK Pay",
            previousTransfers = 35_000,
            transferAmount = 10_000
        )
        assertEquals(-1, result)
    }

    @Test
    fun `Mastercard перевод менее 300 рублей`() {
        val result = calculateCommission(
            cardType = "Mastercard",
            previousTransfers = 0,
            transferAmount = 200
        )
        assertEquals((200 * 0.006).toInt() + 20, result)
    }

    @Test
    fun `Maestro в рамках акции`() {
        val result = calculateCommission(
            cardType = "Maestro",
            previousTransfers = 50_000,
            transferAmount = 20_000
        )
        assertEquals(0, result)
    }

    @Test
    fun `Граничное условие Mastercard ровно 75000`() {
        val result = calculateCommission(
            cardType = "Mastercard",
            previousTransfers = 74_000,
            transferAmount = 1_000
        )
        assertEquals(0, result)
    }

    @Test
    fun `Граничное условие VK Pay ровно 15000`() {
        val result = calculateCommission(
            cardType = "VK Pay",
            previousTransfers = 0,
            transferAmount = 15_000
        )
        assertEquals(0, result)
    }
}