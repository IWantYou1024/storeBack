package cn.itcast.exception;

public class SysException extends Exception {
	private static final long serialVersionUID = -8713647871972102686L;
	private String message;

	public String getMessage() {
		return message;
	}

	public SysException(String message) {
		super(message);
	}

}
