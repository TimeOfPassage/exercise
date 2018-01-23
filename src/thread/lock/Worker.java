package thread.lock;

public class Worker implements Runnable {

	Ticket data;
	
	boolean read;
	
	String name;
	
	public Worker(Ticket data,boolean read, String name){
		this.data = data;
		this.read = read;
		this.name = name;
	}
	
	@Override
	public void run() {
		
		while(true){
			if(read)
				data.get(name);
			else
				data.set(name);
		}
	}
}
