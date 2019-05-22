package Pharse1;

public class StringOutOfBoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StringOutOfBoundException(String err , int row , int column) {
		super(err+" at row : "+row+" , column : "+column);
	}

}
