public class Locks {
	public static void main(String args[]) {
		var mem = new MyObj();
		var lock = new Attempt1();
		var t1 = new MyThread(lock, mem, 0);
		var t2 = new MyThread2(lock, mem, 1);
		t1.start();
		t2.start();
		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e){}
		System.out.println("Method 1: If x != 150000: Failed... x= " + mem.x);
		test_deadlock(mem.x, 500000, 4, t1, t2);

		var mem_2 = new MyObj();
		var lock_2 = new Attempt2();
		var t3 = new MyThread(lock_2, mem_2, 0);
		var t4 = new MyThread2(lock_2, mem_2, 1);
		t3.start();
		t4.start();
		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e){}
		System.out.println("Method 2: If x != 150000: Failed... x= " + mem_2.x);
		test_deadlock(mem_2.x, 500000, 4, t3, t4);
		
		var mem_3 = new MyObj();
		var lock_3 = new Attempt3();
		var t5 = new MyThread(lock_3, mem_3, 0);
		var t6 = new MyThread2(lock_3, mem_3, 1);
		t5.start();
		t6.start();
		try {
			Thread.sleep(500);
		}
		catch (Exception e){System.out.println(e);}
		System.out.println("Method 3: If x != 150000: Failed... x= " + mem_3.x);
		test_deadlock(mem_3.x, 500000, 4, t5, t6);
		
		var mem_4 = new MyObj();
		var lock_4 = new Peterson();
		var t7 = new MyThread(lock_4, mem_4, 0);
		var t8 = new MyThread2(lock_4, mem_4, 1);
		t7.start();
		t8.start();
		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e){}
		System.out.println("Method 4: If x != 150000: Failed... x= " + mem_4.x);
		test_deadlock(mem_4.x, 500000, 4, t7, t8);
	}

	public static void test_deadlock(int x, int goal, int num, MyThread t1, MyThread2 t2) {
		if (x < goal) {
			System.out.println("Method "+num+" is enter in deadlock!");
//			try {
//				t1.suspend();
//				t2.suspend();
//				t1.resume();
//				t1.join();
//				t2.resume();
//				t2.join();
//			}
//			catch (Exception e) {System.out.println(e);}
		}
		else {
			System.out.println("No deadlock");
		}
	}
}

public interface Lock {
	public void requestCS(int i);
	public void releaseCS(int i);
}

class Attempt1 implements Lock {
	private volatile boolean lock = true;

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
	private int num;

	public MyThread(Lock lock, MyObj mem, int num) {
		this.lock = lock;
		this.mem = mem;
		this.num = num;
	}

	public void run() {
		for (int i = 0; i < 1000000; i++) {
			lock.requestCS(this.num);
			this.mem.x += 1;
			lock.releaseCS(this.num);
		}
		System.out.println("Finished...");
	}

}


public class MyThread2 extends Thread {
	private Lock lock;
	private MyObj mem;
	private int num;

	public MyThread2(Lock lock, MyObj mem, int num) {
		this.lock = lock;
		this.mem = mem;
		this.num = num;
	}

	public void run() {
		for (int i = 0; i < 500000; i++) {
			lock.requestCS(this.num);
			this.mem.x += 1;
			lock.releaseCS(this.num);
		}
		System.out.println("Finished...");
	}

}
