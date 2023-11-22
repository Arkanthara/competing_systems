package barrier;
import java.util.concurrent.*;


public class Barrier {

	// Create fair semaphores	
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore wait = new Semaphore(0, true);
	private volatile int count;
	private volatile int current = 0;

	public Barrier(int count) {
		this.count = count;
	}

	public void wFP() {
		try {
			mutex.acquire();
			current ++;
			if (current == 1) {
				System.out.println("First thread goes through the barrier !");
			}
			mutex.release();
			if (current < count) {
				wait.acquire();
			}
			else if (current == count) {
				mutex.acquire();
				current = 0;
				for (int i = 0; i < count; i++) {
					wait.release();
				}
				System.out.println("Last thread goes through the barrier !");
				mutex.release();
			}
		} catch (InterruptedException exc) {}
	}

}
