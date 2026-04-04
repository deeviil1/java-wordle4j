package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordleDictionaryLoaderTest {
    @Test
    public void shouldLoadDictionarySuccessfully() throws Exception {
        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        List<String> words = loader.loadDictionary("words_ru.txt");
        assertNotNull(words);
        assertFalse(words.isEmpty()); // Проверяем, что словарь не пустой

        assertTrue(words.contains("арбуз"), "Слово 'арбуз' должно быть в словаре");
        assertTrue(words.contains("абсент"), "Слово 'абсент' должно быть в словаре");

    }
}
