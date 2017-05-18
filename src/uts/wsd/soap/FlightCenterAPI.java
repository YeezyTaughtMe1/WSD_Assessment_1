package uts.wsd.soap;

import uts.wsd.app.FlightCenterApp;
import uts.wsd.app.user.session.SessionContext;
import uts.wsd.app.user.impl.Customer;
import uts.wsd.app.user.User;
import uts.wsd.util.ResponseWrapper;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 17/5/17
 */
@WebService(serviceName = "soap/api")
public class FlightCenterAPI {

	//TODO refactoring
	//TODO modularisation of methods / code
	//TODO security (CSRF, XSS, sanitisation, validation)
	//TODO rate limiting

	private static final String ERR_LOGGED_IN = "You can't be perform that action whilst logged in.";
	private static final String ERR_LOGGED_OUT = "You need to be logged in to perform that action.";

	@Resource
	private WebServiceContext context;

	@WebMethod(operationName = "logout")
	public ResponseWrapper<String> logout() {
		HttpSession session = session();
		SessionContext context = SessionContext.contextualise(session);

		session.invalidate();

		return context.getUser().isAuthenticated() ? ResponseWrapper.success() : ResponseWrapper.failure(ERR_LOGGED_OUT);
	}

	@WebMethod(operationName = "login")
	public ResponseWrapper<String> login(@WebParam(name = "email") String email,
										 @WebParam(name = "password") String password) {

		SessionContext context = SessionContext.contextualise(session());

		if (context.getUser().isAuthenticated())
			return ResponseWrapper.failure(ERR_LOGGED_IN);

		List<String> messages = new ArrayList<>();

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

		//messages.add(Welcome user.getCredentials().getName()

		return ResponseWrapper.success(messages);
	}

	@WebMethod(operationName = "register")
	public ResponseWrapper<String> register(@WebParam(name = "name") String name,
											@WebParam(name = "password") String password,
											@WebParam(name = "email") String email,
											@WebParam(name = "birthday") String birthday) {

		SessionContext context = SessionContext.contextualise(session());

		//TODO
		//there are two use cases for this method
		//1). a non-registered user attempting to register
		//2). administrative action -> register

		if (context.getUser().isAuthenticated() && !context.getUser().isAdministrator())
			return ResponseWrapper.failure(ERR_LOGGED_IN);

		List<String> messages = new ArrayList<>();

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
		User user = new Customer(new User.Credentials(name, password, email, birthday));

		if (context.getUser().isAdministrator())
			messages.add(String.format("Successfully registered %s with username \"%s\" and password \"%s\".", user.getCredentials().getUsername(), user.getCredentials().getEmail(), user.getCredentials().getPassword()));
		else {
			context.setUser(user);
			messages.add(String.format("Welcome, %s.", user.getCredentials().getUsername()));
		}
		return ResponseWrapper.success(messages);
	}

	private HttpSession session() {
		MessageContext handler = context.getMessageContext();
		HttpSession session = ((HttpServletRequest) handler.get(MessageContext.SERVLET_REQUEST)).getSession();
		if (session == null)
			throw new WebServiceException("No HTTP session found.");
		return session;
	}

	private FlightCenterApp app() {

		ServletContext application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

		boolean initiated = application.getAttribute(FlightCenterApp.ATTRIBUTE_KEY) != null;

		if (!initiated) {
			synchronized ((ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT)) {
				application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
				initiated = application.getAttribute(FlightCenterApp.ATTRIBUTE_KEY) != null;
				if (!initiated) {
					FlightCenterApp app = new FlightCenterApp();
					application.setAttribute(FlightCenterApp.ATTRIBUTE_KEY, app);
				}
			}
		}

		return (FlightCenterApp) application.getAttribute(FlightCenterApp.ATTRIBUTE_KEY);
	}

}
