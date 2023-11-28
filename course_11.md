# Synchronisation non bloquante

Idée: on a toujours des algorithmes qui sont bloquants.... On a souvent des problèmes de deadlock -> si on crée des algorithmes qui ne s'arrêtent jamais, on n'aura jamais de deadlock...

But:
- améliorer les performance

## Variables partagées

Quels sont les variables partagées et quelles sont leurs propriétés ?

3 types de registre: 
- safe
- régulier
- atomique

Existe-t-il des registres qui ne sont pas sûr ?

SWSR -> single writer single reader
SWMR -> single writer multiple reader

Registres: SWSR sûr
-> peut-on avec des algorithmes transformer SWSR en MWMR ?

registre régulier:
retourne soit la valeur écrite précédement, soit la valeur en train d'être écrite...

registre atomique
Un registre est atomique s'il est régulier et linéarisable
Mot clé: linéarisable

Pas compris cas 3 de la page 235...

On utilise une notion de précédence: on peut mettre des flèches entre les exécutions...


On suppose que l'on va toujours écrire w1 -> w2 -> w3 -> w4. Mais les ri peuvent être dans le déshordre...

a vient avant b si la fin de a est plus petit que le début de b.

régulier
On n'a jamais ri -> wi: not (ri -> wi)
On n'a jamais wi -> wj -> ri: not (wi -> wj -> ri)

+ registre atomique
si ri -> rj alors i <= j


On est capable de construire un registre régulier booléen.
Est-on capable de construire un registre atomique booléen ?
Oui en utilisant 3 registres réguliers

On saute la preuve !!!!!!!


!!!!!! Ri -> Wi => toutes les lectures ri viennent avant toutes les écritures wi...!!!!!!!!


