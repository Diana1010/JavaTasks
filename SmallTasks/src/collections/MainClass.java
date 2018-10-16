package collections;

public class MainClass {

	public static void main(String[] args) {
		
		TimeOutQueue<String> queue = new TimeOutQueue<String>();
		queue.offer("first", 3000);
		queue.offer("second", 3000);
		queue.offer("third", 0);
		queue.offer("next", 10000);
	
			System.out.println(queue.poll());
			System.out.println(queue.poll());
			
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(queue.poll());
			System.out.println(queue.poll());
		
			
	}
}

