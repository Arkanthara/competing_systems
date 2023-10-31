Quand on exécute un notify, on ne libère pas le verrou...
Le notify active un processus en attente, mais on ne sait pas lequel...

interrupt(): permet de réactiver le thread... 
Réactivé: devient exécutable, mais ne s'exécute pas forcément...
Différence avec notify(): lève des exceptions...
sleep et yield permettent à un thread de forcer l'ordonnanceur à changer le thread actif.Néanmoins:
- un thread ne perd pas les verrous dont il dispose
- Ces opérations n'ont pas d'effets sur la synchronisation; les données sauvegardées dans les registres ne sont pas copiées en mémoire principale; les données
ne sont pas lues depuis la mémoire principale après l'exécution.

Les blocks synchronized vont écrire dans la mémoire les valeurs lues et modifiées... Elles introduisent de la visibilité sur les variables: Les écritures de T1
sont visibles par T2...

sleep() et yield(): variables pas écrites en mémoire !!!!
Si la variable n'est pas volatile, elle ne sera pas écrite en mémoire avec un sleep

On peut dire au compilateur qu'on ne veut pas qu'il change l'ordre des instructions en mettant volatile...(en effet, sinon, le compilateur peut tout exécuter
comme il veut et optimiser comme il veut le programme, ce qui cause des programmes si on a des variables partagées avec plusieurs threads)

C'est important car c'est en relation avec le memory model...
Commutation des threads prend du temps car il faut écrire toutes les variables de la mémoire cache dans la mémoire centrale -> processeur écrit uniquement
les variables volatiles dans la mémoire, ce qui permet de gagner du temps....

Java: premier langage qui est venu avec un memory model... Les visibilités des variables doivent être précisées dans certaines situations, et le compilateur doit se
débrouiller pour faire ce qu'on lui demande...

Interrupt change juste la valeur d'un flag...

Pas oublier de libérer la sémaphore !!!

Sémaphore entière: elles peuvent prendre plusieurs valeurs...
s.value $\geq$ 0
s.value = $1 - #P + #V$

# Producteur consommateur

On va toujours être plus lent que le plus lent des deux
