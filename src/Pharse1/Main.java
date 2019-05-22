package Pharse1;


public class Main {
	
	public static void main(String args[]) {
		Lexer lexer = new Lexer();
		try {
			try {
				lexer.readFile("Test01.txt");
			} catch (StringOutOfBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NonCloseCommentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonRecognizableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
