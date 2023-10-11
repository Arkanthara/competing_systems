# TP4

## Exercice 1 Algorithme de Peterson

```java
class PetersonAlgoritm implements Lock {
    private boolean wantCS[] = {false, false};
    private int turn = 1;
    public void requestCS(int i) {
        int j = 1-i;
        wantCS[i] = true;
        turn = j;
        while (wantCS[j] && (turn == j)); // les deux processus ne peuvent plus
    } // se trouver simultanement en SC,

    public void releaseCS(int i) { // l’alternance n’est plus necessaire
        wantCS[i] = false;
    }
}
```

| Thread 0                       | Thread 1                         |
| ------------------------------ | -------------------------------- |
| j = 1                          | j = 0                            |
| wantCS[0] = true               | wantCS[1] = true                 |
| turn = 1                       | turn = 0                         |
| while (wantCS[1] && turn == 1) | while (wantCS[0] && (turn == 0)) |
| SC                             | SC                               |
| wantCS[0] = false              | wantCS[1] = false                |



Pour que Thread 0 entre en section critique, il faut que soit wantCS[1] = false, soit turn = 0...

Ce qui donne l'exécution:

```java
wantCS[0] = true;
turn = 1;
while (wantCS[1] && turn == 1)
SC
wantCS[1] = true;
turn = 0;
while (wantCS[0] && turn == 0) // Ici, on a wantCS[0] == true et turn == 0
// On va donc être bloqué ici...
```

```java
wantCS[0] = true;
turn = 1;
wantCS[1] = true;
turn = 0;
// Le block de code au dessus peut s'exécuter dans l'ordre qu'on veut...
// Mais à la fin, on aura wantCS[0] = wantCS[1] = true
// Comme turn est une variable partagée, elle sera forçément égal à 0 ou 1.
// Ainsi, il y aura forçément un thread pour lequel la comparaison 
// avec turn sera fausse, qui pourra entrer en section critique...

// Le thread qui est en section critique ne touche
// plus à la variable partagée turn et le deuxième thread ne touche
// également plus à la variable turn car il l'a modifiée pour que le
// premier thread entre en section critique...
// La valeur de turn est donc fixée lorsqu'un thread rentre en section
// critique.
// L'autre thread fera donc une comparaison qui sera toujours vraie car
// turn est fixée.
// Le deuxième thread ne pourra donc plus être en section critique...

// La suite du code est simplement le sénario où turn = 0 à la fin...
while (wantCS[1] && turn == 1)
SC
while (wantCS[0] && turn == 0) // Ici, on a wantCS[0] == true et turn == 0
// On va donc être bloqué ici...
```

 

On remarque que si Thread 0 entre en section critique, alors Thread 1 est forçément bloqué... Il en est de même pour Thread 1:

```java
wantCS[1] = true;
turn = 0;
while (wantCS[0] && turn == 0)
wantCS[0] = true;
turn = 1;
while (wantCS[1] && turn == 1)
// Ici, on a wantCS[1] == true et turn == 1
// On va donc être bloqué ici...
```

```java
wantCS[1] = true;
turn = 0;
wantCS[0] = true;
turn = 1;
while (wantCS[0] && turn == 0)
while (wantCS[1] && turn == 1)
// Ici, on a wantCS[1] == true et turn == 1
// On va donc être bloqué ici...
```
