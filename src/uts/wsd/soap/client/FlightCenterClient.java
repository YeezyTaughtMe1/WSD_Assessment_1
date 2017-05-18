package uts.wsd.soap.client;

import javax.xml.ws.BindingProvider;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 18/5/17
 */
public class FlightCenterClient {

	public static void main(String[] argv) {
		FlightCenterAPI api = new FlightCenterAPI_Service().getFlightCenterAPI();
		((BindingProvider) api).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

		print("LOGOUT", api.logout());

		print("LOGIN", api.login("hello@goodbye.com", "world"));

		print("LOGOUT", api.logout());

		print("REGISTER", api.register("Harrison", "password", "test@test.com", "18/08/1997"));

		print("LOGOUT", api.logout());

		print("LOGIN", api.login("test@test.com", "wrong_password"));

		print("LOGIN", api.login("test@test.com", "password"));

		print("LOGOUT", api.logout());

	}

	private static void print(String method, ResponseWrapper response) {
		System.out.println(method);
		print(response);
	}

	private static void print(ResponseWrapper response) {
		System.out.println("\t" + response.getResponse().value());
		response.getMessages().forEach(l -> System.out.println("\t\t" + l));
	}
}
