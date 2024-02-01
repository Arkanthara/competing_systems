package countingsemaphore;
import semaphore.*;

public class CountingSemaphore implements Semaphore{
	private int value;

	public CountingSemaphore(int initValue) {
		value = initValue;
	}

	public synchronized void P() {
		while (value == 0)
		try { this.wait(); }
		catch (InterruptedException e) {}
		value--;
	}

	public synchronized void V() {
		value++;
		if (value == 1) notify();
	}
}
