package ru.yandex.practicum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

        private List<String> words;

        // Конструктор, принимающий список слов
        public WordleDictionary(List<String> words) {
            this.words = new ArrayList<>(words);
            // Фильтруем слова, оставляем только 5-буквенные
            filterFiveLetterWords();
        }

        // Метод для фильтрации слов: оставляем только слова из 5 букв
        private void filterFiveLetterWords() {
            words.removeIf(word -> word.length() != 5);
        }

        // Получаем случайное слово из словаря (для загадывания в игре)
        public String getRandomWord() {
            if (words.isEmpty()) {
                throw new RuntimeException("Словарь пуст, нет слов для выбора!");
            }
            Random random = new Random();
            return words.get(random.nextInt(words.size()));
        }

        // Проверяем, есть ли слово в словаре
        public boolean containsWord(String word) {
            return words.contains(word.toLowerCase());
        }

        // Возвращаем размер словаря
        public int getSize() {
            return words.size();
        }

        // Получаем весь список слов (может пригодиться для подсказок)
        public List<String> getAllWords() {
            return new ArrayList<>(words); // Возвращаем копию списка
        }

        // Фильтрация слов по уже известным буквам и их позициям
        public List<String> filterWordsByHints(String inputWord, String hint) {
            List<String> filteredWords = new ArrayList<>();

            for (String word : words) {
                boolean matches = true;

                for (int i = 0; i < hint.length(); i++) {
                    char hintChar = hint.charAt(i);
                    char inputChar = inputWord.charAt(i);
                    char wordChar = word.charAt(i);

                    if (hintChar == '+') {
                        // Буква на правильной позиции
                        if (wordChar != inputChar) {
                            matches = false;
                            break;
                        }
                    } else if (hintChar == '^') {
                        // Буква есть, но не на той позиции
                        if (!word.contains(String.valueOf(inputChar))) {
                            matches = false;
                            break;
                        }
                        // Дополнительно проверяем, что буква не стоит на правильной позиции (иначе будет "+")
                        if (word.charAt(i) == inputChar) {
                            matches = false;
                            break;
                        }
                    } else if (hintChar == '-') {
                        // Буквы нет в слове вообще
                        if (word.contains(String.valueOf(inputChar))) {
                            matches = false;
                            break;
                        }
                    }
                }

                if (matches) {
                    filteredWords.add(word);
                }
            }

            return filteredWords;
        }
    }


