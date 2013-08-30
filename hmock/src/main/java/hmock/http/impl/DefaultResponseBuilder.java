package hmock.http.impl;

import hmock.HMockException;
import hmock.http.ResponseBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class DefaultResponseBuilder implements ResponseBuilder {

	private Map<String, String> headers = new HashMap<String, String>();
	private byte[] _buffer;
	private int status = 200;
	
	@Override
	public ResponseBuilder status(final int status) {

		this.status = status;
		return this;
	}

	@Override
	public ResponseBuilder header(final String name, final String value) {
		headers.put(name, value);
		return this;
	}

	@Override
	public ResponseBuilder body(final String body) {
		
		try {
			body(new ByteArrayInputStream(body.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			
			throw new HMockException("should never get this error", e);
		}
		
		return this;
	}

	@Override
	public ResponseBuilder body(final InputStream body) {
		
		try {
			_buffer = IOUtils.toByteArray(body);
		} catch (IOException e) {
			throw new HMockException("can't create response", e);
		}
		
		return this;
	}
	
	public InputStream getResponseBody() {
		
		return new ByteArrayInputStream(_buffer);
	}
	
	public Map<String, String> getResponseHeaders() {
		return this.headers;
	}
	
	public int getStatus() {
		
		return this.status;
	}
}
