package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private String targetWord; // Загаданное слово
    private int remainingAttempts; // Оставшиеся попытки
    private List<String> previousGuesses; // Предыдущие догадки игрока
    private WordleDictionary dictionary; // Словарь слов для игры

    // Конструктор
    public WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        this.targetWord = dictionary.getRandomWord();
        this.remainingAttempts = 6;
        this.previousGuesses = new ArrayList<>();
    }

    public String getTargetWord(){
        return targetWord;
    }


    public int getRemainingAttempts() {
        return remainingAttempts;
    }


    public boolean isWordGuessed() {
        return previousGuesses.contains(targetWord);
    }


    public boolean areAttemptsExhausted() {
        return remainingAttempts <= 0;
    }


    public String makeGuess(String guess) throws WordNotFoundInDictionaryException {

        guess = guess.toLowerCase().replace('ё', 'е');

        if (guess.length() != 5) {
            throw new WordNotFoundInDictionaryException("Слово должно состоять из 5 букв!");
        }
        if (!dictionary.containsWord(guess)) {
            throw new WordNotFoundInDictionaryException("Слово не найдено в словаре!");
        }

        remainingAttempts--;

        previousGuesses.add(guess);

        return generateHint(guess);
    }


    private String generateHint(String guess) {
        StringBuilder hint = new StringBuilder();

        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char targetChar = targetWord.charAt(i);

            if (guessChar == targetChar) {
                hint.append('+');
            } else if (targetWord.contains(String.valueOf(guessChar))) {
                hint.append('^');
            } else {
                hint.append('-');
            }
        }

        return hint.toString();
    }
}











