package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.util.List;
public class WordleTest {
    WordleDictionaryLoader loader;
    List<String> dictionaryWords;
    WordleDictionary dictionary;

    @BeforeEach
    public void setUp() throws Exception {
        loader = new WordleDictionaryLoader();
        dictionaryWords = loader.loadDictionary("words_ru.txt");
        System.out.println("Количество загруженных слов: " + dictionaryWords.size());
        dictionary = new WordleDictionary(dictionaryWords);
    }

    @Test
    void testMainMethod() throws Exception {
        PrintWriter log = new PrintWriter(System.out);
        Wordle.main(new String[]{});
        log.flush();
    }

    @Nested
    class WordleGameTest {
        private WordleGame game;

        @BeforeEach
        public void setUp() {
            List<String> words = List.of("вода", "привет");
            dictionary = new WordleDictionary(words);
            game = new WordleGame(dictionary);
        }
        @Test
        public void testLoadDictionaryDirectly() throws Exception {
            WordleDictionaryLoader loader = new WordleDictionaryLoader();
            List<String> words = loader.loadDictionary("words_ru.txt");
            System.out.println("Слова из файла: " + words);
            assertFalse(words.isEmpty(), "Файл должен содержать слова");
        }

        @Test
        public void testMakeGuess_ExactMatch() throws WordNotFoundInDictionaryException {
            String guess = "вода";
            String hint = game.makeGuess(guess);
            assertEquals("+++++", hint);
        }

        @Test
        public void testMakeGuess_PartialMatch() throws WordNotFoundInDictionaryException {
            if ("вода".equals(game.getAnswer())) {
                String hint = game.makeGuess("водао");
                assertEquals("+++--", hint);
            }
        }

        @Test
        void testWordGuessed_AfterCorrectGuess() throws WordNotFoundInDictionaryException {
            game.makeGuess("абзац");
            assertTrue(game.isWordGuessed());
        }

        @Test
        void testRemainingAttempts_DecreasesCorrectly() throws WordNotFoundInDictionaryException {
            for (int i = 0; i < 5; i++) {
                game.makeGuess("абзац");
            }
            assertEquals(1, game.getSteps());
        }
    }

    @Nested
    class WordleDictionaryTest {
        private static WordleDictionary dict;

        @BeforeAll
        static void setUpClass() throws Exception {
            WordleDictionaryLoader loader = new WordleDictionaryLoader();
            List<String> words = loader.loadDictionary("words_ru.txt");
            dict = new WordleDictionary(words);
        }

        @Test
        public void testFilterFiveLetterWords() {
            assertNotNull(dict);
            assertFalse(dict.getSize() == 0);
        }

        @Test
        public void testContainsValidWord() {
            // Выберите слово, которое точно должно быть в словаре
            String testWord = "арбуз"; // замените на слово из вашего словаря
            assertTrue(dict.containsWord(testWord), "Ожидалось, что слово " + testWord + " будет найдено");
        }

        @Test
        public void testDoesNotContainInvalidWord() {
            assertFalse(dict.containsWord("кот"));
        }

        @Test
        public void testGetRandomWord() {
            assertNotNull(dict.getRandomWord());
        }
    }
}
