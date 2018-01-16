package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented //ʹ�ô�ע���ʾԪ��Ӧ��javadoc�����ƹ����ĵ���
@Target({ElementType.TYPE,ElementType.METHOD})//ע��֧������(TYPE, METHOD, CONSTRUCTOR, FIELD�ȵ�)
@Inherited//��ʾע���Զ����̳�
@Retention(RetentionPolicy.RUNTIME)//��ʾע�Ᵽ��ʱ�䳤��(SOURCE, CLASS, �Լ�RUNTIME)
public @interface MethodInfo {
	String author() default "heyang";
	int revision() default 1;
	String desc();
}
