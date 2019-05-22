package Pharse1;

public class NonCloseCommentException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonCloseCommentException(String err , int row , int column) {
		super(err+" at row : "+row+" , column : "+column);
	}
}
