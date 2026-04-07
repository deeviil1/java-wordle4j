package ru.yandex.practicum;


import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {
public static void main(String[] args) throws Exception {

        PrintWriter log = new PrintWriter("log.txt");


        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        List<String> dictionaryWords = loader.loadDictionary("words_ru.txt");
        WordleDictionary dictionary = new WordleDictionary(dictionaryWords);


        WordleGame game = new WordleGame(dictionary);

        while (!game.isWordGuessed() && !game.areAttemptsExhausted()) {
            try {

                String guess = getUserInput();


                String hint = game.makeGuess(guess);
                System.out.println("Подсказка: " + hint);
            } catch (WordNotFoundInDictionaryException e) {
                System.out.println(e.getMessage());
            }
        }

        if (game.isWordGuessed()) {
            System.out.println("Вы выиграли! Загаданное слово было: " + game.getAnswer());
        } else {
            System.out.println("Попытки закончились! Загаданное слово было: " + game.getAnswer());
        }

        log.close();
    }


    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите слово из 5 букв: ");
        return scanner.nextLine();
    }
}
