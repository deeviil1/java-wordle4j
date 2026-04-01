package ru.yandex.practicum;


import java.util.ArrayList;
import java.util.List;

public class GameResult {
        private final boolean isWon;
        private final String answer;
        private final int steps;
        private final List<String> userInput;



        public GameResult(boolean isWon, String answer, int steps, List<String> userInput) {
            this.isWon = isWon;
            this.answer = answer;
            this.steps = steps;
            this.userInput = new ArrayList<>(userInput); // Защитная копия
        }


        public boolean isWon() {
            return isWon;
        }


        public String getAnswer() {
            return answer;
        }


        public int getSteps() {
            return steps;
        }


        public List<String> getUserInput() {
            return new ArrayList<>(userInput);
        }


        public int getAttemptsMade() {
            return userInput.size();
        }


        public String getResultMessage() {
            if (isWon) {
                return String.format(
                        "Поздравляем! Вы выиграли за %d попыток!\n" +
                                "Загаданное слово: %s\n" +
                                "Ваши попытки: %s",
                        userInput.size(),
                        answer,
                        String.join(", ", userInput)
                );
            } else {
                return String.format(
                        "Игра окончена. Вы не угадали слово.\n" +
                                "Загаданное слово было: %s\n" +
                                "Вы сделали %d попыток: %s",
                        answer,
                        userInput.size(),
                        String.join(", ", userInput)
                );
            }
        }


        @Override
        public String toString() {
            String result = isWon ? "Победа" : "Поражение";
            return String.format(
                    "Результат: %s | Слово: %s | Осталось попыток: %d | Всего попыток: %d",
                    result,
                    answer,
                    steps,
                    userInput.size()
            );
        }
    }
