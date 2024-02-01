package peterson;
import lock.*;

public class Peterson implements Lock {
	private volatile boolean wantCS[] = {false, false};
	private volatile int turn = 1;

	public void requestCS(int i) {
		int j = 1-i;
		wantCS[i] = true;
		turn = j;
		while (wantCS[j] && (turn == j)); // les deux processus ne peuvent plus
	} // se trouver simultanement en SC,

	public void releaseCS(int i) { // l’alternance n’est plus necessaire
		wantCS[i] = false;
	}
}
