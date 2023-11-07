moniteur: 
données 
méthodes synchronisées
méthodes non synchronisées
Processus en attente sur le verrou
processus en attente de notification
p141

Associé au moniteur, on a une liste des processus en attentes sur un verrou...

Le notify ne va pas forcément réveiller le thread qui attend que la pile ne soit plus vide....
-> variables de condition: on veut implémenter le fait qu'on fait un wait sur le fait que la pile ne soit plus vide, et un wait sur le fait que la pile ne soit plus pleine...
    -> création nouvelles variables...
Attention aux synchronised dans les synchronised... Cela marche car on ne bloque pas les ressources dans le deuxième verrou...

Problème lecteur rédacteur
équitable est plus fort que starvation... Équitable: chacun a une chance identique d'être exécuté

Run: exécute méthode run, mais ne crée pas de thread... VS start: crée un thread et exécute la méthode run.
