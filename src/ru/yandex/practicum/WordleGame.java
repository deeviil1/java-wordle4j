package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.List;


/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private String answer; // Загаданное слово
    private int steps; // Оставшиеся попытки
    private List<String> previousGuesses; // Предыдущие догадки игрока
    private WordleDictionary dictionary; // Словарь слов для игры

    // Конструктор
    public WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        this.answer = dictionary.getRandomWord();
        this.steps = 6;
        this.previousGuesses = new ArrayList<>();
    }

    public String getAnswer(){
        return answer;
    }


    public int getSteps() {
        return steps;
    }


    public boolean isWordGuessed() {
        return previousGuesses.contains(answer);
    }


    public boolean areAttemptsExhausted() {
        return steps <= 0;
    }


    public String makeGuess(String guess) throws WordNotFoundInDictionaryException {

        guess = guess.toLowerCase().replace('ё', 'е');

        if (guess.length() != 5) {
            throw new WordNotFoundInDictionaryException("Слово должно состоять из 5 букв!");
        }
        if (!dictionary.containsWord(guess)) {
            throw new WordNotFoundInDictionaryException("Слово не найдено в словаре!");
        }

        steps--;

        previousGuesses.add(guess);

        return generateHint(guess);
    }


    private String generateHint(String guess) {
        StringBuilder hint = new StringBuilder();

        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char targetChar = answer.charAt(i);

            if (guessChar == targetChar) {
                hint.append('+');
            } else if (answer.contains(String.valueOf(guessChar))) {
                hint.append('^');
            } else {
                hint.append('-');
            }
        }

        return hint.toString();
    }
}











