# Exercice 1

1. Montrez que l'implémentation de la page 109 du cours n'est pas starvation-Montrez que cette implémentation n’est pas starvation-free,
c’est-à-dire qu’un thread qui est en attente dans P() peut ne
jamais accéder à la section critique.

On a:

```
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
```

# Exercice 2

On suppose que N threads exécute le programme suivant
```Ti
	pc = 1: X[i]=1
	pc = 2: Y[i]=X[(i-1) mod N]
	end
```
Montrez qu’une fois que les N threads ont exécutés leur programme il
existe au moins un thread i pour lequel $Y[i]=1$

On veut montrer que $\forall i \in 0\dots N, pc_i = end \Rightarrow \exist j, Y[j] = 1$
Démontrons cela par récurrence.
- Initialisation: Démontrons que notre initialisation est vraie. Au début du programme, on a:
	$\forall i \in 0\dots N, pc_i \neq end$ car aucun thread n'a fini de s'exécuter. Donc la condition de notre proposition $\forall i \in 0\dots N, pc_i = end $ est fausse.
	Or faux implique vrai
	Donc l'initialisation de notre programme respecte la proposition.
- Supposons que $N-1$ treads ont fini de s'exécuter... Il n'y a donc plus qu'un thread k telle que $pc_k \neq end$.
	Démontrons que l'état où tous les threads se sont exécutés est vrai, soit que lorsque $pc_k = end$, notre proposition sera vérifée.
	Supposons que $pc_k = 2$. Il devra alors exécuter $Y[k] = X[(k - 1) mod N]$ avant de se terminer.
	Lorsqu'il a exécuté $X[k] = 1$, comme tous les autres thread se sont déjà exécutés, on a $\forall i \in 0 \dots N, X[i] = 1$.
	Donc lorsque $Y[k] = X[(k - 1) mod N]$ sera exécuté, $Y[k] = 1$ car $\forall i \in 0\dots N, X[i] = 1$.
  Donc la proposition est vérifée !
- Donc $\forall i \in 0\dots N, pc_i = end \Rightarrow \exist j, Y[j] = 1$
