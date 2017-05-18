package uts.wsd.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 2/5/17
 */
@Getter
@Setter
public final class ResponseWrapper {

	public enum Response {

		SUCCESS,
		EXCEPTION,
		FAILURE

	}

	private Response response;
	private String[] messages;

	private ResponseWrapper(Response response, String... messages) {
		this.response = response;
		this.messages = messages;
	}

	//needed for SOAP...
	public ResponseWrapper() {}

	private static ResponseWrapper response(Response response, String... array) {
		return new ResponseWrapper(response, array);
	}

	public static ResponseWrapper success() {
		return response(Response.SUCCESS);
	}

	public static ResponseWrapper success(String... messages) {
		return response(Response.SUCCESS, messages);
	}

	public static ResponseWrapper success(List<String> messages) {
		return response(Response.SUCCESS, messages.toArray(new String[messages.size()]));
	}

	public static ResponseWrapper exception(String... exceptions) {
		return response(Response.EXCEPTION, exceptions);
	}

	public static ResponseWrapper exception(List<String> exceptions) {
		return response(Response.EXCEPTION, exceptions.toArray(new String[exceptions.size()]));
	}

	public static ResponseWrapper failure(String... errors) {
		return response(Response.FAILURE, errors);
	}

	public static ResponseWrapper failure(List<String> errors) {
		return response(Response.FAILURE, errors.toArray(new String[errors.size()]));
	}

}
