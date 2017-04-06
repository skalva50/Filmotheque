#TODO LIST
--------------------
## Mineures
------------------------

#### fiche
+ Ajouter la note dans les fiches film et serie
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
      + Supprimer le choix des pages si une seule page (vue.jsp)
      + Factoriser les differents filtre de film (tenter une factorisation generique)
      + Factoriser get et getPage
      + Modifier tri (par comparateur dans list au lieu de par requete : permet de trier si issue de differntes liste)
      + Ajouter les nationalites dans le filtre
      + Ajouter un sens de tri par defaut ( ex dateSortie--> desc, titre --> asc ...)
      + Bouton supprimer filtre

#### Onlet Acteur et réalisateur
+ a faire avec solution de pagination, fitre, tri trouvé precedamment. 

#### Admin du site
+ MAJ de la BDD par rapport aux des données supprimées des repertoires
+ Maj des fiches films si erreur de correpondance

#### Securité accès
+ Gestion des droits d'accès (spring security)
+ Gestion des droits BDD
