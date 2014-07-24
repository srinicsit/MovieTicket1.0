package com.avihs.movie.web.ticket.payment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.avihs.movie.business.booking_ticket.model.BookingTicket;
import com.avihs.movie.business.booking_ticket.service.BookingTicketService;
import com.avihs.movie.business.email.service.EmailService;
import com.avihs.movie.business.model.BookingStatus;
import com.avihs.movie.business.model.SeatStatus;
import com.avihs.movie.business.model.TicketSummary;
import com.avihs.movie.business.seats.model.Seats;
import com.avihs.movie.web.ticket.payment.form.PaymentForm;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	public static final String PAYMENT_PAGE = "payment_gateway";

	@Autowired
	private EmailService emailService;

	private SimpleDateFormat dtWithWeekdayHrsMins = new SimpleDateFormat(
			"EEE, dd MM yyyy HH:mm:ss a");

	private SimpleDateFormat dtWithWeekday = new SimpleDateFormat(
			"EEE, MMM d, yyyy");

	private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

	@Autowired
	private BookingTicketService bookingTicketService;

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid PaymentForm paymentForm,
			BindingResult bindingResult, Model model) {

		boolean processPayment = processPayment();
		// check the payment once the payment is success then update the status
		Integer transactionId = paymentForm.getTransactionId();
		List<BookingTicket> bookingTickets = bookingTicketService
				.getBookingTickets(transactionId);

		for (BookingTicket bookingTicket : bookingTickets) {
			if (processPayment) {
				bookingTicket.setBookingStatus(BookingStatus.CONFIRMED);
			} else {
				bookingTicket.setBookingStatus(BookingStatus.REJECTED);
				bookingTicket.getSeatStatus().setSeatStatus(
						SeatStatus.AVAILABLE);
			}
			bookingTicketService.update(bookingTicket);
			sendEmail(bookingTickets);
		}

		return PAYMENT_PAGE;
	}

	private boolean processPayment() {

		return true;
	}

	private void sendEmail(List<BookingTicket> bookingTickets) {
		Map context = new HashMap();
		context.put("ticketSummary", getTicketSummary(bookingTickets));
		emailService.sendMail(context, "ticket.vm");
	}

	private void sendMsg() {

	}

	private TicketSummary getTicketSummary(List<BookingTicket> bookingTickets) {
		TicketSummary ticketSummary = new TicketSummary();
		StringBuilder seatsBuilder = new StringBuilder();
		for (BookingTicket bookingTicket : bookingTickets) {
			Seats seat = bookingTicket.getSeatStatus().getSeat();
			seatsBuilder.append(seat.getRow());
			seatsBuilder.append(seat.getColNum());
			seatsBuilder.append(",");
		}
		BookingTicket bookingTicket = bookingTickets.get(0);
		String seats = seatsBuilder.substring(0, seatsBuilder.length() - 1);

		ticketSummary.setSeatNumbers(seats);
		ticketSummary.setSeatClass(bookingTicket.getSeatStatus().getSeat()
				.getRow().getSeatClassType().getSeatClsName());
		String bookingId = bookingTicket.getTransaction().getId() + "";
		ticketSummary.setBookingId(bookingId);

		String city = bookingTicket.getSeatStatus().getMovieScreen()
				.getScreen().getTheater().getLocation().getName();
		ticketSummary.setAddress(city);
		ticketSummary.setBookingTime(dtWithWeekdayHrsMins.format(new Date()));
		ticketSummary.setCity(city);
		ticketSummary.setConfirmationId(bookingTicket.getTransaction().getId()
				.longValue());
		ticketSummary.setMovieName(bookingTicket.getSeatStatus()
				.getMovieScreen().getMovie().getMovieName());
		ticketSummary.setPaymentMode("Credit Card");
		ticketSummary.setScreenName(bookingTicket.getSeatStatus()
				.getMovieScreen().getScreen().getName());		
		ticketSummary.setShowDate(dtWithWeekday.format(bookingTicket.getSeatStatus().getMovieScreen().getShowDate()));
		
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm a");
		ticketSummary.setShowStartTime(tf.format(new Date()));
		ticketSummary.setTheaterName("Gopalan Mall");
		ticketSummary.setTpinOrKioskCode(bookingId);

		ticketSummary.getOrderSummary().setTicketCost(500);
		ticketSummary.getOrderSummary().setInternetHandlingFee(67.42f);
		ticketSummary.getOrderSummary().setOtherCharge(1.00f);
		ticketSummary.getOrderSummary().setDiscount(50);

		return ticketSummary;

	}

	private TicketSummary getTicketSummary1() {
		SimpleDateFormat dt2 = new SimpleDateFormat(
				"EEE, dd MM yyyy HH:mm:ss a");
		TicketSummary ticketSummary = new TicketSummary();
		String bookingId = " W4NQU4M";
		ticketSummary.setBookingId(bookingId);
		ticketSummary.setAddress("old madras road");
		ticketSummary.setBookingTime(dt2.format(new Date()));
		ticketSummary.setCity("Bangalore");
		ticketSummary.setConfirmationId(1234l);
		ticketSummary.setMovieName("Manam");
		ticketSummary.setPaymentMode("Credit Card");
		ticketSummary.setScreenName("Screen1");
		ticketSummary.setSeatClass("Balcony");
		ticketSummary.setSeatNumbers("A1,A2");
		SimpleDateFormat dt1 = new SimpleDateFormat("EEE, MMM d, yyyy");
		ticketSummary.setShowDate(dt1.format(new Date()));
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm a");
		ticketSummary.setShowStartTime(tf.format(new Date()));
		ticketSummary.setTheaterName("Gopalan Mall");
		ticketSummary.setTpinOrKioskCode(bookingId);

		ticketSummary.getOrderSummary().setTicketCost(500);
		ticketSummary.getOrderSummary().setInternetHandlingFee(67.42f);
		ticketSummary.getOrderSummary().setOtherCharge(1.00f);
		ticketSummary.getOrderSummary().setDiscount(50);

		return ticketSummary;

	}

	@RequestMapping(method = RequestMethod.GET)
	public String load(Model model,
			@RequestParam("transactionId") Integer transactionId) {
		PaymentForm paymentForm = new PaymentForm();

		paymentForm.setTransactionId(transactionId);
		model.addAttribute("paymentForm", paymentForm);

		return PAYMENT_PAGE;
	}
}
