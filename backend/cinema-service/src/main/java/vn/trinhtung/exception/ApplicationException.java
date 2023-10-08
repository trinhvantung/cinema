package vn.trinhtung.exception;

public class ApplicationException  extends RuntimeException {
	private static final long serialVersionUID = -3499478937866155007L;
	private String error;

	public ApplicationException(String error, String message) {
		super(message);
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
