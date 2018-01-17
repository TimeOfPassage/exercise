package compiler.lexer;

import java.util.Scanner;

/**
 * 词法分析
 * 
 * @author heyangda-bizcent
 */
public class Lexer {
	public static final int EOI = 0;
	public static final int SEMI = 1;
	public static final int PLUS = 2;
	public static final int TIMES = 3;
	public static final int LP = 4;
	public static final int RP = 5;
	public static final int NUM_OR_ID = 6;

	//表明当前分割的字符串指向的标签值
	private int lookAhead = -1;

	//用于存储当前正在分析的字符串
	public String yytext = "";
	//当前分析的字符串的长度
	public int yyleng = 0;
	//当前分析的字符串所在的行号
	public int yylineno = 0;

	//存储要分析的语句例如: 1+2*3+4
	private String input_buffer = "";
	private String current = "";

	//判断输入的字符是否是数字或字母
	private boolean isAlnum(char c) {
		if (Character.isAlphabetic(c) == true || Character.isDigit(c) == true) {
			return true;
		}

		return false;
	}
	//开始了词法分析的流程
	private int lex() {

		while (true) {

			while (current == "") {
				Scanner s = new Scanner(System.in);
				while (true) {
					String line = s.nextLine();
					if (line.equals("end")) {
						break;
					}
					input_buffer += line;
				}
				s.close();

				if (input_buffer.length() == 0) {
					current = "";
					return EOI;
				}

				current = input_buffer;
				++yylineno;
				current.trim();
			} // while (current != "")

			for (int i = 0; i < current.length(); i++) {

				yyleng = 0;
				yytext = current.substring(0, 1);
				switch (current.charAt(i)) {
				case ';':
					current = current.substring(1);
					return SEMI;
				case '+':
					current = current.substring(1);
					return PLUS;
				case '*':
					current = current.substring(1);
					return TIMES;
				case '(':
					current = current.substring(1);
					return LP;
				case ')':
					current = current.substring(1);
					return RP;

				case '\n':
				case '\t':
				case ' ':
					current = current.substring(1);
					break;

				default:
					if (isAlnum(current.charAt(i)) == false) {
						System.out.println("Ignoring illegal input: " + current.charAt(i));
					} else {

						while (isAlnum(current.charAt(i))) {
							i++;
							yyleng++;
						} // while (isAlnum(current.charAt(i)))

						yytext = current.substring(0, yyleng);
						current = current.substring(yyleng);
						return NUM_OR_ID;
					}

					break;

				} // switch (current.charAt(i))
			} // for (int i = 0; i < current.length(); i++)

		} // while (true)
	}// lex()

	public boolean match(int token) {
		if (lookAhead == -1) {
			lookAhead = lex();
		}

		return token == lookAhead;
	}

	public void advance() {
		lookAhead = lex();
	}

	//将驱动词法解析器，执行解析流程
	public void runLexer() {
		while (!match(EOI)) {
			System.out.println("<" + token() + " ," + yytext+">");
			advance();
		}
	}

	private String token() {
		String token = "";
		switch (lookAhead) {
		case EOI:
			token = "EOI";
			break;
		case PLUS:
			token = "PLUS";
			break;
		case TIMES:
			token = "TIMES";
			break;
		case NUM_OR_ID:
			token = "NUM_OR_ID";
			break;
		case SEMI:
			token = "SEMI";
			break;
		case LP:
			token = "LP";
			break;
		case RP:
			token = "RP";
			break;
		}

		return token;
	}
}
