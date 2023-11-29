package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private int[][][] seats;

    public Cinema() {
        seats = new int[5][10][20]; // 5 залів, 10 рядів, 20 місць у кожному ряду
        initializeSeats();
    }

    public void initializeSeats() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 20; k++) {
                    seats[i][j][k] = 0; // Початкове ініціалізування всіх місць як вільних (0)
                }
            }
        }
    }

    public void bookSeats(int hallNumber, int row, int[] seatsToBook) {
        for (int seat : seatsToBook) {
            seats[hallNumber - 1][row - 1][seat - 1] = 1; // Позначаємо броньовані місця як 1
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seatsToCancel) {
        for (int seat : seatsToCancel) {
            seats[hallNumber - 1][row - 1][seat - 1] = 0; // Скасовуємо броньовані місця назад на 0 (вільні)
        }
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int i = 0; i < 10; i++) {
            int consecutiveSeats = 0;
            for (int j = 0; j < 20; j++) {
                if (seats[hallNumber - 1][i][j] == 0) {
                    consecutiveSeats++;
                    if (consecutiveSeats == numSeats) {
                        return true; // Якщо доступна зазначена кількість послідовних місць
                    }
                } else {
                    consecutiveSeats = 0;
                }
            }
        }
        return false; // Якщо в заданому ряду немає достатньої кількості послідовних вільних місць
    }

    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Схема роміщення місць в залі № " + hallNumber);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(seats[hallNumber - 1][i][j] + " "); // Друк схеми розміщення місць
            }
            System.out.println();
        }
    }
    public List<Integer> findBestAvailable(int hallNumber, int numSeats) {
        List<Integer> bestSeats = new ArrayList<>();
        int consecutiveSeats = 0;
        int startRow = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (seats[hallNumber - 1][i][j] == 0) {
                    consecutiveSeats++;
                    if (consecutiveSeats == 1) {
                        startRow = i; // запам'ятовуємо номер першого ряду в послідовності вільних місць
                    }
                    if (consecutiveSeats == numSeats) {
                        for (int k = j - numSeats + 1; k <= j; k++) {
                            bestSeats.add((startRow + 1) * 100 + k + 1); // додаємо місця в форматі "ряд * 100 + номер місця"
                        }
                        return bestSeats; // повертаємо список знайдених послідовних місць
                    }
                } else {
                    consecutiveSeats = 0;
                }
            }
        }
        return bestSeats; // повертаємо пустий список, якщо немає достатньої кількості послідовних місць
    }

    public void autoBook(int hallNumber, int numSeats) {
        List<Integer> bestSeats = findBestAvailable(hallNumber, numSeats);
        if (!bestSeats.isEmpty()) {
            int row = bestSeats.get(0) / 100;
            int[] seatsToBook = bestSeats.stream().mapToInt(i -> i % 100).toArray();
            bookSeats(hallNumber, row, seatsToBook);
            System.out.println("Місця успішно заброньовані: " + bestSeats);
        } else {
            System.out.println("Доступні місця відсутні для " + numSeats + " місць.");
        }
    }
    public int getSeatState(int hallNumber, int row, int seatNumber) {
        return seats[hallNumber - 1][row - 1][seatNumber - 1];
    }
}
