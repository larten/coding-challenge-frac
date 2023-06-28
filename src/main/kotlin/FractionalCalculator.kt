class FractionalCalculator(
    private val expression: String
) {
    companion object {
        private val expressionRegex =
            Regex("""^\s*(-?\d+(?:&\d+)?/\d+|-?\d+)(?:\s+([-+*/])\s+(-?\d+(?:&\d+)?/\d+|-?\d+))*\s*$""")

        private val availableOperators = listOf(
            Operator(
                priority = 2,
                sign = "*",
                func = Fraction::times
            ),
            Operator(
                priority = 2,
                sign = "/",
                func = Fraction::div
            ),
            Operator(
                priority = 1,
                sign = "+",
                func = Fraction::plus
            ),
            Operator(
                priority = 1,
                sign = "-",
                func = Fraction::minus
            )
        ).associateBy { it.sign }
    }

    fun isValidExpression() = expressionRegex.matches(expression)

    fun evaluateExpression(): Fraction {
        if (!isValidExpression()) throw IllegalArgumentException("Invalid expression")

        val operandsAndOperators = expression.split("\\s+".toRegex())

        val expressionOperators = mutableListOf<Operator>()
        val expressionOperands = mutableListOf<Fraction>()

        for (item in operandsAndOperators) {
            if (item in availableOperators.keys) {
                expressionOperators.add(availableOperators[item]!!)
            } else {
                expressionOperands.add(parseFraction(item))
            }
        }

        return evaluateExpression(expressionOperators, expressionOperands)
    }

    private fun parseFraction(fractionStr: String): Fraction {
        return if ("/" in fractionStr) {
            val parts = fractionStr.split("/")
            val fraction = parts[0].split("&")
            val whole = if (fraction.size > 1) fraction[0].toInt() else 0
            val numerator = if (fraction.size > 1) fraction[1].toInt() else fraction[0].toInt()
            val denominator = parts[1].toInt()
            val sign = if (whole < 0) -1 else 1
            val result = whole * denominator + numerator

            Fraction.createFromExpression(numerator = sign * result, denominator = denominator)
        } else {
            Fraction.createFromExpression(whole = fractionStr.toInt())
        }
    }

    private tailrec fun evaluateExpression(operators: List<Operator>, operands: List<Fraction>): Fraction {
        if (operands.size == 1) {
            return operands[0]
        } else {
            val nextOperator: Operator = operators.maxBy { it.priority }
            val nextOperatorIndex: Int = operators.indexOf(nextOperator)

            val fractionLeftIndex = nextOperatorIndex
            val fractionLeft = operands[fractionLeftIndex]
            val fractionRightIndex = nextOperatorIndex + 1
            val fractionRight = operands[fractionRightIndex]

            val result = nextOperator.func.invoke(fractionLeft, fractionRight)

            return evaluateExpression(
                operators = operators.toMutableList()
                    .apply {
                        removeAt(nextOperatorIndex)
                    },
                operands = operands.toMutableList()
                    .apply {
                        removeAt(fractionRightIndex)
                        removeAt(fractionLeftIndex)
                        add(fractionLeftIndex, result)
                    }
            )
        }
    }
}