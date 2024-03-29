package asgn2Tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.Passenger;
import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;
import junit.framework.TestCase;

public class A380Tests {
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void constructorThrowFlightCodeNull () throws AircraftException, PassengerException {
		int departureTime = 1;
		A380 test = new A380(null, departureTime);	
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void constructorThrowDepartureTimeLessThanZero () throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int departureTime = -1;
		A380 test = new A380(flightCode, departureTime);	
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void constructorThrowDepartureTimeEqualsZero () throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int departureTime = 0;
		A380 test = new A380(flightCode, departureTime);	
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void constructorThrowFirstLessThanZero () throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int departureTime = 2;
		A380 test = new A380(flightCode, departureTime, -1, 0, 0, 0);
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void constructorThrowBusinesLessThanZero () throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int departureTime = 2;
		A380 test = new A380(flightCode, departureTime, 0, -1, 0, 0);
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void constructorThrowPremiumLessThanZero () throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int departureTime = 2;
		A380 test = new A380(flightCode, departureTime, 0, 0, -1, 0);
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void constructorThrowyEconomyLessThanZero () throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int departureTime = 2;
		A380 test = new A380(flightCode, departureTime, 0, 0, 0, -1);
	}

	@Test (expected = asgn2Passengers.PassengerException.class)
	public void cancelBookingExceptionThrowNotConfirmed() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		testp.cancelSeat(cancellationTime);
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
		testp.isNew();
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
		int bookingTime = 1;
		int departureTime = 2;
		int cancellationTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void cancelBookingExceptionThrowSeatsNotContainPassenger() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		int cancellationTime = 2;
		Economy testp = new Economy(bookingTime, departureTime);
		testp.confirmSeat(confirmationTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.cancelBooking(testp, cancellationTime);
	}
	
