import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ValidationTest {
    @ParameterizedTest(name = "Expression: {0}, Expected: {1}")
    @CsvSource(
        "1/2 * 3&3/4, true",
        "2&3/8 + 9/8, true",
        "1&3/4 - 2, true",
        "1&3/4 + -2, true",
        "2 * 1&3/4, true",
        "-2 * 1&3/4, true",
        "8/3 + 8/9, true",
        "1&3/4, true",
        "-1&3/4, true",
        "1&-3/4, false",
        "1&3/-4, false",
        "-1&-3/4, false",
        "1&-3/-4, false",
        "-1&3/-4, false",
        "-1&-3/-4, false",
        "1/2, true",
        "-1/2, true",
        "1/-2, false",
        "-1/-2, false",
        "2, true",
        "-2, true",
        "1/2 * 3&3/4 + 9/8, true",
        "1/2 ** 3&3/4, false",
        "1/2 * 3&3 /4, false",
        "a, false",
        "a + b, false",
        "1/a + * 3&3/4, false",
        "1/a +, false"
    )
    fun isValidExpression(expression: String, expected: Boolean) {
        val result = FractionalCalculator(expression).isValidExpression()
        assertEquals(expected, result)
    }

    @Test
    fun testEmpty() {
        val expression = ""
        val expected = false
        val result = FractionalCalculator(expression).isValidExpression()
        assertEquals(expected, result)
    }

    @Test
    fun testSpace() {
        val expression = " "
        val expected = false
        val result = FractionalCalculator(expression).isValidExpression()
        assertEquals(expected, result)
    }
}
