package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CinemaTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Cinema cinema;

    @BeforeEach
    void setUp() {
        cinema = new Cinema();
    }

    @Test
    void testBookSeats() {
        // Test the bookSeats method

        // Prepare the cinema seats for testing
        int hallNumber = 1; // Define the hall number for testing
        int row = 3; // Define the row for testing
        int[] seatsToBook = {5, 6, 7}; // Define the seats to book for testing

        // Get the initial state of the seats
        int initialSeatState = cinema.getSeatState(hallNumber, row, seatsToBook[0]);

        // Invoke the bookSeats method
        cinema.bookSeats(hallNumber, row, seatsToBook);

        // Verify the changes - check if seats are properly booked
        for (int seat : seatsToBook) {
            int bookedSeatState = cinema.getSeatState(hallNumber, row, seat);
            assertEquals(1, bookedSeatState, "Seat " + seat + " in row " + row + " of hall " + hallNumber + " should be booked.");
        }

        // Additional checks if needed
        // For instance, check if other seats remain unchanged

        // Example:
        for (int i = 1; i <= 20; i++) {
            if (i < 5 || i > 7) {
                int state = cinema.getSeatState(hallNumber, row, i);
                assertEquals(initialSeatState, state, "Seat " + i + " in row " + row + " of hall " + hallNumber + " should remain unchanged.");
            }
        }
    }
    @Test
    void testInitializeSeats() {
        // Perform the initialization of seats
        cinema.initializeSeats();

        // Verify if all seats are initialized to 0 (available)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 20; k++) {
                    assertEquals(0, cinema.getSeatState(i + 1, j + 1, k + 1),
                            "Seat (" + (i + 1) + "," + (j + 1) + "," + (k + 1) + ") should be available.");
                }
            }
        }
    }
    @Test
    void testCancelBooking() {
        // Book some seats first for cancellation testing
        int hallNumber = 1;
        int row = 3;
        int[] seatsToBook = {5, 6, 7};
        cinema.bookSeats(hallNumber, row, seatsToBook);

        // Cancel the booked seats
        cinema.cancelBooking(hallNumber, row, seatsToBook);

        // Verify if the seats are canceled and available again
        for (int seat : seatsToBook) {
            assertEquals(0, cinema.getSeatState(hallNumber, row, seat),
                    "Seat " + seat + " in row " + row + " of hall " + hallNumber + " should be available after cancellation.");
        }
    }
    @Test
    void testCheckAvailability() {
        // Scenario 1: Seats are available
        int hallNumber1 = 1;
        int row1 = 3;
        int[] seatsToBook1 = {5, 6, 7};
        cinema.bookSeats(hallNumber1, row1, seatsToBook1);

        assertTrue(cinema.checkAvailability(hallNumber1, 3),
                "Three consecutive seats should be available in row " + row1 + " of hall " + hallNumber1);

        // Scenario 2: Seats are not available
        int hallNumber2 = 2;
        int row2 = 5;
        int[] seatsToBook2 = {17, 18, 19};
        cinema.bookSeats(hallNumber2, row2, seatsToBook2);

        assertFalse(cinema.checkAvailability(hallNumber2, 4),
                "Four consecutive seats should not be available in row " + row2 + " of hall " + hallNumber2);
    }
    @Test
    void testPrintSeatingArrangement() {
        int hallNumber = 1; // Choose a specific hall number to test

        cinema.printSeatingArrangement(hallNumber);

        // Define the expected seating arrangement here (as a string)
        String expectedOutput = "0 0 0 0 0 0 0 0 0 0 ..."; // Replace with your expected seating arrangement

        // Get the captured output
        String printedOutput = outputStreamCaptor.toString().trim().replaceAll("\\s+", " ");

        // Assert that the printed output matches the expected seating arrangement
        assertEquals(expectedOutput, printedOutput,
                "Printed seating arrangement for hall " + hallNumber + " should match the expected arrangement.");
    }
    @Test
    void testFindBestAvailable() {
        // Scenario 1: Seats are available
        int hallNumber1 = 1;
        int row1 = 3;
        int[] seatsToBook1 = {5, 6, 7, 8};
        cinema.bookSeats(hallNumber1, row1, seatsToBook1);

        List<Integer> availableSeats1 = cinema.findBestAvailable(hallNumber1, 4);

        assertFalse(availableSeats1.isEmpty(),
                "There should be available consecutive seats in hall " + hallNumber1);

        // Scenario 2: Seats are not available
        int hallNumber2 = 2;
        int row2 = 5;
        int[] seatsToBook2 = {17, 18, 19};
        cinema.bookSeats(hallNumber2, row2, seatsToBook2);

        List<Integer> availableSeats2 = cinema.findBestAvailable(hallNumber2, 4);

        assertTrue(availableSeats2.isEmpty(),
                "There should be no available consecutive seats in hall " + hallNumber2);
    }
    @Test
    void testAutoBook() {
        // Scenario 1: Seats are available
        int hallNumber1 = 1;

        // Booking some seats to free up for autoBooking
        int[] seatsToBook1 = {5, 6, 7};
        cinema.bookSeats(hallNumber1, 3, seatsToBook1);

        cinema.autoBook(hallNumber1, 3);

        String successMessage1 = "Місця успішно заброньовані:";
        assertTrue(outputStreamCaptor.toString().contains(successMessage1),
                "Auto booking should be successful when seats are available");

        // Scenario 2: Seats are not available
        int hallNumber2 = 2;

        // Booking some seats so that there are no available consecutive seats
        int[] seatsToBook2 = {17, 18, 19};
        cinema.bookSeats(hallNumber2, 5, seatsToBook2);

        cinema.autoBook(hallNumber2, 4);

        String failureMessage2 = "Доступні місця відсутні для 4 місць.";
        assertTrue(outputStreamCaptor.toString().contains(failureMessage2),
                "Auto booking should fail when seats are not available");
    }
    @Test
    void testGetSeatState() {
        int hallNumber = 1;
        int row = 3;
        int seatNumber = 5;

        // Initially, the seat should be available (state 0)
        assertEquals(0, cinema.getSeatState(hallNumber, row, seatNumber),
                "Seat should be available before booking");

        // Book the seat
        cinema.bookSeats(hallNumber, row, new int[]{seatNumber});

        // After booking, the seat should be marked as booked (state 1)
        assertEquals(1, cinema.getSeatState(hallNumber, row, seatNumber),
                "Seat should be booked after booking");
    }
}