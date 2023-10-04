# TP3

## Exercice 1

Pour que T1 rentre en section critique, il faut que:

Soit b[1] == false
Soit local[0] != turn[1]

1. la seule possibilité d'exécution pour que b[1] == false lors de l'exécution de la boucle while est:

b[0] = true -> local[0] = turn[1] -> turn[0] = local[0] -> b[1] != true && (local[0] == turn[1]) -> SC -> b[1] = true -> local[1] = 1 - turn[0] -> turn[1] = local[1]
-> b[0] == true && (local[1] == 1 - turn[0] != turn[0]) -> bloqué

2. pour que local[0] != turn[1] il faut que:

b[0] = true -> local[0] = turn[1] -> turn[0] = local[0] -> b[1] = true -> local[1] = 1 - turn[0] -> turn[1] = local[1] 
-> b[1] == true && (local[0] != turn[0]) -> sc -> b[0] == true && (local[1] == 1 - turn[0]  != turn[0]) -> bloqué

On peut donc voir que lorsque T1 en section critique, T2 est forcément bloqué.
