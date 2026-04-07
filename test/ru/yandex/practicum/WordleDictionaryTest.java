package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordleDictionaryTest {
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

