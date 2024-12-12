import kotlin.random.Random

fun main() {
    val words = listOf("вода", "телевизор") //Список слов
    val guessWord = words.random() //Произвольное загаданное слово
    val hiddenWord = CharArray(guessWord.length) { '*' } //Скрываем слово *
    val enteredLatters = mutableSetOf<Char>() // Список введенных букв
    var countError = 6 // Кол-во ходов
    val latters = ("абвгдеёжзийклмнопрстуфхцчщьъеюя")

    println("Доброе пожаловать в игру Висельник!")

    while (countError > 0 && hiddenWord.contains('*')) {  //Прописываю условия работы цикла игры
        println("Мы загадали слово ${hiddenWord.concatToString()}")
        println("Введенные буквы: ${if (enteredLatters.isEmpty()) "Нет" else enteredLatters.joinToString(", ")}")
        println("Количество попыток: $countError")
        println("Введите букву:")

        val input = readlnOrNull() // Не уверен в правильности этого метода чтения строки

        if (input == null || input.length != 1) {
            println("Ошибка ввода! Вы должны ввести одну букву")
            continue
        }

        val latter = input.get(0)

        if (latter.lowercaseChar() !in latters) {
            println("Буквы должны быть русские")
            continue
        }

        if (latter !in enteredLatters) {
            enteredLatters.add(latter)
        } else {
            println("Вы уже вводили данную букву!")
            continue
        }

        if (latter in guessWord) {
            print("Ура, вы угадали!")
            for (i in guessWord.indices) {
                if (guessWord[i] == latter) {
                    hiddenWord[i] = latter
                }
            }
        } else {
            println("Такой буквы нет, увы!")
            --countError
        }

        println()
        when (countError) {
            5 -> println(
                """
            |
            |
            |
            |
            
        """.trimIndent()
            )

            4 -> println(
                """
            /--------
            |
            |
            |
            |
            
        """.trimIndent()
            )

            3 -> println(
                """
            /--------
            |         |
            |         
            |       
            |    
                
        """.trimIndent()
            )

            2 -> println(
                """
            /--------
            |         |
            |         *
            |       
            |    
                
        """.trimIndent()
            )

            1 -> println(
                """
            /--------
            |         |
            |         *
            |       /   \
            |       
            
        """.trimIndent()
            )

            0 -> println(
                """
            /--------
            |         |
            |         *
            |       /   \
            |        / \
            
        """.trimIndent()
            )
        }
        println()
    }
    if (!hiddenWord.contains('*')) {
        println("Поздравляем! Вы угадали слово: ${hiddenWord.concatToString()}")
    } else {
        println("Вы проиграли! Загаданное слово было: $guessWord")
    }
}

