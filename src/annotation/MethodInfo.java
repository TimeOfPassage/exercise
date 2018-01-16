package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented //使用此注解表示元素应被javadoc或类似工具文档化
@Target({ElementType.TYPE,ElementType.METHOD})//注解支持种类(TYPE, METHOD, CONSTRUCTOR, FIELD等等)
@Inherited//表示注解自动被继承
@Retention(RetentionPolicy.RUNTIME)//表示注解保留时间长短(SOURCE, CLASS, 以及RUNTIME)
public @interface MethodInfo {
	String author() default "heyang";
	int revision() default 1;
	String desc();
}
