class Main {
  public static void main(String[] args) {
    System.out.println("Coucou !");
  }
}

interface Lock {
  public void requestCS(int i);

  public void releaseCS(int i);
}

class Bakery implements Lock {
  int N;
  volatile boolean[] choosing; // processus en phase 1
  volatile int[] number; // gestion de la file d’attente

  public Bakery(int numProc) {
    N = numProc;
    choosing = new boolean[N];
    for (int j = 0; j < N; j++) {
      choosing[j] = false;
      number[j] = 0;
    }
  }

  public void requestCS(int i) {
    choosing[i] = true;
    for (int j = 0; j < N; j++)
      if (number[i] < number[j])
        number[i] = number[j];
    number[i]++; // on choisi le plus grand numéro
    choosing[i] = false;
    for (int j = 0; j < N; j++) {
      while (choosing[j])
        ; // attente proc. en phase 1.
      while ((number[j] != 0) && ((number[j] < number[i]) ||
          ((number[j] == number[i]) && j < i)))
        ; // attente active
    }
  }

  public void releaseCS(int i) { // protocole de sortie de SC
    number[i] = 0;
  }
}
