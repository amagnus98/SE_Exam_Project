package system.model.domain;

/**
 * The class represents an exception that is thrown to indicate that the 
 * intended operation is not allowed. In most cases, it is thrown, when the
 * administrator is not logged in, but the operation requires administrator authorization.
 */
public class OperationNotAllowedException extends Exception {

	/**
	 * A new exception is constructed with error message errorMessage.
	 * @param errorMessage the error message of the exception
	 */
	public OperationNotAllowedException(String errorMessage) {
		super(errorMessage);
	}

}