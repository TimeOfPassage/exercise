package stringUtil;

import java.text.MessageFormat;

public class StringUtils {

	public static void main(String[] args) {
		
		String str = "��ã���ӭʹ��{0},�������ʣ��벦��{1}";
		
		String value = MessageFormat.format(str, "����Ƽ�app","400400400");
		
		System.out.println(value);
		
		
	}
	
}
