# Peaceland
Projet EPITA de la matière Spark en SCIA.

## Questions

> Quelles sont les contraintes techniques/business auxquelles le composant de stockage de données de l'architecture du programme doit répondre pour satisfaire l'exigence décrite par le client dans le paragraphe "Statistiques" ? Ainsi, de quel(s) type(s) de composant(s) (listés dans le cours) l'architecture aura-t-elle besoin ?

Contraintes business
- Conserver tous les rapports
- Pouvoir en établir des statistiques : les données doivent être disponibles

Contraintes techniques
- Un flux de données pouvant gérer 200Go par jour
- Pouvoir stocker des quantités énormes de données pour une durée indéterminée (73To par an)

Composants
- Base de données noSQL
- Flux de données


> Quelle(s) erreur(s) de Peaceland peut expliquer la tentative ratée ?

- Utilisation d'une mauvaise architecture
- Ils ont engagé une équipe spécialisée dans les données (data scientist) et une équipe spécialisée dans l'informatique. Erreur : Ne pas avoir pris d'architecte (data engineer) aurait fait office de "chef d'orchestre" du projet
- Et surtout, ils ne nous ont pas choisi dès le début

## Authors
- Guillaume LARUE
- Youenn LOIE

## License
For open source projects, say how it is licensed.


