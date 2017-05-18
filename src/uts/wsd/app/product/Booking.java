package uts.wsd.app.product;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 17/5/17
 */
public interface Booking<U> {

	void reserve(U user) throws BookingException;

	void release(U user) throws BookingException;

	String identifier();

	int size();

	int capacity();

	default boolean available() {
		return size() < capacity();
	}

	class BookingException extends Exception {

		public BookingException(String message) {
			super(message);
		}

	}

}
