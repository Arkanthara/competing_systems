### Programmation concurrente: mémoire partagée (câblé)
- exclusion mutuelle
- synchronisation
- synchronisation wait-free
### Programmation distribuée: messages dans réseau (non câblé)
- sockets/RMI en Java
- ...

En programmation concurrente, on identifie les tâches... Programmation séquentielle: est-ce que cette action est survenue ? si oui, on fait cela etc...
#### Exemple: T1 -> tampon -> T2
On appuye sur une touche du clavier...
Programmation concurrente: si l'événement survient, on crée un objet et on l'envoie dans le tampon. Quand T2 aura du temps, il va s'occuper de ce qu'il y a dans le tampon.
T1: while(1): x=crée + tampon.insert(x); T2: while(1): y=tampon.read() + traitement y

### Thread vs Processus
thread: processus comuniquant par mémoire partagée (MP)
processus: programme en cours d'exécution

### Programmation parallèle vs programmation concurrente
Programmation concurrente: exclusion mutuelle
Programmation parallèle: pas d'exclusion mutuelle

##### Regrader dans chapitre 17 de java
Java s'exécute sur une machine virtuelle... Pour les systèmes concurrents, le problème est qu'il y a des mémoires caches... Donc il ne faut pas simplement prendre le
BUS pour écrire dans la mémoire partagée... Un modèle a été défini, et la machine virtuelle respecte ce modèle.

### Problèmes programmation concurrente
deadlock/interblockage: T1 attend que T2 fasse quelque chose et T2 attend que T1 fasse quelque chose
interférences: plusieurs entités concurrentes modifient une donnée en même temps -> la donnée peut finalement être corrompue...
On ne sait pas quel résultat on va obtenir à cause des effets de bord
starvation/insuffisance de ressources: une entité d'arrive pas à accéder à une ressource

### Propriétés désirées
safety/sûreté: Un événement qui ne doit pas se produire, ne doit pas se produire
liveness/vivacité: Si quelque chose doit se produire, alors elle se produit

#### Interblockage
4 conditions nécessaires pour avoir un interblockage:
- exclusion mutuelle: Il faut avoir une exclusion mutuelle sinon, il n'y aura pas d'interblockage
- hold and wait: possèdent le verrou, ne le relâchent pas et attendent l'accès sur une autre ressource
- pas de préemption: objets capable de faire que le processus qui s'exécute exécute autre chose que ce que le programmeur a prévu (exemple: exceptions...)
- attente circulaire: thread 1 possède une ressource que thread 2 attend et attend une ressource de thread 2 et vice-versa

##### Exemple
T1						T2
a.lock()			b.lock()
b.lock()			a.lock()

##### Solution
- S'assurer que au moins une des 4 conditions ne se produise jamais
- utiliser un algorithme d'allocution de ressources qui gère dynamiquement les accès en évitant les interblockages
- préemption: système qui gère les interblockages

#### Notion de moniteur
module: composé de fonctions et de variables globales (on n'a pas accès aux variables globales car elles sont encapsulées)- Un module a une interface.
moniteur: c'est comme un module + mécanisme pour accéder aux fonctions grâce à un verrou. Associé au moniteur, on a une file d'attente pour accéder aux fonctions.
lorsqu'on utilise une fonction, on a le verrou qui blocke les autres personnes voulant utiliser la fonction.
Dans les objets java, on a un mécanisme similaire aux moniteurs avec un verrou et 2 files d'attentes...
