import mythread.*;
import peterson.*;
import lock.*;
import bakery.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello everybody !");

		MyThread t[];

		// if (args.length == 0) {
		// 	System.out.println("Give the number of threads !");
		// 	return;
		// }

		// int N = Integer.parseInt(args[0]);
		int N = 4;
		t = new MyThread[N];
		Lock lock = new Bakery(N); //compléter avec un algorithme mutex
		for(int i=0; i<N; i++){
			t[i] = new MyThread(i,lock);
			t[i].start();
		}
	}
}
