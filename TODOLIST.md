#TODO LIST
--------------------
## Mineures
------------------------

#### fiche
+ OK Ajouter la note dans les fiches film et serie
+ Ajouter nombre de vote
+ Voir si bio en fançais plus pertinente
+ OK format affichage date

#### Organisation
+ Determiner objectif de la prochaine release  
+ Lister les modifs à effectuer lors de la réalisation d'une release
     + Ajouter le war (se fait dans la fiche de release github)
     + Mettre application.properties en générique (login, mdp ...)

------------------------------------
## Majeures
---------------------------------
#### Mise en forme resultats
+ OK Pagination 
+ Systeme de filtre et de tri
     + RESTE A FAIRE :     
      + OK Factoriser les differents filtre de film (tenter une factorisation generique)
      + OK Factoriser get et getPage
      + OK Modifier tri (par comparateur dans list au lieu de par requete : permet de trier si issue de differntes liste)
      + OK Ajouter les nationalites dans le filtre
      + OK Ajouter un sens de tri par defaut ( ex dateSortie--> desc, titre --> asc ...)
      + OK Bouton supprimer filtre
      + OK Trier les items des filtres
      + OK Afficher les items filtres uniquement disponible pour l'affichage en cours
      + OK Creer une abstract et interface generique pour les services
      + OK Creer une interface generique pour la DAO
      + creer une abstract ou une interface pour les filtres      


#### Onglet Acteur et réalisateur
+ a faire avec solution de pagination, fitre, tri trouvé precedamment.
+ ajouter le sexe(filtre), popularite(tri) des personnes (acteurs+realisateur)
+ OK Fiche realisateur : ajouter les series

#### Admin du site
+ MAJ de la BDD par rapport aux des données supprimées des repertoires
+ Maj des fiches films si erreur de correpondance
+ Rapport ajout et suppression en fin de manip
+ Date d'ajout du media

#### Securité accès
+ Gestion des droits d'accès (spring security)
+ Gestion des droits BDD


