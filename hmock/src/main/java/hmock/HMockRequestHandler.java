package hmock;

import hmock.http.impl.BaseRequestBuilder;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.DefaultHandler;

class HMockRequestHandler extends DefaultHandler {

	private ArrayList<BaseRequestBuilder> _mockRequests = new ArrayList<BaseRequestBuilder>();
	
	@Override
	public void handle(
			String target, 
			Request baseRequest,
			HttpServletRequest request, 
			HttpServletResponse response)
	throws IOException, ServletException {
	
		try {
			for (BaseRequestBuilder mockRequest : _mockRequests) {
				
				if (!mockRequest.canHandle(request)) {
					continue;
				}
				
				mockRequest.handle(request, response);
				baseRequest.setHandled(true);
				return;
			}
		} catch (Throwable e) {
			//if anything goes wrong does requesting handling, print out the stack trace for debugging.
			e.printStackTrace();
		}
	}

	public void addRequest(BaseRequestBuilder req) {
		
		_mockRequests.add(req);
	}
}