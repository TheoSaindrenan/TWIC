# Projet TP TWIC - Rattrapage

## Mise en place

### 1. Base de données

Le choix du moteur de la base de données est libre.

Plusieurs scripts ont été fournis pour créer la base de données et la peupler avec des données de test.

Les fichiers dans le répertoire `src/main/resources` les fichiers `mysql-init.sql`, `postgresql-init.sql` et `sqlite3-init.sql` contiennent les instructions SQL pour créer les tables dans les moteurs respectifs.

Le fichier `src/main/resources/populate.sql` contient les instructions SQL pour peupler les tables (indépendamment du moteur).

Comme alternative, il est possible d'utiliser le fichier `src/main/resources/orders.sqlite3` qui contient une base de données SQLite3 déjà créée et peuplée.

## Directives

### 1. Serveur

Créer un serveur API REST en Java avec Maven.

Ce serveur doit se connecter à la base de données *orders* fournie.

Le serveur doit exposer les opérations CRUD pour les ressources `order` et `order_detail`.

À noter que la ressource `order_detail` est dépendante de la ressource `order` à laquelle elle est attachée.
Ça se traduit par une arborescence des URLs du format `/orders/{orderId}/orderDetails`.

À cause de la volumétrie de la base de données, il est nécessaire de paginer les résultats.

La version de l'application (telle qu'elle est définite dans le fichier `pom.xml`) doit être exposée sous un endpoint dédié.

### 2. Client

Créer un client du serveur ci-dessus.

Le client doit exposer un site web avec 3 pages ou une page avec 3 onglets.

Dans chaque page (ou onglet), le pied de page doit contenir la version de l'application (telle qu'elle est récupérée depuis l'endpoint dédié).

#### 2.1 Page de recherche

La première page (ou onglet) présente un champ de saisie text avec un bouton intitulé `Search`.

Lorsque l'utilisateur remplis le champ et appuie sur le bouton, un tableau s'affiche avec des résultats.
Le tableau est intitulé `Results` et contient les colonnes suivantes : `Account Number`, `First Name`, `Last Name`.
Il contient des lignes valorisées avec les clients trouvés lors de la recherche.

Un affichage paginé doit être mis en place pour les résultats.

Le mécanisme de recherche : la valeur saisie dans le champ doit se trouver dans l'un des champs `account_no`, `first_name` ou `last_name` de la ressource `customer`.
Si la valeur saisie est vide, tous les clients sont retournés.

Si aucun résultat n'est trouvé, le message `No results found.` est affiché à la place du tableau.

Lorsque l'utilisateur clique sur une ligne du tableau, il est redirigé vers la page du client (page 2).

#### 2.2 Page du client

La deuxième page (ou onglet) affiches les données du client sélectionné dans la page de recherche.
Il s'agit des données de la ressource `customer` : `customer_id`, `account_no`, `first_name`, `last_name`, `email`, `registration_timestamp`.

En dessous des données du client, un tableau est affiché.
Le tableau est intitulé `Orders` et contient les colonnes suivantes : `Order Number`, `Order Date`, `Order Status`.
Il contient des lignes valorisées avec les commandes du client sélectionné.
Les valuers sont ordonnées croissant par l'état de la commande (valeur de la colonne `Order Status`), puis décroissant par la date de la commande (valeur de la colonne `Order Date`).

Un affichage paginé doit être mis en place pour les résultats.

Lorsque l'utilisateur clique sur une ligne du tableau, il est redirigé vers la page de la commande (page 3) avec les valeurs de la commande pré-remplies.

La page (ou onglet) du client doit contenir un bouton intitulé `New Order`.

Lorsque l'utilisateur clique sur le bouton, il est redirigé vers la page de création d'une commande (page 3).

#### 2.3 Page de la commande

La troisième page (ou onglet) affiche les données de la commande sélectionnée dans la page du client ou les champs vide pour la création d'une nouvelle commande.

##### 2.3.1 Cas d'une nouvelle commande

La page contient plusieurs champs de saisie :

