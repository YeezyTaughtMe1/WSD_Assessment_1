package uts.wsd.session.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 2/5/17
 */
public interface User {

	Credentials getCredentials();

	default boolean isAuthenticated() {
		return !isViewer();
	}

	default boolean isViewer() {
		return this instanceof Viewer;
	}

	default boolean isCustomer() {
		return this instanceof Customer;
	}

	default boolean isAdministrator() {
		return this instanceof Administrator;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	final class Credentials {

		@XmlElement(name="username")
		private String username;
		@XmlElement(name="password")
		private String password;
		@XmlElement(name="email")
		private String email;
		@XmlElement(name="birthday")
		private String birthday;

	}

}
