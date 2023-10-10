Structure en pyramide inversée pour appliquer Peterson avec à chaque fois des objets partagés pour avoir plusieurs processeurs en même temps qui veulent arriver en section critique



#### Lamport

Idée de tickets... Mais plusieurs processeurs peuvent avoir le même numéro de ticket

Lamport: je vais vivre avec ça... $\Rightarrow$ N thread peuvent avoir le même numéro. Le prochain thread qui rentre est celui qui a le numéro le plus petit, et s'il y a plusieurs threads avec le même numéro, je prend celui avec l'identifiant le plus petit.

- On choisit le ticket

- On décide si on peut rentrer en section critique

- L'utilité de la variable choosing est que les variables ne varient plus à la boucle while suivante...



Création de cycle pour les numéros car problèmes avec numéros non bornés !!! $\Rightarrow$ génère des estampilles temporelles



Traiter avec un nombre fini d'estampilles un nombre N de threads sans problème d'overflow ou de débordement (s'il y a toujours un thread en section critique, la valeur des tickets augmente à chaque fois....



Moniteur: permet de gérer l'exécution des threads

TestAndSet: comment la jvm marche ? Elle utilise un moniteur pour l'objet lock

Quand T1 sort du block, il libère le verrou, et tous les threads qui attendent passent en état exécutable....



Le niveau d'exécution de la jvm est supérieur au niveau d'exécution des threads... $\Rightarrow$ la jvm peut tout gérer et ne se fait pas interrompre...

Attente passive des threads dans la jvm
