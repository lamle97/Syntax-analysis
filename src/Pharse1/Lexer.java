package Pharse1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Lexer {
    private List<Token> tokenList;
    
    public Lexer() {
    	tokenList  = new ArrayList<Token>();
    }
    
    public void readFile(String path) throws NonCloseCommentException, NonRecognizableException, StringOutOfBoundException {
    	String file ="";
 
    	try {
    		File f = new File(path);
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				file = file +sc.nextLine()+"\n";
			}
			sc.close();
			lexer(file);
			writeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeFile() {
		try {
			File f = new File("output.txt");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			String result="";
			for(Token t : tokenList) {
				result = result+ t.getTokenString() + " " + t.getTokenType()+"number"+ " " + "[" + "Line"+ " "+ t.getRow() + ","  + " " +"BeginIndex:" + t.getBeginIndex()+"," +" "+ "EndIndex:"+t.getEndIndex()+ "]"+"\n";
				System.out.println(t.getTokenString() + " " + t.getTokenType()+"number"+ " " +"[" +"Line"+ " "+ t.getRow() + ","  + " " +"BeginIndex:" + t.getBeginIndex()+","+" "+ "EndIndex:"+t.getEndIndex()+"]");
			}
			bw.write(result);
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void lexer(String file) throws NonCloseCommentException, NonRecognizableException, StringOutOfBoundException {
    	int row = 1;
    	String tmp= "";
    	int index=1;
    	int columnEnd=0;
    	int columnBegin=0;
    	for(int i=0;i<file.length();i++) {
    		if("(".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			if("*".equals(Character.toString(file.charAt(i+1)))) {
    				tmp = tmp +"(*";
    				int j = i+2;
    				while(j<file.length()) {
    					if("*".equals(Character.toString(file.charAt(j+1))) == true && ")".equals(Character.toString(file.charAt(j+2))) == true) {
        					tmp = tmp+file.charAt(j)+"*)";
        					columnEnd = j+3;
        					i = j+2;
        					break;
        				}
        				else {
        					tmp = tmp + file.charAt(j);
        				}
    					j=j+1;
    				}
    				if(")".equals(Character.toString(tmp.charAt(tmp.length()-1)))==false && "*".equals(Character.toString(tmp.charAt(tmp.length()-2)))==false){
    					throw new NonCloseCommentException("You have error",row,columnBegin);
    				}
    				
    			}
    			else {
    				tmp = tmp + file.charAt(i);
    			}
    		}
    		else if(".".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			if(i+1<file.length()) {
    				if(".".equals(Character.toString(file.charAt(i+1)))) {
        				tmp = tmp+"..";
        				i=i+1;
        				columnEnd = i+1;
        			}
    			}
    			else {
    				tmp = tmp+".";
    				columnEnd = i+1;
    			}
    		}
    		else if(":".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			if(i+1<file.length()) {
    				if("=".equals(Character.toString(file.charAt(i+1)))) {
        				tmp = tmp+":=";
        				i=i+1;
        				columnEnd = i+1;
        			}
    			}
    			else {
    				tmp = tmp+":";
    				columnEnd = i+1;
    			}
    		}
    		else if("<".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			if(i+1<file.length()) {
    				if("=".equals(Character.toString(file.charAt(i+1)))) {
        				tmp = tmp+"<=";
        				i=i+1;
        				columnEnd = i+1;
        			}
        			else if(">".equals(Character.toString(file.charAt(i+1)))) {
        				tmp = tmp+"<>";
        				i=i+1;
        				columnEnd = i+1;
        			}
    			}
    			else {
    				tmp = tmp+"<";
    				columnEnd = i+1;
    			}
    		}
    		else if(">".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			if(i+1<file.length()) {
    				if("=".equals(Character.toString(file.charAt(i+1)))) {
        				tmp = tmp+">=";
        				i=i+1;
        				columnEnd = i+1;
        			}
    			}
    			else {
    				tmp = tmp+">";
    				columnEnd = i+1;
    			}
    		}
    		else if(";".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			columnEnd=i+1;
    			tmp = tmp+";";
    		}
    		else if(",".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			columnEnd=i+1;
    			tmp = tmp+",";
    		}
    		else if(")".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			columnEnd=i+1;
    			tmp = tmp+")";
    		}
    		else if("=".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			columnEnd=i+1;
    			tmp = tmp+"=";
    		}
    		else if("-".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			columnEnd=i+1;
    			tmp = tmp+"-";
    		}
    		else if("+".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			columnEnd=i+1;
    			tmp = tmp+"+";
    		}
    		else if("*".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			columnEnd=i+1;
    			tmp = tmp+"*";
    		}
    		else if("'".equals(Character.toString(file.charAt(i)))) {
    			columnBegin=i+1;
    			int j = i+1;
    			tmp = tmp+"'";
    			while(j<file.length()) {
    				if("'".equals(file.charAt(j+1))==true) {
    					tmp = tmp+file.charAt(j)+"'";
    					columnEnd = j+2;
    					i = j+1;
    					break;
    				}
    				else {
    					tmp = tmp+file.charAt(j);
    				}
    				j=j+1;
    			}
    			if("'".equals(tmp.charAt(tmp.length()-1))==false) {
    				throw new StringOutOfBoundException("You have error at",row,columnBegin);
    			}
    		}
    		else if(" ".equals(Character.toString(file.charAt(i)))) {
    			continue;
    		}
    		else if("\t".equals(Character.toString(file.charAt(i)))) {
    			continue;
    		}
    		else if("\n".equals(Character.toString(file.charAt(i)))) {
    			row++;
    			continue;
    		}
    		else {
    			columnBegin = i+1;
    			int j = i;
    			while(j<file.length()) {
    					if("\t".equals(Character.toString(file.charAt(j)))) {
    						i=j;
    						break;
    					}
    					if("\n".equals(Character.toString(file.charAt(j)))) {
    						i=j;
    						break;
    					}
    					if("\r".equals(Character.toString(file.charAt(j)))) {
    						i=j;
    						break;
    					}
    					if("\f".equals(Character.toString(file.charAt(j)))) {
    						i=j;
    						break;
    					}
    					if(" ".equals(Character.toString(file.charAt(j)))) {
    						i=j;
    						break;
    					}
    					if(",".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if(";".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if("=".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if(":".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if(">".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if("<".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if("-".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if("*".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					if("+".equals(Character.toString(file.charAt(j)))) {
    						i=j-1;
    						break;
    					}
    					tmp = tmp+file.charAt(j);
    					if(tmp.equals("and")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("array")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("begin")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("constant")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("div")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("downto")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("else")) {
        					String s="";
        					if(j+1 <file.length() && j+2 < file.length()) {
        						s = file.charAt(j+1)+""+file.charAt(j+2);
        					}
        					if(s.equals("if")) {
        						tmp = tmp+"if";
        						columnEnd = j+3;
        						i=j+2;
        						break;
        					}
        					else {
        						columnEnd = j+1;
            					i=j;
            					break;
        					}
        				}
        				else if(tmp.equals("end")) {
        					String s1="";
        					String s2="";
        					String s3="";
        					if(j+1 < file.length() && j+2 <file.length()) {
        						 s1 = file.charAt(j+1)+""+file.charAt(j+2);
        					}
        					if(j+1 < file.length() && j+2 <file.length() && j+3 < file.length()) {
        						 s2= file.charAt(j+1)+""+file.charAt(j+2)+file.charAt(j+3);
        					}
        					if(j+1 < file.length() && j+2 <file.length() && j+3 < file.length() && j+4 < file.length()) {
        						 s3 = file.charAt(j+1)+""+file.charAt(j+2)+file.charAt(j+3)+file.charAt(j+4);
        					}
        					if(s1.equals("if")) {
        						tmp = tmp+"if";
        						columnEnd = j+3;
        						i=j+2;
        						break;
        					}
        					else if(s2.equals("rec")) {
        						tmp = tmp+"rec";
        						columnEnd = j+4;
        						i=j+3;
        						break;
        					}
        					else if(s3.equals("loop")) {
        						tmp = tmp+"loop";
        						columnEnd = j+5;
        						i=j+4;
        						break;
        					}
        					else {
        						columnEnd = j+1;
            					i=j;
            					break;
        					}
        				}
        				else if(tmp.equals("exit")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("for")) {
        					String ss = file.charAt(j+1)+""+file.charAt(j+2)+file.charAt(j+3)+file.charAt(j+4);
        					if(ss.equals("ward")) {
        						tmp = tmp+"ward";
        						columnEnd = j+5;
        						i=j+4;
        						break;
        					}
        					else {
        						columnEnd = j+1;
            					i=j;
            					break;
        					}
        				}
        				else if(tmp.equals("function")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("if")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("is")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("loop")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("not")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("of")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("or")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("procedure")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("program")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("record")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("repeat")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("return")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("then")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("to")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("type")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("until")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("var")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
        				else if(tmp.equals("while")) {
        					columnEnd = j+1;
        					i=j;
        					break;
        				}
    					columnEnd = j+1;
    					i=j;
    					j=j+1;
    			}
    		}
    		String tokenType = "";
    		for(Map.Entry<String,String> entry : Token.tokenDistionary.entrySet()) {
        		if(tmp.matches(entry.getValue())) {
        			tokenType = entry.getKey();
        			break;
        		}
        	}
    		if(tokenType.isEmpty()) {
        		throw new NonRecognizableException("You have error",row,columnBegin);
        	}
    		if(tokenType.isEmpty()==false) {
        		if(tokenType.equals("COMMENT")==false || tokenType.equals("EMPTY")==false || tokenType.equals("SPACE")==false) {
        			Token t =  new Token(columnBegin,columnEnd,row,tokenType,tmp);
    				 t.setIdNumber(index);
    				tokenList.add(t);
    			}
        	}
    		index++;
    		tmp="";
    	}
    }
    
}
