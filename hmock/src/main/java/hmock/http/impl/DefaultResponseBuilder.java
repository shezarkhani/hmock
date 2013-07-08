package hmock.http.impl;

import hmock.HMockException;
import hmock.http.ResponseBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;

public class DefaultResponseBuilder implements ResponseBuilder {

	private byte[] _buffer;
	private int status = 200;
	
	@Override
	public ResponseBuilder status(final int status) {

		this.status = status;
		
		return this;
	}

	@Override
	public ResponseBuilder header(final String name, final String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBuilder body(final String body) {
		
		ResponseBuilder rBuilder = null;
		try {
			rBuilder = body(new ByteArrayInputStream(body.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			
			throw new HMockException("should never get this error", e);
		}
		
		return rBuilder;
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
	
	public int getStatus() {
		
		return this.status;
	}
}
