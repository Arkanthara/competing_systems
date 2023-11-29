package barrier;
import java.util.concurrent.*;
import java.util.Random;


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
				System.out.print("0");
			}
			if (current == count - 1) {
				Thread.yield();
			}
			// Don't work !
			// mutex.release();
			if (current < count) {
				mutex.release();
				wait.acquire();
			}
			else if (current == count) {
				current = 0;
				for (int i = 0; i < count; i++) {
					wait.release();
				}
				System.out.print("1");
				mutex.release();
			}
		} catch (InterruptedException exc) {}
	}

}
