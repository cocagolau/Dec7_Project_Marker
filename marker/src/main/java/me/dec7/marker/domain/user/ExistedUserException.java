package me.dec7.marker.domain.user;

public class ExistedUserException extends Exception {
	
	private static final long serialVersionUID = 579316126882160984L;

	public ExistedUserException() {
		super();
	}

	public ExistedUserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExistedUserException(String arg0) {
		super(arg0);
	}

	public ExistedUserException(Throwable arg0) {
		super(arg0);
	}
}
