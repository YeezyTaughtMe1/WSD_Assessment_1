package uts.wsd.session.user.booking;

import uts.wsd.session.user.User;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 17/5/17
 */
public interface Booking {

	void reserve(User user) throws BookingException;

	void unreserve(User user) throws BookingException;

	String identifier();

	int size();

	int capacity();

	default boolean available() {
		return size() < capacity();
	}

	class BookingException extends Exception {

		protected BookingException(String message) {
			super(message);
		}

	}

}
