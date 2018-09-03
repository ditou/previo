package exception;

public class GenericRemoteException extends Exception {

	private static final long serialVersionUID = 3789757845219753970L;
	private static final String EXCEPTION_MESSAGE = "No se pudo realizar la operación, por favor intente nuevamente o contáctese con un administrador.";
	
	public GenericRemoteException(Throwable t) {
		super(EXCEPTION_MESSAGE, t);
	}
	
	public GenericRemoteException(String message, Throwable t) {
		super(message, t);
	}

}
