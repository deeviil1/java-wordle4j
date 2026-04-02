package ru.yandex.practicum;


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

        public WordleDictionary(List<String> words) {
            this.words = new ArrayList<>(words);
            filterFiveLetterWords();
        }

        private void filterFiveLetterWords() {
            words.removeIf(word -> word.length() != 5);
        }


        public String getRandomWord() {
            if (words.isEmpty()) {
                throw new RuntimeException("Словарь пуст, нет слов для выбора!");
            }
            Random random = new Random();
            return words.get(random.nextInt(words.size()));
        }

        public boolean containsWord(String word) {
            return words.contains(word.toLowerCase());
        }

        public int getSize() {
            return words.size();
        }

        public List<String> getAllWords() {
            return new ArrayList<>(words);
        }

        public List<String> filterWordsByHints(String inputWord, String hint) {
            List<String> filteredWords = new ArrayList<>();

            for (String word : words) {
                boolean matches = true;

                for (int i = 0; i < hint.length(); i++) {
                    char hintChar = hint.charAt(i);
                    char inputChar = inputWord.charAt(i);
                    char wordChar = word.charAt(i);

                    if (hintChar == '+') {
                        if (wordChar != inputChar) {
                            matches = false;
                            break;
                        }
                    } else if (hintChar == '^') {
                        if (!word.contains(String.valueOf(inputChar))) {
                            matches = false;
                            break;
                        }
                        if (word.charAt(i) == inputChar) {
                            matches = false;
                            break;
                        }
                    } else if (hintChar == '-') {
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


