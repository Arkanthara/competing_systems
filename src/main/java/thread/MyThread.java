package thread;
import barrier.*;

public class MyThread extends Thread {

	private Barrier barrier;

	public MyThread(Barrier barrier) {
		this.barrier = barrier;
	}

	public void run() {
		for (int i = 0; i < 30; i++) {
			this.barrier.wFP();
		}
	}
			
}
	
	
