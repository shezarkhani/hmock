package hmock.http.impl;

import javax.servlet.http.HttpServletRequest;

import hmock.http.GetRequestBuilder;

public class GetRequestBuilderImpl extends BaseRequestBuilder implements GetRequestBuilder {

	public GetRequestBuilderImpl(String path) {
		
		super (path);
	}

	@Override
	public boolean canHandle(HttpServletRequest request) {

		return "GET".equalsIgnoreCase(request.getMethod()) && super.canHandle(request);
	}
}
