Remarque: quand on a des problèmes en systèmes distribués, on peut faire des hypothèses sur l'ordonnanceur...

Comment peut-on classer l'intelligence de l'ordonnanceur ???

# Data race

Déf: Si on a une variable qui est accédée par plusieurs threads, alors on a un problème de data race...

Atomic operations in java: AtomicInt etc... lire doc...

Consistance: On a un ensemble de valeurs qui doivent avoir des valeurs spéciales pour être consistantes....

Lecture et écriture sur variables de type standard sont atomiques

volatile: permet de rendre une variable visible entre tous les threads possédant un objet possédant cette variable... Force l'écriture en mémoire...


out-of-thin-air: on a une variable sur 64 bits... on veut écrire x, y dedans alors qu'on a a,b (32 bits chacune) -> quelqu'un pourrait lire la variable intermédiaire x, b


Synchronized: structure d'ordre sur les accès -> doit être cohérent sur la visibilité... Sinon on ne peut rien dire...

Visible: dernière écriture toujours visible

En java, tout est référence !


# Publication

Un objet se trouve dans un état prévisible et cohérent seulement après l'appel du constructeur....

première instruction isAlive() (flèche dans les 2 sens... L'un s'exécute toujours avant l'autre...)
dernière instruction join() (flèche dans les 2 sens... L'un s'exécute toujours avant l'autre...)


Sync order

L'ordre doit être total

Relation d'ordre; on peut numéroter les exécutions...

Relation d'ordre total: chacun s'exécute avant l'autre d'une manière distincte... Si on prend n'importe quel élément, on peut savoir si l'un s'est exécuté avant l'autre ou pas...

Happen-before: se poser la question de savoir qui vient avec qui dans une relation pas synchronisée... C'est un ordre partiel....

En java, si on a 2 accès à une variable partagée, alors ils sont conflictuels s'il y a au moins une écriture
On a un problème de course sur les données si des accès conflictuels ne sont pas ordonnés par la relation happen-before....

En résumé

On regarde tous les enterlacements, et on réfléchit ensuite sur un programme séquentiel.... Une des difficultés est que le compilateur peut avoir envie de swapper des instructions
Java -> on regarde uniquement les entrelacements entre les instructions synchronisées Si on n'a pas de relation happend-before entre les 2, on ne peut pas définir la valeur -> programme mal synchronisé...
