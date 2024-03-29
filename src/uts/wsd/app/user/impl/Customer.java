package uts.wsd.app.user.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uts.wsd.app.user.User;

import java.io.Serializable;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 2/5/17
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Customer implements User, Serializable {

	private Credentials credentials;

	@Override
	public User.Credentials getCredentials() {
		return credentials;
	}

}
