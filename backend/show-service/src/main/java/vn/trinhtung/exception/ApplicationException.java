package vn.trinhtung.exception;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -3499478937866155007L;
    private String error;
    private Object data;

    public ApplicationException(String error, String message, Object data) {
        super(message);
        this.error = error;
        this.data = data;
    }

	public ApplicationException(String error, String message) {
		super(message);
		this.error = error;
	}

}
