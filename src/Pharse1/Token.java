package Pharse1;

import java.util.HashMap;
import java.util.Map;

public class Token {
	public static Map<String,String> tokenDistionary ;
	private int idNumber;
    private int beginIndex;
    private int endIndex;
    private int row;
    private String tokenType;
    private String tokenString;
    
    
    
    static {
    	tokenDistionary = new HashMap<String, String>();
    	   tokenDistionary.put("COMMENT", "\\(\\*.*\\*\\).*");
           tokenDistionary.put("EOF", "\\Z");
           tokenDistionary.put("SEMI", ";");
           tokenDistionary.put("COLON", ":");
           tokenDistionary.put("COMMMA", ",");
           tokenDistionary.put("DOT", "\\.");
           tokenDistionary.put("LPARE", "\\(");
           tokenDistionary.put("RPARE", "\\)");
           tokenDistionary.put("LT", "<");
           tokenDistionary.put("RT", ">");
           tokenDistionary.put("EQ", "=");
           tokenDistionary.put("MINUS", "-");
           tokenDistionary.put("PLUS", "\\+");
           tokenDistionary.put("TIME", "\\*");
           tokenDistionary.put("DOTDOT", "\\..");
           tokenDistionary.put("COLEQ", ":=");
           tokenDistionary.put("LE", "<=");
           tokenDistionary.put("GE", ">=");
           tokenDistionary.put("NE", "<>");
           tokenDistionary.put("IDENTIFIER", "([a-zA-Z])+([a-zA-Z0-9])*");
           tokenDistionary.put("ICONST", "[0-9]+");
           tokenDistionary.put("CCONST", "'.?'");
           tokenDistionary.put("SCONST", "'.+'");
           tokenDistionary.put("AND", "and");
           tokenDistionary.put("ARRAY", "array");
           tokenDistionary.put("BEGIN", "begin");
           tokenDistionary.put("CONST", "constant");
           tokenDistionary.put("DIV", "div");
           tokenDistionary.put("DOWNTO", "downto");
           tokenDistionary.put("ELSE", "else");
           tokenDistionary.put("ELSIF", "elsif");
           tokenDistionary.put("END", "end");
           tokenDistionary.put("ENDIF", "endif");
           tokenDistionary.put("ENDLOOP", "endloop");
           tokenDistionary.put("ENDREC", "endrec");
           tokenDistionary.put("EXIT", "exit");
           tokenDistionary.put("FOR", "for");
           tokenDistionary.put("FORWARD", "forward");
           tokenDistionary.put("FUNCTION", "function");
           tokenDistionary.put("IF", "if");
           tokenDistionary.put("IS", "is");
           tokenDistionary.put("LOOP", "loop");
           tokenDistionary.put("NOT", "not");
           tokenDistionary.put("OF", "of");
           tokenDistionary.put("OR", "or");
           tokenDistionary.put("PROCEDURE", "procedure");
           tokenDistionary.put("PROGRAM", "program");
           tokenDistionary.put("RECORD", "record");
           tokenDistionary.put("REPEAT", "repeat");
           tokenDistionary.put("RETURN", "return");
           tokenDistionary.put("THEN", "then");
           tokenDistionary.put("TO", "to");
           tokenDistionary.put("TYPE", "type");
           tokenDistionary.put("UNTIL", "until");
           tokenDistionary.put("VAR", "var");
           tokenDistionary.put("WHILE", "while");
           tokenDistionary.put("NEWLINE", "\\n");
           tokenDistionary.put("EMPTY", "");
           tokenDistionary.put("SPACE", " +");
    }
    
	public Token(int idNumber, int beginIndex, int endIndex, int row, String tokenType, String tokenString) {
		super();
		this.idNumber = idNumber;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		this.row = row;
		this.tokenType = tokenType;
		this.tokenString = tokenString;
	}
	
	
	
	public Token(int beginIndex, int endIndex, int row, String tokenType, String tokenString) {
		super();
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		this.row = row;
		this.tokenType = tokenType;
		this.tokenString = tokenString;
	}



	public int getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(int idNumber) {
		switch (this.tokenType) {
        case "ICONST":
            this.idNumber = Integer.valueOf(this.tokenString) + this.hashCode();
            break;
        case "CCONST":
            this.idNumber = Character.getNumericValue(this.tokenString.charAt(0) + +this.hashCode());
            break;
        case "SCONST":
            this.idNumber = idNumber + this.hashCode();
            break;
        case "IDENTIFIER":
            this.idNumber = idNumber + this.hashCode();
            break;
        default:
            this.idNumber = this.hashCode();
            break;
		}
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getTokenString() {
		return tokenString;
	}
	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
    
    
}
