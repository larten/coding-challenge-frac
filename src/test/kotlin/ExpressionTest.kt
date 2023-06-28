import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExpressionTest {
    @ParameterizedTest(name = "Expression: {0}, Expected: {1}")
    @CsvSource(
        "1/2 * 3&3/4, 1&7/8",
        "2&3/8 + 9/8, 3&1/2",
        "1&3/4 - 2, -1/4",
        "-2 + 3/4, -1&1/4",
        "1/2 + 3/4 + 1&1/3, 2&7/12",
        "1/2 + 3/4 * 1&1/3, 1&1/2",
        "1/2 * 3/4 + 1&1/3, 1&17/24",
        "1 + 2 + 3, 6",
        "1 + 2 * 3, 7",
        "1 * 2 + 3, 5",
        "7/3 + 4/3, 3&2/3"
    )
    fun testExpression(expression: String, expected: String) {
        val result = FractionalCalculator(expression).evaluateExpression().toString()
        assertEquals(expected, result)
    }
}