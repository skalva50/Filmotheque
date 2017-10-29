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
+ OK Systeme de filtre et de tri

#### Fonction Download
+ OK
      
#### Onglet Acteur et réalisateur
+ OK a faire avec solution de pagination, fitre, tri trouvé precedamment.
+ OK ajouter le sexe(filtre)OK, popularite(tri) des personnes (acteurs+realisateur); ajouter au cast de tmdb vers entite bdd
+ OK Fiche realisateur : ajouter les series

#### BugFix

+ OK Ordre de tri des épisodes
+ Nom dossier: supprimer la sensibilité à la casse
+ OK Ajout du sexe des personnes
+ OK si note null, mettre 0
+ Suppression des acteurs et réalisateurs ne se fait qu'après un deuxième lancement de suppression
+ OK Ordre de tri des épisodes
+ Aligner les filtres dans page jsp
+ Absence de tmdb key
+ Ne pas executer actualisation si dossier serie/film introuvable

#### Onglet A propos
+ version du site (a voir avec version maven)
+ OK credits

#### Admin du site
+ OK MAJ de la BDD par rapport aux des données supprimées des repertoires
+ OK Maj des fiches films et serie si erreur de correpondance    
+ OK Rapport ajout et suppression en fin de manip
+ OK Date d'ajout du media

#### Securité accès
+ Gestion des droits d'accès (spring security)
    + OK suppression du user
    + Gerer la verif de password
    + OK Mise en forme de la page admin
    + Afficher la nom de l'utilisateur dans la barre de menu

+ Gestion des droits BDD

#### Logs
+ Supprimer les system.out
+ Supprimer le log des requete SQL
+ implementer Log4J ou autre solution
+ trapper les exceptions potentiel dans le log (abstract dao par exemple) 

#### Accueil
+ Expliquer les fonctionnalités principales dans la page d'accueil


