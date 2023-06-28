data class Operator(
    val priority: Int,
    val sign: String,
    val func: (Fraction, Fraction) -> Fraction,
)