package binarysemaphore;
import semaphore.*;

public class BinarySemaphore implements Semaphore{
	private boolean value;

	public BinarySemaphore(boolean initValue) {
		value = initValue;
	}

	public synchronized void P() { // protocole d’entrée en SC
		while (value == false)
		try { this.wait(); } 
		catch (InterruptedException e) {}
		value = false;
	}

	public synchronized void V() { // protocole de sortie de SC
		value = true;
		notify(); // libère un processus en attente
	}
}
