package org.example;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Бронювання місць");
            System.out.println("2. Скасування бронювання");
            System.out.println("3. Перевірка доступності місць");
            System.out.println("4. Друк схеми розміщення місць");
            System.out.println("5. Автоматичне бронювання");
            System.out.println("6. Вихід");

            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookSeats(cinema, scanner);
                    break;
                case 2:
                    cancelBooking(cinema, scanner);
                    break;
                case 3:
                    checkAvailability(cinema, scanner);
                    break;
                case 4:
                    printSeatingArrangement(cinema, scanner);
                    break;
                case 5:
                    autoBookSeats(cinema, scanner);
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неправильний вибір, спробуйте знову.");
            }
        }
    }
    static void autoBookSeats(Cinema cinema, Scanner scanner) {
        System.out.print("Номер залу: ");

        int hallNumber = scanner.nextInt();
        System.out.print("Кількість місць для автоматичного бронювання: ");
        int numSeats = scanner.nextInt();

        cinema.autoBook(hallNumber, numSeats);
    }
    static void bookSeats(Cinema cinema, Scanner scanner) {
        System.out.print("Номер залу: ");
        int hallNumber = scanner.nextInt();
        System.out.print("Номер ряду: ");
        int row = scanner.nextInt();
        System.out.print("Кількість місць для бронювання: ");
        int numSeats = scanner.nextInt();

        int[] seatsToBook = new int[numSeats];
        System.out.println("Виберіть номери місць для бронювання:");
        for (int i = 0; i < numSeats; i++) {
            seatsToBook[i] = scanner.nextInt();
        }

        cinema.bookSeats(hallNumber, row, seatsToBook);
        System.out.println("Місця успішно заброньовані!");
    }

    static void cancelBooking(Cinema cinema, Scanner scanner) {
        System.out.print("Номер залу: ");
        int hallNumber = scanner.nextInt();
        System.out.print("Номер ряду: ");
        int row = scanner.nextInt();
        System.out.print("Кількість місць для скасування: ");
        int numSeats = scanner.nextInt();

        int[] seatsToCancel = new int[numSeats];
        System.out.println("Виберіть номери місць для скасування:");
        for (int i = 0; i < numSeats; i++) {
            seatsToCancel[i] = scanner.nextInt();
        }

        cinema.cancelBooking(hallNumber, row, seatsToCancel);
        System.out.println("Бронювання скасовано!");
    }

    static void checkAvailability(Cinema cinema, Scanner scanner) {
        System.out.print("Номер залу: ");
        int hallNumber = scanner.nextInt();
        System.out.print("Кількість місць, які потрібно перевірити: ");
        int numSeats = scanner.nextInt();

        boolean available = cinema.checkAvailability(hallNumber, numSeats);
        if (available) {
            System.out.println("Доступні місця знайдені!");
        } else {
            System.out.println("Доступні місця відсутні.");
        }
    }

    static void printSeatingArrangement(Cinema cinema, Scanner scanner) {
        System.out.print("Номер залу для друку схеми розміщення місць: ");
        int hallNumber = scanner.nextInt();

        cinema.printSeatingArrangement(hallNumber);
    }
}