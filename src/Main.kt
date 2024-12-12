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

        if (input == null || input.length != 1) { // Проверка на null или больше одного символа
            println("Ошибка ввода! Вы должны ввести одну букву")
            continue
        }

        val latter = input.get(0) // Под вопросом правильность этого метода

        if (latter.lowercaseChar() !in latters) { // проверка на русский алфавит, сделал в lowcase, если будут вводить caps lock
            println("Буквы должны быть русские")
            continue
        }

        if (latter !in enteredLatters) { //проверяю на введенные буквы
            enteredLatters.add(latter)
        } else {
            println("Вы уже вводили данную букву!")
            continue
        }

        if (latter in guessWord) { // проверка наличие буквы в слове
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

