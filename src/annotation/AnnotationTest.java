package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解处理
 * 
 * @author heyangda-bizcent
 */
public class AnnotationTest {
	public static void main(String[] args) {

		try {
			Class<?> c = Class.forName("annotation.AnnotationExample");

			System.out.println("----------------Class----------------");
			// 判断类上是否有注解
			if (c.isAnnotationPresent(MethodInfo.class)) {
				MethodInfo methodInfo = c.getAnnotation(MethodInfo.class);
				System.out.println(methodInfo.author());
				System.out.println(methodInfo.desc());
				System.out.println(methodInfo.revision());
			}
			System.out.println("---------------Method-1-----------------");
			// 判断方法上是否有注解,并获取方法上的实例
			Method[] methods = c.getMethods();
			for (Method m : methods) {
				if (m.isAnnotationPresent(MethodInfo.class)) {
					MethodInfo methodInfo = m.getAnnotation(MethodInfo.class);
					System.out.println(methodInfo.author());
					System.out.println(methodInfo.desc());
					System.out.println(methodInfo.revision());
				}
			}
			System.out.println("---------------Method-2-----------------");
			// 另一种获取方法上的注解的解析方法
			for (Method m : methods) {
				Annotation[] annotations = m.getAnnotations();
				for (Annotation a : annotations) {
					if (a instanceof MethodInfo) {
						MethodInfo methodInfo = (MethodInfo) a;
						System.out.println(methodInfo.author());
						System.out.println(methodInfo.desc());
						System.out.println(methodInfo.revision());
					}
				}
			}
			System.out.println("---------------Field-1-----------------");
			Object newO = c.newInstance();
			Field[] fields = c.getDeclaredFields();
			for (Field f : fields) {
				if (f.isAnnotationPresent(Bean.class)) {
					f.setAccessible(true);
					f.set(newO, new AnnotationImpl());
				}
			}
			AnnotationExample ae = (AnnotationExample) newO;
			ae.test();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}
