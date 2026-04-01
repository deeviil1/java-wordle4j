package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
        private PrintWriter writer;

        public Logger(String fileName) {
            try {
                writer = new PrintWriter(new FileWriter(fileName, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void log(String message) {
            writer.println(message);
            writer.flush();
        }

        public void close() {
            if (writer != null) {
                writer.close();
            }
        }
    }
