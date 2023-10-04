public class Locks {
	public static void main(String args[]) {
		var mem = new MyObj();
		var lock = new Attempt1();
		var t1 = new MyThread(lock, mem);
		var t2 = new MyThread(lock, mem);
		t1.start();
		t2.start();
		try {
			Thread.sleep(600);
		}
		catch (InterruptedException e){}
		System.out.println("If x != 2000: Failed... x= " + mem.x);

		var mem_2 = new MyObj();
		var lock_2 = new Attempt2();
		var t3 = new MyThread(lock_2, mem_2);
		var t4 = new MyThread(lock_2, mem_2);
		t3.start();
		t4.start();
		try {
			Thread.sleep(600);
		}
		catch (InterruptedException e){}
		System.out.println("If x != 2000: Failed... x= " + mem_2.x);
		
		var mem_3 = new MyObj();
		var lock_3 = new Peterson();
		var t5 = new MyThread(lock_3, mem_3);
		var t6 = new MyThread(lock_3, mem_3);
		t5.start();
		t6.start();
		try {
			Thread.sleep(5000);
		}
		catch (Exception e){System.out.println(e);}
		System.out.println("If x != 2000: Failed... x= " + mem_3.x);
		
		var mem_4 = new MyObj();
		var lock_4 = new Peterson();
		var t7 = new MyThread(lock_4, mem_4);
		var t8 = new MyThread(lock_4, mem_4);
		t7.start();
		t8.start();
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e){}
		System.out.println("If x != 2000: Failed... x= " + mem_4.x);
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

class Attempt2 implements Lock {
	private volatile boolean wantcs[] = {false, false};
	
	public void requestCS(int i) {
		wantcs[i] = true;
		while (wantcs[1-i]);
	}

	public void releaseCS(int i) {
		wantcs[i] = false;
	}
}

class Attempt3 implements Lock {
	private volatile int turn = 0;

	public void requestCS(int i) {
		while (turn == 1-i);
	}

	public void releaseCS(int i) {
		turn = 1-i;
	}
}

class Peterson implements Lock {
	private volatile boolean wantcs[] = {false, false};
	private volatile int turn = 1;

	public void requestCS(int i) {
		int j = 1-i;
		wantcs[i] = true;
		turn = j;
		while (wantcs[j] && (turn == j));
	}

	public void releaseCS(int i) {
		wantcs[i] = false;
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
