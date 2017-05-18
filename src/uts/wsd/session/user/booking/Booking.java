package uts.wsd.session.user.booking;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 17/5/17
 */
public interface Booking<U, P> {

	void reserve(U user, P product) throws BookingException;

	void release(U user, P product) throws BookingException;

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
