const val MAX_ATTEMPTS = 6
const val WRONG = '_'
const val CORRECT = '+'
const val SWAPPED = '#'

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
 * Verifica se a string indicada não contém caracteres repetidos
 * @param s A string a verificar
 * @return true se não houver caracteres repetidos, false caso contrário
 */
fun noRepeats(s: String): Boolean =
    s.distinct().length == s.length

/**
 * Verifica se todos os caracteres da string result são iguais a CORRECT
 * @param result A string a verificar
 * @return true se todos os caracteres forem iguais a CORRECT, false caso contrário
 */
fun allCorrect(result: String): Boolean =
    result.all { it == CORRECT }

/**
 * Lê uma palavra introduzida pelo utilizador convertida para maiúsculas.
 * Só retorna quando a palavra lida tem o comprimento correto
 * e só contém letras maiúsculas.
 * Sugere a introdução da palavra com a mensagem "Palavra: ".
 * Cada palavra não aceite provoca a mensagem "Palavra inválida."
 * @param length O comprimento da palavra a ler
 * @return A palavra lida em maiúsculas
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
 * Obtém o resultado da tentativa com base na palavra secreta e na tentativa do utilizador
 * @param secret A palavra secreta
 * @param guess A tentativa do utilizador
 * @return A string do resultado com os caracteres CORRECT, WRONG e SWAPPED
 * Exemplo: getResult("CARRO", "CRAVO") == "+##_+"
 */
fun getResult(secret: String, guess: String): String {
    val result = CharArray(guess.length) { WRONG }
    val used = BooleanArray(secret.length)

    for (i in guess.indices) {
        if (guess[i] == secret[i]) {
            result[i] = CORRECT
            used[i] = true
        }
    }

    for (i in guess.indices) {
        if (result[i] == WRONG) {
            for (j in secret.indices) {
                if (!used[j] && guess[i] == secret[j]) {
                    result[i] = SWAPPED
                    used[j] = true
                    break
                }
            }
        }
    }

    return String(result)
}

main()