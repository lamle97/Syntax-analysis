package Pharse1;

public class NonRecognizableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonRecognizableException(String err , int row , int column) {
		super(err+" at row : "+row+" , column : "+column);
	}
	
}
