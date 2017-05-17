package uts.wsd.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 17/5/17
 */
@WebService(serviceName = "soap/api")
public class FlightCenterAPI {

	@WebMethod(operationName = "hello")
	public String hello() {
		return "Hello World";
	}

}
