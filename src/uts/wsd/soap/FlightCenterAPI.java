package uts.wsd.soap;

import uts.wsd.session.SessionContext;
import uts.wsd.util.ResponseWrapper;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;

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

	private HttpSession session() {
		MessageContext handler = context.getMessageContext();
		HttpSession session = ((HttpServletRequest) handler.get(MessageContext.SERVLET_REQUEST)).getSession();
		if (session == null)
			throw new WebServiceException("No HTTP session found.");
		return session;
	}

}
