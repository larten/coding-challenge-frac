import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    while (true) {
        print("Expression: ")
        val userInput = scanner.nextLine().trim()
        if (userInput.equals("exit", ignoreCase = true)) {
            break
        }

        try {
            val result = FractionalCalculator(userInput).evaluateExpression()
            println("Result: $result")
        } catch (e: Exception) {
            println(e.message ?: "Unknown error")
        }
        println()
    }
}
