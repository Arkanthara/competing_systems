1-s: temps non parallélisable + s/N temps exécuté parallélisé

Speedup: montre que si on veut améliorer les performances du programme, il faut aussi écrire le programme pour que les processeurs puissent s'exécuter d'une manière parallèle

Pour les preuves: il y a vite beaucoup de cas... => mécaniser la preuve ? difficile à implémenter
erreurs peuvent se produire que dans certaines conditions... => il nous faut des méthodes formelles...

Technique des invariants: prouver que quelque chose est toujours vrai....

État du système: besoin du pc, des registres, de la mémoire et de la mémoire partagée
f(état) -> f(pc1, pc2) = (pc1=4) et (pc2=4) = 1

Invariants: on va utiliser exclusivement la logique des propositions

Def: une proposition est invariante si elle est toujours vraie... Une des manières de faire est de prouver cela par récurrence...

Synchronisation: réaliser l'exclusion mutuelle et coordonner l'exécution des threads....
Idée: ne plus faire d'attente active... À utiliser seulement en cas de nécessité car sinon c'est plus lent...

### Sémaphore
Permet de réaliser l'exclusion mutuelle et qui dispose de 2 champs de données:
- une valeur booléenne
- une file d'attente de processus bloqués
- P(): si valeur = true, alors valeur = false si valeur = false, le processus appelant est bloqué
- V() valeur = true, si al file d'attente des processus n'est pas libre on libère un processus