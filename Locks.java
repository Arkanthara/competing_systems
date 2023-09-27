public class Locks {
	public static void main(String args[]) {
		var mem = new MyObj();
		var lock = new Attempt1();
		var t1 = new MyThread(lock, mem);
		var t2 = new MyThread(lock, mem);
		t1.start();
		t2.start();
		try {
			Thread.sleep(400);
		}
		catch (InterruptedException e){}
		System.out.println("If x != 2000: Failed... x= " + mem.x);
	}
}

public interface Lock {
	public void requestCS(int i);
	public void releaseCS(int i);
}

class Attempt1 implements Lock {
	private boolean lock = true;

	public void requestCS(int i) {
		while (!lock);
		lock = false;
	}

	public void releaseCS(int i) {
		lock = true;
	}
}

public class MyObj {
	public int x = 0;
}

public class MyThread extends Thread {
	private Lock lock;
	private MyObj mem;

	public MyThread(Lock lock, MyObj mem) {
		this.lock = lock;
		this.mem = mem;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			lock.requestCS(0);
			this.mem.x += 1;
			lock.releaseCS(0);
		}
		System.out.println("Finished...");
	}

}
