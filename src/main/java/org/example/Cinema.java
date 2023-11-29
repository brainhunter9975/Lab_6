package org.example;

public class Cinema {
    private int[][][] seats;

    public Cinema() {
        seats = new int[5][10][20]; // 5 залів, 10 рядів, 20 місць у кожному ряду
        initializeSeats();
    }

    private void initializeSeats() {
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
}
