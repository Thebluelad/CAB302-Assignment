package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Economy;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

public class PassengerTests {

	//A note to lucy is that the subclass constructors should call the super class
	//Probably why these tests are failing.
	
	@Test
	public void cancelSeatTestIsNew() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.cancelSeat(cancellationTime);
		assertTrue(test.isNew() == true);
	}

	@Test
	public void cancelSeatTestBookingTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.cancelSeat(cancellationTime);
		assertTrue(test.getBookingTime() == cancellationTime);
	}
	
	//Test the passenger exception throw for cancellationTime
}
