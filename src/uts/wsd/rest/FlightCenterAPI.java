package uts.wsd.rest;

import uts.wsd.session.SessionContext;
import uts.wsd.session.user.Customer;
import uts.wsd.session.user.User;
import uts.wsd.session.user.booking.Booking;
import uts.wsd.util.ResponseWrapper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static uts.wsd.session.SessionContext.contextualise;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 2/5/17
 */
//TODO refactoring
//TODO modularisation of methods / code
//TODO security (CSRF, XSS, sanitisation, validation)
//TODO rate limiting
@Path("/api")
public final class FlightCenterAPI {

	private static final String ERR_LOGGED_IN = "You can't be perform that action whilst logged in.";
	private static final String ERR_LOGGED_OUT = "You need to be logged in to perform that action.";

	@Context
	private ServletContext context;

	@POST
	@Path("flights")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public List<Booking> bookings(@Context HttpServletRequest request,
							  @FormParam("username") String username,
							  @FormParam("status") Boolean available,
							  @FormParam("limit") Integer limit,
							  @FormParam("numberofcustomers") Integer customers) {

		//TODO

		return Arrays.asList(null, null);
	}

	@GET
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseWrapper logout(@Context HttpServletRequest request) {
		final HttpSession session = request.getSession();
		final SessionContext context = contextualise(session);

		//TODO check CSRF token

		session.invalidate();

		return context.getUser().isAuthenticated() ? ResponseWrapper.success() : ResponseWrapper.failure(ERR_LOGGED_OUT);
	}

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseWrapper register(@Context HttpServletRequest request,
									@FormParam("name") String name,
									@FormParam("password") String password,
									@FormParam("email") String email,
									@FormParam("birthday") String birthday) {
		final HttpSession session = request.getSession();
		final SessionContext context = contextualise(session);

		//TODO check CSRF token

		//TODO
		//there are two use cases for this method
		//1). a non-registered user attempting to register
		//2). administrative action -> register

		if (context.getUser().isAuthenticated() && !context.getUser().isAdministrator())
			return ResponseWrapper.failure(ERR_LOGGED_IN);

		final List<String> messages = new ArrayList<>();

		//TODO
		//properly sanitise and validate all parameters
		if (name == null || name.isEmpty())//TODO inadequate
			messages.add("You must provide a valid name.");

		if (password == null || password.isEmpty())//TODO inadequate
			messages.add("You must provide a valid password.");

		if (email == null || email.isEmpty())//TODO inadequate
			messages.add("You must provide a valid email address.");

		if (birthday == null || birthday.isEmpty())//TODO inadequate
			messages.add("You must provide a date of birth.");

		if (!messages.isEmpty())
			return ResponseWrapper.failure(messages);

		//TODO
		//ensure no user with the same username already exists

		//TODO
		//if the above does not fail i.e, user == null
		//store the user within the database (or file)
		final User user = new Customer(new User.Credentials(name, password, email, birthday));

		if (context.getUser().isAdministrator())
			return ResponseWrapper.success(String.format("Successfully registered %s with username \"%s\" and password \"%s\".", user.getCredentials().getUsername(), user.getCredentials().getEmail(), user.getCredentials().getPassword()));

		context.setUser(user);
		return ResponseWrapper.success(String.format("Welcome, %s.", user.getCredentials().getUsername()));
	}

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseWrapper login(@Context HttpServletRequest request,
								 @FormParam("email") String email,
								 @FormParam("password") String password) {
		final HttpSession session = request.getSession();
		final SessionContext context = contextualise(session);

		//TODO check CSRF token

		if (context.getUser().isAuthenticated())
			return ResponseWrapper.failure(ERR_LOGGED_IN);

		final List<String> messages = new ArrayList<>();

		//TODO
		//properly sanitise and validate all parameters
		if (email == null || email.isEmpty())//TODO inadequate
			messages.add("You must provide a valid email address.");

		if (password == null || password.isEmpty())//TODO inadequate
			messages.add("You must provide a valid password.");

		if (!messages.isEmpty())
			return ResponseWrapper.failure(messages);

		//TODO
		//ensure a user with matching credentials exists

		//TODO
		//if the above does not fail i.e, user != null
		//update the context's user object

		return ResponseWrapper.success();//TODO welcome, username
	}

}
