package uts.wsd.soap.client;

import javax.xml.ws.BindingProvider;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 18/5/17
 */
public class FlightCenterClient {

	public static void main(String[] argv) {
		FlightCenterAPI api = new FlightCenterService().getFlightCenterAPIPort();
		((BindingProvider) api).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

		print("LOGOUT", api.logout());

		print("LOGIN", api.login("hello", "world"));

		print("LOGOUT", api.logout());

		print("LOGOUT", api.logout());

	}

	private static void print(String method, ResponseWrapper response) {
		System.out.println("[" + method +"]");
		print(response);
	}

	private static void print(ResponseWrapper response) {
		System.out.println(response.getResponse().value());
		response.getMessages().forEach(System.out::println);
	}
}
