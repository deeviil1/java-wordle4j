package ru.yandex.practicum;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class WordleTest {
    @Nested
    class WordleGameTest {
        private WordleGame game;

        @BeforeEach
        public void setUp() {
            List<String> words = List.of("абрек", "арбуз", "абрис");
            WordleDictionary dictionary = new WordleDictionary(words);
            game = new WordleGame(dictionary);
        }

        @Test
        public void testMakeGuess_ExactMatch() throws WordNotFoundInDictionaryException {
            String guess = game.getAnswer();
            String hint = game.makeGuess(guess);
            assertEquals("+++++", hint);
        }

        @Test
        public void testMakeGuess_PartialMatch() throws WordNotFoundInDictionaryException {
            if ("абрек".equals(game.getAnswer())) {
                String hint = game.makeGuess("абрис");
                assertEquals("+++--", hint);
            }
        }

        @Test
        void testWordGuessed_AfterCorrectGuess() throws WordNotFoundInDictionaryException {
            String answer = game.getAnswer();
            System.out.println("Загаданное слово: " + answer);
            game.makeGuess(answer);
            System.out.println("Угадали? " + game.isWordGuessed());
            assertTrue(game.isWordGuessed());
        }

        @Test
        void testRemainingAttempts_DecreasesCorrectly() throws WordNotFoundInDictionaryException {
            for (int i = 0; i < 5; i++) {
                game.makeGuess("абрек");
            }
            assertEquals(1, game.getSteps());
        }
    }

    @Nested
    class WordleDictionaryTest {
        @Test
        public void testContainsValidWord() {
            List<String> words = List.of("абрек", "арбуз", "абрис");
            WordleDictionary dict = new WordleDictionary(words);
            assertTrue(dict.containsWord("абрек"), "Ожидалось, что слово 'абрек' будет найдено");
            assertTrue(dict.containsWord("арбуз"), "Ожидалось, что слово 'арбуз' будет найдено");
        }

        @Test
        public void testDoesNotContainInvalidWord() {
            List<String> words = List.of("абрек", "арбуз", "абрис");
            WordleDictionary dict = new WordleDictionary(words);
            assertFalse(dict.containsWord("кот"), "Ожидалось, что слова 'кот' не будет в словаре");
            assertFalse(dict.containsWord("привет"), "Ожидалось, что слова 'привет' не будет в словаре");
        }

        @Test
        public void testGetRandomWord() {
            List<String> words = List.of("абрек", "арбуз", "абрис");
            WordleDictionary dict = new WordleDictionary(words);

            String randomWord = dict.getRandomWord();
            assertTrue(List.of("абрек", "арбуз", "абрис").contains(randomWord),
                    "Случайное слово должно быть одним из заданных в списке");
        }
    }
}

