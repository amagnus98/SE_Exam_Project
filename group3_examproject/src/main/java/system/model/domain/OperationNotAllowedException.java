package system.model.domain;
// Based on code from Hubert Baumeister

// the purpose of this class is to handle exceptions
public class OperationNotAllowedException extends Exception {

	
	public OperationNotAllowedException(String errorMessage) {
		super(errorMessage);
	}

}