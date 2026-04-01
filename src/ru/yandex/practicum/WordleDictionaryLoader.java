package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class WordleDictionaryLoader {
    public List<String> loadDictionary(String dictionaryFilePath) throws Exception {
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Приводим слово к нижнему регистру и заменяем ё на е
                line = line.toLowerCase().replace('ё', 'е');
                words.add(line);
            }
        }

        return words;
    }
}