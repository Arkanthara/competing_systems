public class Thread1 {
	public static void main(String args[]) {
		Ecrit e1 = new Ecrit("bonjour", 50, null);
		Ecrit e2 = new Ecrit("salut", 50, e1);
		Ecrit e3 = new Ecrit("coucou", 50, e2);

/* Version 1 
		try {
			e1.start();
			e1.join();
			e2.start();
			e2.join();
			e3.start();
			e3.join();
		}
		catch (InterruptedException e) {}
	}
*/
		try {
			e3.start();
			Thread.sleep(300);
			e2.start();
			Thread.sleep(300);
			e1.start();
		} catch(Exception e) {}
	}
}

class Ecrit extends Thread
{
	private String text;
	private int nb;
	private Ecrit e;

	public Ecrit(String text, int nb, Ecrit e) {
		this.text = text;
		this.nb = nb;
		this.e = e;
	}

	public void run() {
		try {
			if (this.e != null) {
				while (!this.e.isAlive());
				this.e.join();
			}
			for (int i = 0; i < this.nb; i++) {
				System.out.print(this.text);
			}
		}
		catch (InterruptedException e) {}
	}
}
