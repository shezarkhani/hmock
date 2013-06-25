package hmock.http;

import java.io.InputStream;

public interface ResponseBuilder {

	public ResponseBuilder status(int status);
	public ResponseBuilder header(String name, String value);
	public ResponseBuilder body(String body);
	public ResponseBuilder body(InputStream body);
}
