package hmock;

public class HMockException extends RuntimeException {

	private static final long serialVersionUID = -2449010401142940613L;

	public HMockException(String msg) {
		super(msg);
	}
	
	public HMockException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
