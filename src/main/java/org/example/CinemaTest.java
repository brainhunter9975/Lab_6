package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CinemaTest {

    private Cinema cinema;

    @BeforeEach
    public void setUp() {
        cinema = new Cinema();
    }

    @Test
    public void testInitializeSeats() {
        // Assuming the seats are correctly initialized in the constructor
        // You can add assertions here to check the initial state of the seats array
    }

    @Test
    public void testBookSeats() {
        int hallNumber = 1;
        int row = 2;
        int[] seatsToBook = {1, 3, 5};
        cinema.bookSeats(hallNumber, row, seatsToBook);
        // Add assertions to validate if seats are correctly booked
    }

    @Test
    public void testCancelBooking() {
        int hallNumber = 1;
        int row = 2;
        int[] seatsToCancel = {1, 3, 5};
        cinema.cancelBooking(hallNumber, row, seatsToCancel);
        // Add assertions to validate if seats are correctly canceled
    }

    @Test
    public void testCheckAvailability() {
        int hallNumber = 1;
        int numSeats = 3;
        boolean available = cinema.checkAvailability(hallNumber, numSeats);
        // Add assertions to verify the availability of seats
    }

    @Test
    public void testPrintSeatingArrangement() {
        int hallNumber = 1;
        cinema.printSeatingArrangement(hallNumber);
        // Manual check for the seating arrangement by inspecting console output
    }

    @Test
    public void testFindBestAvailable() {
        int hallNumber = 1;
        int numSeats = 4;
        List<Integer> bestSeats = cinema.findBestAvailable(hallNumber, numSeats);
        // Add assertions to verify the best available seats
    }

    @Test
    public void testAutoBook() {
        int hallNumber = 1;
        int numSeats = 4;
        cinema.autoBook(hallNumber, numSeats);
        // Manual check or assertions if you can predict the best seats based on the method logic
    }

    @Test
    public void testGetSeatState() {
        int hallNumber = 1;
        int row = 2;
        int seatNumber = 3;
        int seatState = cinema.getSeatState(hallNumber, row, seatNumber);
        // Add assertions to validate the state of a particular seat
    }
}