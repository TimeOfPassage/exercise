package stringUtil;

import java.text.MessageFormat;

public class StringUtils {

	public static void main(String[] args) {
		
		String str = "你好，欢迎使用{0},如有疑问，请拨打{1}";
		
		String value = MessageFormat.format(str, "电神科技app","400400400");
		
		System.out.println(value);
		
		
	}
	
}
