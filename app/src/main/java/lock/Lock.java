package lock;

// voir l’interface Runnable pour l’utilisation
public interface Lock {
	public void requestCS(int pid); // requête pour entrer en SC
	public void releaseCS(int pid); // indique que le thread quitte la SC
}
