package hmock.http;

import org.hamcrest.Matcher;

public interface RequestBuilder {
	
	public RequestBuilder pathparam(String name, Matcher<String> value);
	public RequestBuilder param(String name, Matcher<String> value);
	public RequestBuilder header(String name, Matcher<String> value);
	
	public ResponseBuilder respond();
}
