package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.AircraftException;
import asgn2Passengers.Economy;
import asgn2Passengers.PassengerException;

public class A380Tests {

	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowNotConfirmed() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int departureTime = 4;
		int cancellationTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowIsNew() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int cancellationTime = 2;
		int departureTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowIsQueued() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int queueTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		testp.queuePassenger(queueTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowIsRefused() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int refusalTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		testp.refusePassenger(refusalTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowIsFlown() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		testp.flyPassenger(departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowCancellationTimeLessZero() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int cancellationTime = -1;
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		testp.confirmSeat(confirmationTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowDepartureTimeLessCancellationTime() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int cancellationTime = 3;
		int bookingTime = 1;
		int departureTime = 2;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test
	public void flightEmpty() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		//int cancellationTime = -1;
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		assertTrue(test.flightEmpty() == false);
	}
	
}
	
