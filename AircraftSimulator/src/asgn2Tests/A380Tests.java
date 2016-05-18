package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.AircraftException;
import asgn2Passengers.Economy;
import asgn2Passengers.PassengerException;

public class A380Tests {

	@Test
	public void cancelBookingTest() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 1;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
		assertTrue(testp.isConfirmed() == false);
	}

}
