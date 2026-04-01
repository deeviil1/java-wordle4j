package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.util.List;
public class WordleTest {

    @Test
    void testMainMethod() throws Exception {

        PrintWriter log = new PrintWriter(System.out);
        Wordle.main(new String[]{});
        log.flush();

    }

    @Nested
    class WordleGameTest {
        private WordleDictionary dictionary;
        private WordleGame game;

        @BeforeEach
        public void setUp() {
            List<String> words = List.of("вода", "привет");
            dictionary = new WordleDictionary(words);
            game = new WordleGame(dictionary);
        }

        @Test
        public void testMakeGuess_ExactMatch() throws WordNotFoundInDictionaryException {
            String guess = "вода";
            String hint = game.makeGuess(guess);
            assertEquals("+++++", hint); // Полное совпадение
        }

        @Test
        public void testMakeGuess_PartialMatch() throws WordNotFoundInDictionaryException {
            if ("вода".equals(game.getTargetWord())) {
                String hint = game.makeGuess("водао");
                assertEquals("+++--", hint);  // Пример частичного совпадения
            }
        }

        @Test
        void testWordGuessed_AfterCorrectGuess() throws WordNotFoundInDictionaryException {
            game.makeGuess("вода");
            assertTrue(game.isWordGuessed());
        }

        @Test
        void testRemainingAttempts_DecreasesCorrectly() throws WordNotFoundInDictionaryException {
            for (int i = 0; i < 5; i++) {
                game.makeGuess("привет");
            }
            assertEquals(1, game.getRemainingAttempts());
        }
    }

    @Nested
    class WordleDictionaryTest {
        private static WordleDictionary dict;

        @BeforeAll
        static void setUpClass() {
            List<String> words = List.of("вода", "привет");
            dict = new WordleDictionary(words);
        }

        @Test
        public void testFilterFiveLetterWords() {
            assertEquals(2, dict.getSize()); // Должны остаться только «вода» и «привет»
        }

        @Test
        public void testContainsValidWord() {
            assertTrue(dict.containsWord("вода"));
        }

        @Test
        public void testDoesNotContainInvalidWord() {
            assertFalse(dict.containsWord("кот"));
        }

        @Test
        public void testGetRandomWord() {
            String randomWord = dict.getRandomWord();
            assertTrue(List.of("вода", "привет").contains(randomWord));
        }
    }
}
