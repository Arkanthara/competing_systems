### Locks

Problème: test et fermeture de la porte de manière non atomique...

But: trouver un moyen de vérifier et fermer le verrou d'une manière atomique...

1. Est-ce qu'il peut y avoir 2 threads en section critique ?
2. Possibilité d'interbloquage...

Manière méthodique de résoudre la question 1. est d'énumérer tous les entrelacements... Pas possible de créer un graphe si on ne connaît pas le nombre
de threads...
exemple de modélisation d'un état de notre processus: (p1, q1, False, False)

argument:
wantcs(0) = true -> wantcs(1) == False -> T1

wantcs(1) = true -> wantcs(0) == False -> T2
wantcs(1) = true -> wantcs(0) == False -> wantcs(0) = true -> wantcs(1) == False -> wantcs(1) = true => contradiction

1. Ok
2. Problème de vivacité: il existe une execution tel qu'on arrive à un interbloquage

3ème méthode
1. ok
2. T1 ne peut pas rentrer 2 fois de suite en section critique car il doit attendre que T2 soit rentrée en section critique

algorithme de peterson:

variable turn sert uniquement en cas d'interbloquage.... 
j: variable locale
turn: variable partagée

T1 en sc: 2 possibilités: 1: wantcs(1) == F 2: turn = 0
Faire un shéma d'exécution du genre "wantcs(1) = true -> wantcs(0) == False -> T2"

Si T2 rentre en sc et dès qu'il ressort, il rentre à nouveau très rapidement en section critique, il ne pourra pas car turn va être modifié permettant à t1 de rentrer en section critique...

