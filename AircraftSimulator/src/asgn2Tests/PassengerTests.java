package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

public class PassengerTests {	
	//Now begins the cancelSeat Tests
	
	@Test
	public void cancelSeatTestIsNew() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		int cancellationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		test.cancelSeat(cancellationTime);
		assertTrue(test.isNew() == true);
	}

	@Test
	public void cancelSeatTestBookingTime() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		int cancellationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
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
	
	//Now begins the confirmSeat Tests
	
	@Test
	public void confirmSeatIsNew() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		assertTrue(test.isNew() == false);
	}
	
	@Test
	public void confirmSeatIsConfirmed() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		assertTrue(test.isConfirmed() == true);
	}
	
	@Test
	public void confirmSeatConfirmationTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		assertTrue(test.getConfirmationTime() == confirmationTime);
	}
	
	@Test
	public void confirmSeatDepartureTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		assertTrue(test.getDepartureTime() == departureTime);
	}
	
	@Test
	public void confirmSeatIsQueued() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		assertTrue(test.isQueued() == false);
	}
	
	@Test
	public void confirmSeatExitQueueTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		assertTrue(test.getExitQueueTime() == confirmationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmSeatExceptionThrowIsConfirmed() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmSeatExceptionThrowIsRefused() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		int refusalTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.refusePassenger(refusalTime);
		
		//Should fail because the passenger should be refused
		test.confirmSeat(confirmationTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmSeatExceptionThrowIsFlown() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.flyPassenger(departureTime);
		
		//Should fail because the passenger is flown
		test.confirmSeat(confirmationTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmSeatExceptionThrowConfirmationTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = -1;
		Economy test = new Economy(bookingTime, departureTime);
		
		test.confirmSeat(confirmationTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmSeatExceptionThrowDepartureConfirmation() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 2;
		int confirmationTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		
		test.confirmSeat(confirmationTime, departureTime);
	}
	
	
	//Now begins the flyPassenger Tests
	
	@Test
	public void flyPassengerIsConfirmed() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		test.flyPassenger(departureTime);
		assertTrue(test.isConfirmed() == false);
	}
	
	@Test
	public void flyPassengerIsFlown() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		test.flyPassenger(departureTime);
		assertTrue(test.isFlown() == true);
	}
	
	@Test
	public void flyPassengerDepartureTime() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		test.flyPassenger(departureTime);
		assertTrue(test.getDepartureTime() == departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void flyPassengerExceptionThrowIsNew() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int cancellationTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.cancelSeat(cancellationTime);
		test.flyPassenger(departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void flyPassengerExceptionThrowIsQueued() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int queueTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		test.flyPassenger(departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void flyPassengerExceptionThrowIsRefused() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		int refusalTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		test.refusePassenger(refusalTime);
		test.flyPassenger(departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void flyPassengerExceptionThrowIsFlown() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.flyPassenger(departureTime);
		test.flyPassenger(departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void flyPassengerExceptionThrowDepartureTime() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 0;
		Economy test = new Economy(bookingTime, departureTime);
		test.flyPassenger(departureTime);
	}
	
	//Now begins the queuePassenger Tests
	
	@Test
	public void queuePassengerIsNew() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		assertTrue(test.isNew() == false);
	}
	
	@Test
	public void queuePassengerIsQueued() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		assertTrue(test.isQueued() == true);
	}
	
	@Test
	public void queuePassengerEnterQueueTime() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		assertTrue(test.getEnterQueueTime() == queueTime);
	}
	
	@Test
	public void queuePassengerDepartureTime() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		assertTrue(test.getDepartureTime() == departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void queuePassengerExceptionThrowInQueue() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		
		//Should throw an exception because the passenger is already in queue
		test.queuePassenger(queueTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void queuePassengerExceptionThrowIsConfirmed() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		
		//Should throw an exception because the passenger is already confirmed
		test.queuePassenger(queueTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void queuePassengerExceptionThrowIsRefused() throws PassengerException {
		int bookingTime = 1;
		int refusalTime = 2;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.refusePassenger(refusalTime);
		
		//Should throw an exception because the passenger is already refused
		test.queuePassenger(queueTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void queuePassengerExceptionThrowIsFlown() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.flyPassenger(departureTime);
		
		//Should throw an exception because the passenger is already flown
		test.queuePassenger(queueTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void queuePassengerExceptionThrowQueueTime() throws PassengerException {
		int bookingTime = 1;
		int queueTime = -1;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		
		//Should throw an exception because queueTime < 0
		test.queuePassenger(queueTime, departureTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void queuePassengerExceptionThrowQueueDeparture() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 3;
		int departureTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		
		//Should throw an exception because departureTime < queueTime
		test.queuePassenger(queueTime, departureTime);
	}
	
	//Now begins the refusePassenger Tests
	
	@Test
	public void refusePassengerIsNew() throws PassengerException {
		int bookingTime = 1;
		int refusalTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.refusePassenger(refusalTime);
		assertTrue(test.isNew() == false);
	}
	
	@Test
	public void refusePassengerIsQueued() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int refusalTime = 3;
		int departureTime = 4;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		test.refusePassenger(refusalTime);
		assertTrue(test.isQueued() == false);
	}
	
	@Test
	public void refusePassengerExitQueue() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int refusalTime = 3;
		int departureTime = 4;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		test.refusePassenger(refusalTime);
		assertTrue(test.getExitQueueTime() == refusalTime);
	}
	
	@Test
	public void refusePassengerIsRefused() throws PassengerException {
		int bookingTime = 1;
		int refusalTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.refusePassenger(refusalTime);
		assertTrue(test.isRefused() == true);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void refusePassengerExceptionThrowIsConfirmed() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int refusalTime = 3;
		int departureTime = 4;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		
		//Should throw an exception because passenger isConfirmed
		test.refusePassenger(refusalTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void refusePassengerExceptionThrowIsRefused() throws PassengerException {
		int bookingTime = 1;
		int refusalTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.refusePassenger(refusalTime);
		
		//Should throw an exception because already refused
		test.refusePassenger(refusalTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void refusePassengerExceptionThrowIsFlown() throws PassengerException {
		int bookingTime = 1;
		int refusalTime = 2;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		test.flyPassenger(departureTime);
		
		//Should throw an exception because already flown
		test.refusePassenger(refusalTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void refusePassengerExceptionThrowRefusalTime() throws PassengerException {
		int bookingTime = 1;
		int refusalTime = -1;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		
		//Should throw an exception because refusalTime < 0
		test.refusePassenger(refusalTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void refusePassengerExceptionThrowRefusalBooking() throws PassengerException {
		int bookingTime = 2;
		int refusalTime = 1;
		int departureTime = 3;
		Economy test = new Economy(bookingTime, departureTime);
		
		//Should throw an exception because refusalTime < bookingTime
		test.refusePassenger(refusalTime);
	}
	
	
	//Now begins the wasConfirmed Tests
	
	@Test
	public void wasConfirmed() throws PassengerException {
		int bookingTime = 1;
		int confirmationTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy test = new Economy(bookingTime, departureTime);
		test.confirmSeat(confirmationTime, departureTime);
		test.cancelSeat(cancellationTime);
		assertTrue(test.wasConfirmed() == true);
	}
	
	//Now begins the wasQueued Tests
	
	@Test
	public void wasQueued() throws PassengerException {
		int bookingTime = 1;
		int queueTime = 2;
		int refusalTime = 3;
		int departureTime = 4;
		Economy test = new Economy(bookingTime, departureTime);
		test.queuePassenger(queueTime, departureTime);
		test.refusePassenger(refusalTime);
		assertTrue(test.wasQueued() == true);
	}
	
	//Now begins the upgrade Tests
	
	@Test
	public void upgradeEconomy() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 2;
		Economy test = new Economy(bookingTime, departureTime);
		Premium upgraded = new Premium(bookingTime, departureTime);
		assertTrue(test.upgrade().getClass() == upgraded.getClass());
	}
	
	@Test
	public void upgradePremium() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 2;
		Premium test = new Premium(bookingTime, departureTime);
		Business upgraded = new Business(bookingTime, departureTime);
		assertTrue(test.upgrade().getClass() == upgraded.getClass());
	}
	
	@Test
	public void upgradeBusiness() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 2;
		Business test = new Business(bookingTime, departureTime);
		First upgraded = new First(bookingTime, departureTime);
		assertTrue(test.upgrade().getClass() == upgraded.getClass());
	}
	
	@Test
	public void upgradeFirst() throws PassengerException {
		int bookingTime = 1;
		int departureTime = 2;
		First test = new First(bookingTime, departureTime);
		First upgraded = new First(bookingTime, departureTime);
		assertTrue(test.upgrade().getClass() == upgraded.getClass());
	}
	
	//Now begins the copyPassengerState Tests
	//copyPassengerState tests need to be done within the upgrade tests because copyPassengerState is a protected function
}