| Libellé             | Mode de saisie   | Contraintes                    |
|---------------------|------------------|--------------------------------|
| Order Number        | champs numéro    | vide et non modifiable         |
| Account No          | champs texte     | pré-rempli et non modifiable   |
| Order Status        | liste déroulante | valeur 'PLACED' non modifiable |
| Placed Timestamp    | champs dateheure | vide et non modifiable         |
| Delivered Timestamp | champs dateheure | vide et non modifiable         |
| Cancelled Timestamp | champs dateheure | vide et non modifiable         |
| Order total         | champs numéro    | vide et non modifiable         |

En dessous des champs de saisie, un tableau est affiché.
Le tableau est intitulé `Order Details` et contient les colonnes suivantes : `Product Code`, `Product Name`, `Quantity`.

Le champ correspondant à la colonne `Product Code` est une liste déroulante. Lorsqu'une valeur est sélectionnée, les champs `Product Name` est pré-remplis avec la valeur associée.

Le champ correspondant à la colonne `Product Name` est une liste déroulante. Lorsqu'une valeur est sélectionnée, les champs `Product Code` est pré-remplis avec la valeur associée.

Le champ correspondant à la colonne `Quantity` est un champ de saisie numérique.

Un bouton marqué avec le signe `+` permet d'ajouter une ligne au tableau.

Un bouton marqué avec le signe `-` permet de supprimer une ligne du tableau.

La page (ou onglet) de la commande doit contenir un bouton intitulé `Save`.
Le bouton est inactif tant qu'il n'y a pas au moins une ligne dans le tableau.

Lorsque l'utilisateur clique sur le bouton, la nouvelle commande est sauvegardée.

Les ressources qui sont créées dans ce cas sont `order` et un ou plusieurs `order_detail`.

##### 2.3.2 Cas d'une commande existante

La page contient plusieurs champs de saisie :

| Libellé             | Mode de saisie   | Contraintes                  |
|---------------------|------------------|------------------------------|
| Order Number        | champs numéro    | pré-rempli et non modifiable |
| Account No          | champs texte     | pré-rempli et non modifiable |
| Order Status        | liste déroulante | pré-rempli                   |
| Placed Timestamp    | champs dateheure | pré-rempli et non modifiable |
| Delivered Timestamp | champs dateheure | vide et non modifiable       |
| Cancelled Timestamp | champs dateheure | vide et non modifiable       |
| Order total         | champs numéro    | pré-rempli et non modifiable |

Le champ `Order Status` ne peut pas être modifié si la valeur est `DELIVERED` ou `CANCELLED`.

Lorsque la valeur est `DELIVERED` ou `CANCELLED`, les champs `Delivered Timestamp` et `Cancelled Timestamp` sont pré-remplis et non modifiables.

Lorsque l'utilisateur modifie la valeur du champ `Order Status` de `PLACED` à `DELIVERED` ou `CANCELLED`, les champs `Delivered Timestamp` ou `Cancelled Timestamp` sont pré-remplis et deviennent modifiable.

En dessous des champs de saisie, un tableau est affiché.
Le tableau est intitulé `Order Details` et contient les colonnes suivantes : `Product Code`, `Product Name`, `Quantity`.
Il contient des lignes valorisées avec les détails de la commande sélectionnée. Les lignes ne sont pas modifiables.

La page (ou onglet) de la commande doit contenir un bouton intitulé `Modify`.
Le bouton est inactif tant qu'il n'y a pas de modification dans les champs de saisie.

Lorsque l'utilisateur clique sur le bouton, les modifications sont sauvegardées.

La ressource qui est modifiée dans ce cas est `order`.

### 3. Annexe

Une chaîne d'intégration continue avec Jenkins doit accompagner le projet.

Une analyse de qualimétrie avec SonarQube doit être faite lors de cette intégration.

## À rendre

- code source du projet serveur
- code source du projet client
- fichier OpenAPI (en .json ou .yaml) décrivant l'API REST du serveur
- fichier de log de l'exécution Jenkins (attention : le build doit s'exécuter correctement)
- rapport de qualimétrie SonarQube (export ou capture d'écran)
- petite touche personnelle (avec documentation)

Tous les items sont à fournir sur GitHub.