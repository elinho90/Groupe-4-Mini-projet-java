Résumé de notre projet 

Ce programme en Java permet de gérer les bulletins scolaires de manière simple et organisée. Il s’articule autour de deux éléments principaux :  

1. Les matières : Chaque matière a un nom, une note (entre 0 et 20), un coefficient, et une méthode pour calculer son impact sur la moyenne (note × coefficient).  
2. Les élèves : Ils ont des informations personnelles (nom, prénom, classe, etc.) et une liste des matières qu’ils suivent. Le programme calcule leur moyenne générale en tenant compte des coefficients, et permet de les classer du meilleur au moins bon.  

 Comment ça marche ?  
- Saisie des élèves : On commence par entrer le nombre d'élèves, puis leurs détails (nom, prénom, etc.).  
- Saisie des notes : Pour chaque élève, on renseigne les notes matière par matière, avec une vérification pour éviter les erreurs (une note doit être entre 0 et 20).  
- Calcul et classement : Le programme calcule automatiquement la moyenne de chaque élève et les classe par ordre de mérite.  
- Génération des bulletins : Enfin, un fichier texte est créé pour chaque élève, résumant ses résultats, sa moyenne et son rang dans la classe.  

Une fois terminé, le programme confirme que tous les bulletins ont bien été générés. 