	@Test
	public void cancelBookingSeatsRemove() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int departureTime = 3;
		int confirmationTime = 2;
		int cancellationTime = 2;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.cancelBooking(testp, cancellationTime);
		assertTrue(test.hasPassenger(testp) == false);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmBookingExceptionThrowIsConfirmed() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		testp.confirmSeat(confirmationTime, departureTime);
		test.confirmBooking(testp, confirmationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmBookingExceptionThrowIsRefused() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int refusalTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		testp.refusePassenger(refusalTime);
		test.confirmBooking(testp, confirmationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmBookingExceptionThrowIsFlown() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		testp.flyPassenger(departureTime);
		test.confirmBooking(testp, confirmationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmBookingExceptionThrowConfirmationTimeLessThanZero() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int confirmationTime = -1;
		int bookingTime = 1;
		int departureTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
	}
	
	@Test (expected = asgn2Passengers.PassengerException.class)
	public void confirmBookingExceptionThrowDepartureTimeLessThanConfirmationTime() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int departureTime = 2;
		int confirmationTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
	}
	
	@Test (expected = asgn2Aircraft.AircraftException.class)
	public void confirmBookingAircraftExceptionThrow() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		Economy testp2 = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 0, 0, 0, 0);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testp2, confirmationTime);
		test.seatsAvailable(testp);
		test.seatsAvailable(testp2);
	}
	
	@Test
	public void confirmBookingPassengerSeatsAdded() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 3;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 0, 0, 0, 2);
		test.confirmBooking(testp, confirmationTime);
		assertTrue(test.seatsAvailable(testp));
	}
	
	@Test
	public void flightEmptyNotEmpty() throws AircraftException, PassengerException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		assertFalse(test.flightEmpty());
	}
	
	@Test
	public void flightEmptyIsEmpty() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.cancelBooking(testp, cancellationTime);
		assertTrue(test.flightEmpty());
	}
	
	@Test
	public void flightFullNotFull() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.cancelBooking(testp, cancellationTime);
		assertFalse(test.flightFull());
	}
	
	@Test
	public void flightFullIsFull() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 0, 0, 0, 1);
		test.confirmBooking(testp, confirmationTime);
		assertTrue(test.flightFull());
	}
	
	@Test
	public void flyPassengers() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.flyPassengers(departureTime);
		assertTrue(testp.isFlown());
	}
	
	@Test
	public void flyPassengersMultiple() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		Economy testp2 = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testp2, confirmationTime);
		test.flyPassengers(departureTime);
		assertTrue(testp.isFlown());
		assertTrue(testp2.isFlown());
	}
	
	@Test
	public void flyPassengersNoneFail() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.cancelBooking(testp, cancellationTime);
		test.flyPassengers(departureTime);
		assertFalse(testp.isFlown());
	}
	
	@Test
	public void getBookingsOneEconomy() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getNumEconomy() == 1);
	}
	
	@Test
	public void getBookingsNoBusiness() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getNumBusiness() == 0);
	}
	
	@Test
	public void getBookingsNoPremium() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getNumPremium() == 0);
	}
	
	@Test
	public void getBookingsNoFirst() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getNumFirst() == 0);
	}
	
	@Test
	public void getBookingsFourTotal() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy teste = new Economy(bookingTime, departureTime);
		Premium testp = new Premium(bookingTime, departureTime);
		Business testbus = new Business (bookingTime, departureTime);
		First testf = new First (bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testf, confirmationTime);
		test.confirmBooking(testbus, confirmationTime);
		test.confirmBooking(teste, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getTotal() == 4);
	}
	
	@Test
	public void getBookingsOneTotal () throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getTotal() == 1);
	}
	
	@Test
	public void getBookingsNoneTotal () throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int departureTime = 4;
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		Bookings testb = test.getBookings();
		assertTrue(testb.getTotal() == 0);
	}
	
	@Test
	public void getBookingsNoneAvaliable() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy teste = new Economy(bookingTime, departureTime);
		Premium testp = new Premium(bookingTime, departureTime);
		Business testbus = new Business (bookingTime, departureTime);
		First testf = new First (bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testf, confirmationTime);
		test.confirmBooking(testbus, confirmationTime);
		test.confirmBooking(teste, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getAvailable() == 0);
	}
	
	@Test
	public void getBookingsThreeAvaliable() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		Bookings testb = test.getBookings();
		assertTrue(testb.getAvailable() == 3);
	}
	
	@Test 
	public void getPassengersOnePassenger() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(testp, confirmationTime);
		test.getPassengers();
		java.util.List<Passenger> list2 = test.getPassengers();
		assertTrue(list2.get(0) == testp);
	}
	
	@Test 
	public void getPassengersSecondPassenger() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy teste = new Economy(bookingTime, departureTime);
		Premium testp = new Premium(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(teste, confirmationTime);
		test.confirmBooking(testp, confirmationTime);
		test.getPassengers();
		java.util.List<Passenger> list2 = test.getPassengers();
		assertTrue(list2.get(1) == testp);
	}
	@Test 
	public void getPassengersThirdPassenger() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy teste = new Economy(bookingTime, departureTime);
		Premium testp = new Premium(bookingTime, departureTime);
		Business testb = new Business(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(teste, confirmationTime);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testb, confirmationTime);
		test.getPassengers();
		java.util.List<Passenger> list2 = test.getPassengers();
		assertTrue(list2.get(2) == testb);
	}
	
	@Test 
	public void getPassengersForthPassenger() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy teste = new Economy(bookingTime, departureTime);
		Premium testp = new Premium(bookingTime, departureTime);
		Business testb = new Business(bookingTime, departureTime);
		First testf = new First(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(teste, confirmationTime);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testb, confirmationTime);
		test.confirmBooking(testf, confirmationTime);
		test.getPassengers();
		java.util.List<Passenger> list2 = test.getPassengers();
		assertTrue(list2.get(3) == testf);
	}
	
	@Test 
	public void getPassengersSecondPassengerPersonCancel() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy teste = new Economy(bookingTime, departureTime);
		Premium testp = new Premium(bookingTime, departureTime);
		Business testb = new Business(bookingTime, departureTime);
		First testf = new First(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);
		test.confirmBooking(teste, confirmationTime);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testb, confirmationTime);
		test.confirmBooking(testf, confirmationTime);
		test.cancelBooking(testp, cancellationTime);
		test.getPassengers();
		java.util.List<Passenger> list2 = test.getPassengers();
		assertTrue(list2.get(1) == testb);
	}
	
	@Test 
	public void getPassengersNone() throws PassengerException, AircraftException {
		String  flightCode = "CNS-A380";
		int departureTime = 4;
		A380 test = new A380(flightCode, departureTime, 1, 1, 1, 1);	
		test.getPassengers();
		java.util.List<Passenger> list2 = test.getPassengers();
		assertTrue(list2.size() == 0);
	}
	
	@Test
	public void hasPassengerTrue() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		assertTrue(test.hasPassenger(testp));	
	}
	
	@Test
	public void hasPassengerNoPassenger() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int cancellationTime = 3;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.cancelBooking(testp, cancellationTime);
		assertFalse(test.hasPassenger(testp));	
	}
	
	@Test
	public void hasPassengerMultiple() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		Economy testp2 = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime);
		test.confirmBooking(testp, confirmationTime);
		test.confirmBooking(testp2, confirmationTime);
		assertTrue(test.hasPassenger(testp));
		assertTrue(test.hasPassenger(testp2));
	}
	
	@Test
	public void seatsAvaliableOneEconomy() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 0, 0, 0, 2);
		test.confirmBooking(testp, confirmationTime);
		assertTrue(test.seatsAvailable(testp));
	}

	@Test
	public void seatsAvaliableNoneFree() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int departureTime = 4;
		Economy testp = new Economy(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 0, 0, 0, 0);
		assertFalse(test.seatsAvailable(testp));
	}
	
	@Test
	public void upgradeBookingsExceptFirst() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy econ1 = new Economy(bookingTime, departureTime);
		Economy econ2 = new Economy(bookingTime, departureTime);
		Premium prem1 = new Premium (bookingTime, departureTime);
		Premium prem2 = new Premium (bookingTime, departureTime);
		Business bus1 = new Business(bookingTime, departureTime);
		Business bus2 = new Business(bookingTime, departureTime);
		First first1 = new First(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 2, 2, 2, 2);
		test.confirmBooking(econ1, confirmationTime);
		test.confirmBooking(econ2, confirmationTime);
		test.confirmBooking(prem1, confirmationTime);
		test.confirmBooking(prem2, confirmationTime);
		test.confirmBooking(bus1, confirmationTime);
		test.confirmBooking(bus2, confirmationTime);
		test.confirmBooking(first1, confirmationTime);
		
		test.upgradeBookings();
		assertEquals(test.toString(), "A380:CNS-A380:4 Count: 7 [F: 2 J: 2 P: 2 Y: 1]");
	}
	
	@Test
	public void upgradeBookingsNoUpgrades() throws PassengerException, AircraftException{
		String  flightCode = "CNS-A380";
		int bookingTime = 1;
		int confirmationTime = 2;
		int departureTime = 4;
		Economy econ1 = new Economy(bookingTime, departureTime);
		Economy econ2 = new Economy(bookingTime, departureTime);
		Premium prem1 = new Premium (bookingTime, departureTime);
		Premium prem2 = new Premium (bookingTime, departureTime);
		Business bus1 = new Business(bookingTime, departureTime);
		Business bus2 = new Business(bookingTime, departureTime);
		First first1 = new First(bookingTime, departureTime);
		First first2 = new First(bookingTime, departureTime);
		A380 test = new A380(flightCode, departureTime, 2, 2, 2, 2);
		test.confirmBooking(econ1, confirmationTime);
		test.confirmBooking(econ2, confirmationTime);
		test.confirmBooking(prem1, confirmationTime);
		test.confirmBooking(prem2, confirmationTime);
		test.confirmBooking(bus1, confirmationTime);
		test.confirmBooking(bus2, confirmationTime);
		test.confirmBooking(first1, confirmationTime);
		test.confirmBooking(first2, confirmationTime);
		
		test.upgradeBookings();
		assertEquals(test.toString(), "A380:CNS-A380:4 Count: 8 [F: 2 J: 2 P: 2 Y: 2]");
		
	}
	
	//This test is going to be fairly complex.
	//You will need to create an aircraft of a certain size (using the version of the constructor that lets you specify)
	//Then create passengers of each class (First, Economy, ...) to fill that aircraft.
	//Then you will need to run upgradeBooking and see if the outcome is what is expected.
}
	
