package uts.wsd.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 2/5/17
 */
@Getter
@Setter
public final class ResponseWrapper<P> {

	public enum Response {

		SUCCESS,
		EXCEPTION,
		FAILURE

	}

	private Response response;
	private List<P> messages;

	private ResponseWrapper(Response response, List<P> messages) {
		this.response = response;
		this.messages = messages;
	}

	//needed for SOAP...
	public ResponseWrapper() {}

	private static <T> ResponseWrapper<T> response(Response response, T... array) {
		return new ResponseWrapper<>(response, Arrays.asList(array));
	}

	public static ResponseWrapper success() {
		return response(Response.SUCCESS);
	}

	public static <T> ResponseWrapper<T> success(T... messages) {
		return response(Response.SUCCESS, messages);
	}

	public static <T> ResponseWrapper<T> success(List<T> messages) {
		return new ResponseWrapper<>(Response.SUCCESS, messages);
	}

	public static <T> ResponseWrapper<T> exception(T... exceptions) {
		return response(Response.EXCEPTION, exceptions);
	}

	public static <T> ResponseWrapper<T> exception(List<T> exceptions) {
		return new ResponseWrapper<>(Response.EXCEPTION, exceptions);
	}

	public static <T> ResponseWrapper<T> failure(T... errors) {
		return response(Response.FAILURE, errors);
	}

	public static <T> ResponseWrapper<T> failure(List<T> errors) {
		return new ResponseWrapper<>(Response.FAILURE, errors);
	}

}
