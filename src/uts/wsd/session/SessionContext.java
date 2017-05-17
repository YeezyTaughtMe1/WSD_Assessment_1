package uts.wsd.session;

import lombok.Getter;
import lombok.Setter;
import uts.wsd.session.user.User;
import uts.wsd.session.user.Viewer;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 3/5/17
 */
@Getter
@Setter
public final class SessionContext {

	private static final String ATTRIBUTE_KEY = "context";

	private User user;

	public SessionContext(User user) {
		this.user = user;
	}

	public SessionContext() {
		this(new Viewer());
	}

	public static SessionContext contextualise(HttpSession session) {
		SessionContext context = (SessionContext) Optional.ofNullable(session.getAttribute(ATTRIBUTE_KEY)).orElse(new SessionContext());
		session.setAttribute(ATTRIBUTE_KEY, context);
		return context;
	}
}
