# Consensus

2 threads avec registres atomiques et c'est wait free... Pas possible...

L'état est bivalent lorsqu'on n'a pas fait de choix

L'état est critique si lorsque l'état suivant amène à un choix.


Supposons qu'on a un état bivalent critique...

- soit e, soit f est une lecture ou une instruction interne
(ex: e: LDR r0, ... e va lire une valeur... f ne va pas voir de différence et pourrait s'exécuter jusqu'à la fin...)

- e et f sont des accès à des adresses mémoires différentes
(ex: on a f -> e identique à e -> f... => on est toujours dans un état bivalent car l'algorithme est déterministe...)

- e et f sont des accès à une même adresse mémoire
(ex: e -> f -> f ... 1 car f va écraser l'écriture de e...)

Pas capable de résoudre la section critique de manière wait-free...


+ Test and set (lecture écriture d'une manière atomique) TAS
marche pour 2 threads, mais pas pour 3...
(ex: exécution de p0, p1 j -> critique.
e et f sont des instructions TAS sinon cela ne marche pas, comme montré précédement... On peut faire e -> f ou f -> e, mais cela ne va rien changer pour p2)
(idem pour 1 adresse: p2 ne va pas voir de différence entre e et f -> e...)

Corollaire: on ne peut pas implémenter de fonction TAS avec des registres atomiques ! On a besoin d'un swap (lecture et écriture atomique) !


Supposons qu'on a p0 choisissant une valeur j0 et p1 choisissant une valeur j1.
Comme p0 a choisi j0, il a fait un move et a embêté tout le monde pour mettre sa valeur, puis il est redescendu et a testé r[j0, 1] = j0.
Mais quand il est redescendu, il a testé r[j1, 1] != j1.
Mais si j1 a choisi sa valeur, cela signifie que j1 a effectué le move, donc qu'on a r[j1, 1] = j1....

Si p0 < j1, p0 -> r[j1, 0] = j1 - 1 -> r[j1, 1] != j1 -> mov

Preuve récursive... On ne peut jamais retourner une valeur plus grande que j0...

Donc on peut résoudre le consensus avec n processus en wait free grâce à move...

Cela nous a permit de faire un peu une classification des fonctions de synchronisations... (Implémenter move avec test and set n'est pas possible !)

Donc le matériel est également très important car sans instruction move, on ne peut pas résoudre le problème du consensus avec n processeurs...
