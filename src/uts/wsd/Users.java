package uts.wsd;

import uts.wsd.session.user.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 2/5/17
 */
//TODO XML
//TODO other methods
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public final class Users {

	private final Map<String, User> users = new HashMap<>();

	public User login(final String email, final String password) {
		return Optional.ofNullable(users.get(email))
				.filter(user -> user.getCredentials().getEmail().equals(email) && user.getCredentials().getPassword().equals(password))
				.orElse(null);
	}

}
