package spring;

import org.springframework.stereotype.Component;

@Component
public class MessageImpl implements IMessage {

	@Override
	public void print(String str) {
		System.out.println(str);
	}
}
