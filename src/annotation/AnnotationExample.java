package annotation;

@MethodInfo(author = "heyang-hhh", desc = "this is class", revision = 1)
public class AnnotationExample {

	@Bean
	private IAnnotationInteface ai;
	
	@MethodInfo(author = "heyang-hhh", desc = "this is method", revision = 1)
	public void test() {
		System.out.println("test Method ing");
		ai.hello();
	}
}
