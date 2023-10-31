# Exercice 1

1. Montrez que l'implémentation de la page 109 du cours n'est pas starvation-Montrez que cette implémentation n’est pas starvation-free,
c’est-à-dire qu’un thread qui est en attente dans P() peut ne
jamais accéder à la section critique.

On a:

public class Binarysemaphore {
	private boolean value;
	BinarySemaphore(boolean initValue) {
	value = initValue;
}
public synchronized P() { // protocole d’entrée en SC
	while (value == false)
		try { this.wait() } catch (InterruptedException e) {}
		value = false;
}
public synchronized V() { // protocole de sortie de SC
	value = true;
	notify(); // libère un processus en attente
}
