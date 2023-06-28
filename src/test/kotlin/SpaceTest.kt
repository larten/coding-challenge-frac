import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SpaceTest {
    companion object {
        const val expected = "1&7/8"
    }

    @ParameterizedTest(name = "Expression: {0}")
    @CsvSource(
        "1/2 * 3&3/4",
        "  1/2 * 3&3/4",
        "  1/2   * 3&3/4",
        "  1/2   *   3&3/4",
        "  1/2   *   3&3/4  "
    )
    fun canEvaluateExpression(expression: String) {
        val result = FractionalCalculator(expression).evaluateExpression().toString()
        assertEquals(expected, result)
    }
}