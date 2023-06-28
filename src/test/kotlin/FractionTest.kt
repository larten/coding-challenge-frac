import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FractionTest {

    @Test
    fun testFractionCreationFromExpressionWithWholeNumber() {
        val fraction = Fraction.createFromExpression(whole = 2)
        val expectedFraction = Fraction.createFromExpression(whole = 2)
        assertEquals(expectedFraction, fraction)
    }

    @Test
    fun testFractionCreationFromExpressionNumber() {
        val fraction = Fraction.createFromExpression(numerator = 1, denominator = 2)
        val expectedFraction = Fraction.createFromExpression(numerator = 1, denominator = 2)
        assertEquals(expectedFraction, fraction)
    }

    @Test
    fun testFractionEquality() {
        val fraction1 = Fraction.createFromExpression(numerator = 1, denominator = 2)
        val fraction2 = Fraction.createFromExpression(numerator = 2, denominator = 4)
        assertEquals(fraction1, fraction2)
    }

    @Test
    fun testFractionWithWholeEquality() {
        val fraction1 = Fraction.createFromExpression(whole = 2)
        val fraction2 = Fraction.createFromExpression(numerator = 4, denominator = 2)
        assertEquals(fraction1, fraction2)
    }

    @Test
    fun testFractionAddition() {
        val fraction1 = Fraction.createFromExpression(numerator = 1, denominator = 4)
        val fraction2 = Fraction.createFromExpression(numerator = 3, denominator = 4)
        val expected = Fraction.createFromExpression(numerator = 1, denominator = 1)
        val result = fraction1 + fraction2
        assertEquals(expected, result)
    }

    @Test
    fun testFractionSubtraction() {
        val fraction1 = Fraction.createFromExpression(numerator = 5, denominator = 8)
        val fraction2 = Fraction.createFromExpression(numerator = 1, denominator = 8)
        val expected = Fraction.createFromExpression(numerator = 1, denominator = 2)
        val result = fraction1 - fraction2
        assertEquals(expected, result)
    }

    @Test
    fun testFractionMultiplication() {
        val fraction1 = Fraction.createFromExpression(numerator = 3, denominator = 5)
        val fraction2 = Fraction.createFromExpression(numerator = 2, denominator = 3)
        val expected = Fraction.createFromExpression(numerator = 2, denominator = 5)
        val result = fraction1 * fraction2
        assertEquals(expected, result)
    }

    @Test
    fun testFractionDivision() {
        val fraction1 = Fraction.createFromExpression(numerator = 2, denominator = 3)
        val fraction2 = Fraction.createFromExpression(numerator = 3, denominator = 4)
        val expected = Fraction.createFromExpression(numerator = 8, denominator = 9)
        val result = fraction1 / fraction2
        assertEquals(expected, result)
    }

    @Test
    fun testProvidedSample1() {
        val fraction1 = Fraction.createFromExpression(numerator = 1, denominator = 2)
        val fraction2 = Fraction.createFromExpression(whole = 3, numerator = 3, denominator = 4)
        val expected = Fraction.createFromExpression(whole = 1, numerator = 7, denominator = 8)
        val result = fraction1 * fraction2
        assertEquals(expected, result)
    }

    @Test
    fun testProvidedSample2() {
        val fraction1 = Fraction.createFromExpression(whole = 2, numerator = 3, denominator = 8)
        val fraction2 = Fraction.createFromExpression(numerator = 9, denominator = 8)
        val expected = Fraction.createFromExpression(whole = 3, numerator = 1, denominator = 2)
        val result = fraction1 + fraction2
        assertEquals(expected, result)
    }

    @Test
    fun testProvidedSample3() {
        val fraction1 = Fraction.createFromExpression(whole = 1, numerator = 3, denominator = 4)
        val fraction2 = Fraction.createFromExpression(whole = 2)
        val expected = Fraction.createFromExpression(numerator = -1, denominator = 4)
        val result = fraction1 - fraction2
        assertEquals(expected, result)
    }

    @Test
    fun testTripleFractionOperator() {
        val fraction1 = Fraction.createFromExpression(numerator = 1, denominator = 2)
        val fraction2 = Fraction.createFromExpression(whole = 3, numerator = 3, denominator = 4)
        val fraction3 = Fraction.createFromExpression(whole = 1, numerator = 1, denominator = 3)
        val expected = Fraction.createFromExpression(whole = 5, numerator = 7, denominator = 12)
        val result = fraction1 + fraction2 + fraction3
        assertEquals(expected, result)
    }

    @Test
    fun testTripleFractionOperatorOrder() {
        val fraction1 = Fraction.createFromExpression(numerator = 1, denominator = 2)
        val fraction2 = Fraction.createFromExpression(whole = 3, numerator = 3, denominator = 4)
        val fraction3 = Fraction.createFromExpression(whole = 1, numerator = 1, denominator = 3)
        val expected = Fraction.createFromExpression(whole = 5, numerator = 6, denominator = 12)
        val result = fraction1 + fraction2 * fraction3
        assertEquals(expected, result)
    }

    @Test
    fun testTripleFractionOperatorReversedOrder() {
        val fraction1 = Fraction.createFromExpression(numerator = 1, denominator = 2)
        val fraction2 = Fraction.createFromExpression(whole = 3, numerator = 3, denominator = 4)
        val fraction3 = Fraction.createFromExpression(whole = 1, numerator = 1, denominator = 3)
        val expected = Fraction.createFromExpression(whole = 3, numerator = 5, denominator = 24)
        val result = fraction1 * fraction2 + fraction3
        assertEquals(expected, result)
    }

    @Test
    fun testFractionToString() {
        val fraction = Fraction.createFromExpression(numerator = 3, denominator = 4)
        val expectedString = "3/4"
        assertEquals(expectedString, fraction.toString())
    }

    @Test
    fun testFractionWithWholeToString() {
        val fraction = Fraction.createFromExpression(whole = 2, numerator = 3, denominator = 4)
        val expectedString = "2&3/4"
        assertEquals(expectedString, fraction.toString())
    }

    @Test
    fun testNegativeFractionWithWholeToString() {
        val fraction = Fraction.createFromExpression(whole = -2, numerator = 3, denominator = 4)
        val expectedString = "-2&3/4"
        assertEquals(expectedString, fraction.toString())
    }
}