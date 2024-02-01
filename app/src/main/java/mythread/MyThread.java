package mythread;
import java.util.Random;
import lock.*;

public class MyThread extends Thread {
	int myId;
	Lock lock;
	Random r = new Random();

	public MyThread(int id, Lock lock) {
		myId = id; // chauqe processus possède une identité propre
		this.lock = lock; // les processus utilisent le même verrou pour accéder la SC
	}

	void nonCriticalSection() {
		// System.out.println(myId + " n’est pas en SC ");
		try {
			this.sleep(r.nextInt(5));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	void CriticalSection() {
		// System.out.println(myId + " est en SC ");
		System.out.print(myId);
		try {
			this.sleep(r.nextInt(5));
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		// System.out.println(myId + " n'est plus en SC ");
		System.out.print(myId);
	}

	public void run() {
		for (int i = 0; i < 30; i++) {
			lock.requestCS(myId);
			// section critique
			CriticalSection();

			lock.releaseCS(myId);
			// section non critique
			nonCriticalSection();
		}
	}
}
