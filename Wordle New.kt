#!/usr/bin/env kotlin

val MAX_ATTEMPTS = 6
val WRONG = '_'
val CORRECT = '+'
val SWAPPED = '#'


fun main() {
    val secret = words.random()

    println(
        "Adivinhar uma palavra com ${secret.length} letras " +
            "em $MAX_ATTEMPTS tentativas."
    )

    for (attempts in 1..MAX_ATTEMPTS) {
        println("${attempts}ª tentativa")

        val guess = readGuess(secret.length)
        val result = getResult(secret, guess)

        println(guess)
        println(result)

        if (allCorrect(result)) {
            println("YOU WIN -> Word=$secret")
            return
        }
    }

    println("YOU LOSE -> Word=$secret")
}

val words = listOf(
    "ARRAY", "CICLO", "DADOS", "DEBUG", "ERROS", "FLUXO", "LISTA", "PILHA",
    "TEXTO", "VALOR", "VETOR", "CHAVE", "CAMPO", "FONTE", "FORMA", "LINHA",
    "MUNDO", "PORTA", "PRAIA", "LIVRO", "CASAR", "PEDRA", "NUVEM", "SONHO",
    "TEMPO", "VERDE", "BANCO", "BEBER", "BRAVO", "CARRO", "COISA", "FOLHA",
    "GENTE", "GRUPO", "JOVEM", "MASSA", "NOITE", "PAPEL", "RAPAZ", "TERRA"
)

/**
 * Verifica se a string indicada não contém caracteres repetidos.
 */
fun noRepeats(s: String): Boolean =
    s.toSet().size == s.length

/**
 * Verifica se todos os caracteres do resultado são CORRECT.
 */
fun allCorrect(result: String): Boolean =
    result.all { it == CORRECT }

/**
 * Lê uma palavra válida, convertida para maiúsculas, sem usar 'var'.
 */
fun readGuess(length: Int): String {
    print("Palavra: ")
    val guess = readln().uppercase()

    if (guess.length == length && guess.all { it in 'A'..'Z' }) {
        return guess
    }

    println("Palavra inválida.")
    return readGuess(length)
}

/**
 * Obtém o resultado usando apenas valores imutáveis.
 */
fun getResult(secret: String, guess: String): String {
    val exactMatches = guess.indices
        .filter { i -> guess[i] == secret[i] }
        .toSet()

    val remainingSecret = guess.indices
        .filter { i -> i !in exactMatches }
        .map { i -> secret[i] }

    return guess.indices.map { i ->
        val totalInSecret = remainingSecret.count { it == guess[i] }
        val usedSoFar = (0 until i).count { j ->
            j !in exactMatches && guess[j] == guess[i]
        }

        when {
            i in exactMatches -> CORRECT
            usedSoFar < totalInSecret -> SWAPPED
            else -> WRONG
        }
    }.joinToString("")
}