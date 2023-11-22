import barrier.*;
import thread.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello everybody !");

		Barrier barrier = new Barrier(3);

		MyThread T1 = new MyThread(barrier);
		MyThread T2 = new MyThread(barrier);
		MyThread T3 = new MyThread(barrier);

		T1.start();
		T2.start();
		T3.start();

	}
}
