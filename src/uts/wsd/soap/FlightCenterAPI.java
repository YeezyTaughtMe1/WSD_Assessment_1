package uts.wsd.soap;

import uts.wsd.session.SessionContext;
import uts.wsd.util.ResponseWrapper;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
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

	private static final String ERR_LOGGED_IN = "You can't be perform that action whilst logged in.";
	private static final String ERR_LOGGED_OUT = "You need to be logged in to perform that action.";

	@Resource
	private WebServiceContext context;

	@WebMethod(operationName = "logout")
	public ResponseWrapper logout() {
		HttpSession session = session();
		SessionContext context = SessionContext.contextualise(session);

		session.invalidate();

		return context.getUser().isAuthenticated() ? ResponseWrapper.success() : ResponseWrapper.failure(ERR_LOGGED_OUT);
	}

	@WebMethod(operationName = "login")
	public ResponseWrapper login(@WebParam(name = "email") String email,
								 @WebParam(name = "password") String password) {

		SessionContext context = SessionContext.contextualise(session());

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

	private HttpSession session() {
		MessageContext handler = context.getMessageContext();
		HttpSession session = ((HttpServletRequest) handler.get(MessageContext.SERVLET_REQUEST)).getSession();
		if (session == null)
			throw new WebServiceException("No HTTP session found.");
		return session;
	}

}
