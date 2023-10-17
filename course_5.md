##### 17/10/2023

Dans un objet, on a 2 files d'attente: une pour les verrous, et une pour les wait/notify.



Blocs sont réentrant -> On peut prendre plusieurs fois le même verrou sur le même objet



Si une méthode est statique, alors le verrou est sur la classe et pas sur l'objet... Cela permet la syncronisation entre les objets d'une même classe



Si on fait un notify() au lieu d'un notifyAll(), on va être bloqué (exemple à 3 threads...)

Solution: utiliser 2 objets....

Si on met un if au lieu d'un while, on pourra faire des choses illégales...

(p92)


