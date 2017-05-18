package uts.wsd.app.product.flight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uts.wsd.app.product.Booking;
import uts.wsd.app.user.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 18/5/17
 */
@Setter
@Getter
@AllArgsConstructor
public class Flight implements Booking<User> {

	private final List<User> users = new LinkedList<>();

	private int capacity;
	private String identifier;
	private Itinerary departure, arrival;

	@Override
	public void reserve(User user) throws BookingException {
		if (users.contains(user)) {
			throw new BookingException(String.format("You have already reserved a booking for flight \'%s\'.", getIdentifier()));
		}
		if (size() < capacity()) {
			users.add(user);
		} else {
			throw new BookingException(String.format("Flight \'%s\' is full.", identifier()));
		}
	}

	@Override
	public void release(User user) throws BookingException {
		if (!users.remove(user))
			throw new BookingException(String.format("You did not have a reservation for flight \'%s\'.", identifier()));
	}

	@Override
	public String identifier() {
		return identifier;
	}

	@Override
	public int size() {
		return users.size();
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Getter
	@AllArgsConstructor
	public static class Itinerary {

		private final String location;
		private final Date date;

	}

}
