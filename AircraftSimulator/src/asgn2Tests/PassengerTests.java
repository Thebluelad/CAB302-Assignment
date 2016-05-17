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
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelSeatTestExceptionThrowIsNew() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.cancelSeat(cancellationTime);
		
		//Should throw the exception because the first cancelSeat makes the passenger new.
		test.cancelSeat(cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelSeatTestExceptionThrowIsQueued() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 2;
		int queueTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		
		//Should throw the exception because the passenger is queued
		test.cancelSeat(cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelSeatTestExceptionThrowIsRefused() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 2;
		int refusalTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.refusePassenger(refusalTime);
		
		//Should throw the exception because the passenger is refused
		test.cancelSeat(cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelSeatTestExceptionThrowIsFlown() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.flyPassenger(departureTime);
		
		//Should throw the exception because the passenger is flown
		test.cancelSeat(cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelSeatTestExceptionThrowCancellationTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = -1;
		Economy test = new Economy(bookingTime, departureTime);
		
		//Should throw the exception because the cancellationTime is less than 0
		test.cancelSeat(cancellationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelSeatTestExceptionThrowDepartureTimeCancellationTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 2;
		int cancellationTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		
		//Should throw the exception because the departureTime is less than the cancellationTime
		test.cancelSeat(cancellationTime);
	}
}
