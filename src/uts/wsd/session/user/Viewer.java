package uts.wsd.session.user;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 2/5/17
 */
@Setter
@AllArgsConstructor
public final class Viewer implements User {

	@Override
	public Credentials getCredentials() {
		return null;
	}

}
