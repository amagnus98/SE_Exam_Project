package system.model.domain;
// Based on code from Hubert Baumeister

// the purpose of this class is to handle exceptions
public class OperationNotAllowedException extends Exception {

	/**
	 * A new exception is constructed with error message errorMessage.
	 * @param errorMessage the error message of the exception
	 */
	public OperationNotAllowedException(String errorMessage) {
		super(errorMessage);
	}

}