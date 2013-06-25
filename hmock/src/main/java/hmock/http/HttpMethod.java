package hmock.http;

public interface HttpMethod {

	public RequestBuilder get();
	public RequestBuilder post();
	public RequestBuilder delete();
	public RequestBuilder head();
	public RequestBuilder put();
	public RequestBuilder options();	
}
