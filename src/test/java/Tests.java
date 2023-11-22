import barrier.*;
import thread.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

	@Test
	@DisplayName("Test of our implementation")

	public void test() {
		Barrier barrier = new Barrier(5);
		MyThread T1 = new MyThread(barrier);
		MyThread T2 = new MyThread(barrier);
		MyThread T3 = new MyThread(barrier);
		MyThread T4 = new MyThread(barrier);
		MyThread T5 = new MyThread(barrier);
	}
}
