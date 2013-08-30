package hmock;

import static org.junit.Assert.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class HMockTest {

	@Test
	public void testCanHandleParamlessGetRequest() throws Exception {
		
		HMock
			.get("/employees")
		.respond()
			.body("A,B,C");
		
		URL request = new URL("http://localhost:7357/employees");
		HttpURLConnection conn = (HttpURLConnection) request.openConnection();
		
		String response = IOUtils.toString(conn.getInputStream());
		
		assertEquals("A,B,C", response);
	}
	
	@Test
	public void test404IfWrongHttpMethod() throws Exception {
		
		HMock.get("/employees/nopost").respond().body("A,B,C");
		
		URL request = new URL("http://localhost:7357/employees/nopost");
		HttpURLConnection conn = (HttpURLConnection) request.openConnection();
		conn.setRequestMethod("POST");
		assertEquals(404, conn.getResponseCode());
	}
	
	@Test
	public void testWithHeaders() throws Exception {
		HMock
			.get("/employees/headers")
		.respond()
			.body("{\"A\",\"b\",\"C\"}")
			.header("Content-Type", "application/json")
			.header("X-Server", "HMock");
		
		URL request = new URL("http://localhost:7357/employees/headers");
		HttpURLConnection conn = (HttpURLConnection) request.openConnection();
		
		assertEquals("application/json", conn.getHeaderField("Content-Type"));
		assertEquals("HMock", conn.getHeaderField("X-Server"));
	}
	
}
