## Problème des philosophes
Problème d'interblockage.....

pas symétrique: les thread n'exécutent pas tous le même code...

On veut symétrique: 1 pièce de code distribuée à tous les threads vs élire un thread qui aura un autre code (asymétrique...)

Sémaphore fair: sémaphore équitable

solution centralisée: il existe un élément central partagé par tous les threads...
Si on n'a pas de mémoire partagée commune ou pas de mémoire centrale, il n'existe pas de solution symétrique distribuée et déterministe au problème des philosophes...???


Si chaque thread a exécuté une instruction, on prétend que le système est symétrique (si chaque thread possède les mêmes instructions)



Les penseurs ne peuvent pas être interbloqués ... Ils vont choisir un nombre infini de fois la fourchette de gauche et un nombre infini de fois la fourchette de droite....

Donc pour 2 voisins, il va y avoir nécessairement une situation ou les deux se distputent la fourchette commune... Et cette situation va se produire une infinité de fois... Selon un lemme, il y en aura toujours un qui aura une probabilité
positive de manger...

### Problème de course sur les données...

P1 choisit la gauche (il doit se battre jusqu'à l'obtenir), P2 choisit la droite (il doit se battre pour l'obtenir)... Puis en même temps ils essayent d'accéder à la fourchette commune...

### Configuration

Valeur des derniers choix effectués par les philosophes....

2 configurations sont disjointes si entre 2 configurations, tous les philosophes ont fait un tirage aléatoire... => variables aléatoires indépendantes... (Il faut que le système ait été 'réinitialisé' ....???)

Probabilité que la situation ne se produise pas va tendre vers 0....

Problème qu'on peut pas résoudre avec un algorithme déterministe, mais qu'on peut résoudre avec un algorithme probabiliste...

vert: accède à la fourchette commune
rouge: n'accède pas à la fourchette commune
vert traitillé: essayé d'accéder à la fourchette commune mais ne la trouve pas... (p187 etc...)
