import java.util.Objects

class Fraction private constructor(
    private val numerator: Int,
    private val denominator: Int
) {
    companion object {
        fun createFromExpression(
            whole: Int = 0,
            numerator: Int,
            denominator: Int
        ): Fraction {
            require(denominator != 0) { "Denominator cannot be zero" }

            val newNumerator = if (whole < 0) {
                (whole * denominator) - numerator
            } else {
                (whole * denominator) + numerator
            }
            return simplifyFraction(Fraction(newNumerator, denominator))
        }

        fun createFromExpression(
            whole: Int
        ): Fraction {
            return simplifyFraction(Fraction(whole, 1))
        }

        private fun simplifyFraction(fraction: Fraction): Fraction {
            val greatestCommonDivisor = calculateGreatestCommonDivisor(fraction.numerator, fraction.denominator)
            val newNumerator = fraction.numerator / greatestCommonDivisor
            val newDenominator = fraction.denominator / greatestCommonDivisor

            return if (newDenominator < 0) {
                Fraction(-newNumerator, -newDenominator)
            } else {
                Fraction(newNumerator, newDenominator)
            }
        }

        private tailrec fun calculateGreatestCommonDivisor(a: Int, b: Int): Int {
            return if (b == 0) {
                a
            } else {
                calculateGreatestCommonDivisor(b, a % b)
            }
        }
    }

    operator fun plus(other: Fraction): Fraction {
        val commonDenominator = denominator * other.denominator
        val newNumerator = (numerator * other.denominator) + (other.numerator * denominator)
        return simplifyFraction(Fraction(newNumerator, commonDenominator))
    }

    operator fun minus(other: Fraction): Fraction {
        val commonDenominator = denominator * other.denominator
        val newNumerator = (numerator * other.denominator) - (other.numerator * denominator)
        return simplifyFraction(Fraction(newNumerator, commonDenominator))
    }

    operator fun times(other: Fraction): Fraction {
        val newNumerator = numerator * other.numerator
        val newDenominator = denominator * other.denominator
        return simplifyFraction(Fraction(newNumerator, newDenominator))
    }

    operator fun div(other: Fraction): Fraction {
        val newNumerator = numerator * other.denominator
        val newDenominator = denominator * other.numerator
        return simplifyFraction(Fraction(newNumerator, newDenominator))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false

        return this.numerator == other.numerator &&
                this.denominator == other.denominator
    }

    override fun hashCode(): Int {
        return Objects.hash(numerator, denominator)
    }

    override fun toString(): String {
        val whole = numerator / denominator
        val fractionNumerator = numerator % denominator
        return if (fractionNumerator == 0) {
            whole.toString()
        } else if (whole != 0) {
            if (whole < 0) {
                "$whole&${-fractionNumerator}/$denominator"
            } else {
                "$whole&$fractionNumerator/$denominator"
            }
        } else {
            "$fractionNumerator/$denominator"
        }
    }
}