package uts.wsd.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uts.wsd.app.product.flight.Flight;
import uts.wsd.app.user.User;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 18/5/17
 */
@Getter
@AllArgsConstructor
public class FlightCenterApp {

	public static final String ATTRIBUTE_KEY = "FlightCenterApp";

	//TODO load from XML @Max
	//TODO make users a group class, as well as flights in order to encapsulate functionality
	private final List<User> users = new LinkedList<>();
	private final List<Flight> flights = new LinkedList<>();



}
