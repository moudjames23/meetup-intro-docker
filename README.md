

# Meetup débuter avec Docker
Ce document vous permettra de comprendre les bases de docker. En pratique, on utilisatera un projet **Spring-boot (Java)** comme backend, un projet **React** comme frontend.

 ## 1. Qu'est ce que Docker ?
 Docker est comme une boîte virtuelle pour votre logiciel. Cette boîte contient tout ce dont le logiciel a besoin pour fonctionner, peu importe où vous mettez cette boîte. Cela signifie que si le logiciel fonctionne dans la boîte (Docker) sur votre machine, il fonctionnera également dans la même boîte sur une autre machine, sans nécessiter d'ajustements supplémentaires. En d'autres termes, Docker permet de "conteneuriser" les applications, garantissant qu'elles fonctionnent de la même manière partout.
 
 ## 2. Quelques avantages de Docker

 - ***Portabilité***: Une fois une application "conteneurisée", elle peut être exécutée sur n'importe quelle machine équipée de Docker, garantissant une uniformité entre les environnements de développement, de test et de production.
       
  
 - ***Consistance***: Les conteneurs garantissent que l'application fonctionne de la même manière quel que soit l'endroit où elle est  déployée, ce qui élimine les problèmes classiques du type "ça   
   fonctionne sur ma machine".

       

 - ***Déploiement rapide***: Contrairement aux machines virtuelles, les conteneurs peuvent être démarrés en quelques secondes, ce qui accélère considérablement le développement et le déploiement.

       
   

 - ***Gestion efficace des ressources***: Les conteneurs sont plus légers que les machines virtuelles, car ils partagent le même OS kernel et n'ont pas besoin d'un système d'exploitation complet.

       
 

 - ***Isolation des dépendances***: Les applications conteneurisées peuvent avoir leurs propres versions de dépendances sans interférer  avec d'autres applications, garantissant ainsi une meilleure gestion et moins de conflits.

       
  

 - ***Intégration et déploiement continus***: Docker s'intègre facilement avec des outils de CI/CD, rendant l'automatisation et l'orchestration des déploiements plus fluides.



## 3. Installation de Docker

### Windows

1.  **Docker Desktop** : Il s'agit de l'application recommandée pour exécuter Docker sur Windows.
    
2.  **Prérequis** :
    
    -   Assurez-vous que la virtualisation matérielle est activée dans les paramètres du BIOS.
    -   Docker Desktop nécessite Windows 10 Pro ou Enterprise Version 15063 ou ultérieure.
3.  **Installation** :
    
    -   Allez sur le site officiel de Docker : [https://www.docker.com](https://www.docker.com/)
    -   Téléchargez Docker Desktop pour Windows.
    -   Exécutez le fichier d'installation téléchargé.
    -   Suivez les instructions à l'écran.
    
## Mac

1.  **Docker Desktop** : C'est l'application recommandée pour exécuter Docker sur Mac.
    
2.  **Prérequis** :
    
    -   Docker Desktop nécessite macOS Sierra 10.12 ou version ultérieure.
3.  **Installation** :
    
    -   Allez sur le site officiel de Docker : [https://www.docker.com](https://www.docker.com/)
    -   Téléchargez Docker Desktop pour Mac.
    -   Ouvrez le fichier `.dmg` téléchargé et glissez l'icône Docker dans votre dossier Applications.

## Linux (premier exemple avec Ubuntu)

1.  **Prérequis** : Mettez à jour les paquets existants.
    
    bashCopy code
    
    `sudo apt-get update` 
    
2.  **Installation des dépendances nécessaires** :
    
    `sudo apt-get install \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg-agent \
        software-properties-common` 
    
3.  **Ajoutez la clé GPG officielle de Docker** :
    
    
`curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`

 
    
4.  **Ajoutez le référentiel Docker à APT** :
   
    
    `sudo add-apt-repository \
       "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
       $(lsb_release -cs) \
       stable"` 
    
5.  **Installez Docker** :
    
    
    `sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io` 
    
6.  **Pour exécuter Docker sans `sudo` (facultatif)** :
    
    
    `sudo usermod -aG docker $USER` 
    
    Puis déconnectez-vous et reconnectez-vous ou redémarrez la session pour que les modifications prennent effet.## Linux (premier exemple avec Ubuntu)

1.  **Prérequis** : Mettez à jour les paquets existants.
    
    
    `sudo apt-get update` 
    
2.  **Installation des dépendances nécessaires** :
   
    
    `sudo apt-get install \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg-agent \
        software-properties-common` 
    
3.  **Ajoutez la clé GPG officielle de Docker** :
    
    
    `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -` 
    
4.  **Ajoutez le référentiel Docker à APT** :
    
    
    `sudo add-apt-repository \
       "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
       $(lsb_release -cs) \
       stable"` 
    
5.  **Installez Docker** :
    
    
    `sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io` 
    
6.  **Pour exécuter Docker sans `sudo` (facultatif)** :
    
    
    `sudo usermod -aG docker $USER` 
    
    Puis déconnectez-vous et reconnectez-vous ou redémarrez la session pour que les modifications prennent effet.

On va vérifier tout fonctionne normalement, taper la commande suivante pour afficher la version de Docker: 

    docker --version

*Docker version 20.10.14, build a224086*
 
 ## 4. Dockerfile, Image et Container

![Reference](https://miro.medium.com/v2/resize:fit:1079/1*3ds-PdxGGMN-ZzJH95_lsA.png)

Prenons une analogie simple pour expliquer ces concepts.
### Dockerfile

***Nous allons travailler dans le repertoire backend. Assurez-vous d’être  dans le bon dossier.***
Imaginons que vous souhaitez préparer un gâteau.

Le **Dockerfile** est comme une recette de cuisine. Il contient une liste d'instructions étape par étape sur la façon de préparer quelque chose. Dans le monde Docker, cela signifie des instructions sur la façon de créer une image. Ces instructions pourraient inclure des choses comme:

-   De quel système d'exploitation avez-vous besoin comme base ? (Ex. : "Commencez avec une base de gâteau au chocolat.")
-   Quels logiciels ou dépendances doivent être installés ? (Ex. : "Ajoutez des pépites de chocolat et des noix.")
-   Quelles configurations doivent être modifiées ? (Ex. : "Saupoudrez un peu de sucre glace sur le dessus.") 
### Image

Une fois que vous avez suivi toutes les étapes de la recette, vous obtenez un gâteau fini. Dans Docker, une fois que vous avez suivi toutes les instructions du Dockerfile, vous obtenez une **Image**.

L'**Image** est comme un gâteau non coupé. Il s'agit d'un "snapshot" statique qui contient tout ce dont vous avez besoin pour exécuter votre application, mais qui n'est pas encore en cours d'exécution. Vous pouvez prendre cette image, la partager avec d'autres, ou la déployer ailleurs. C'est une unité empaquetée et prête à l'emploi.

Pour résumer:

-   **Dockerfile** = Recette de cuisine.
-   **Image** = Gâteau fini, prêt à être servi.
-   **Conteneur** = Une part du gâteau que vous mangez actuellement.

### a. Dockerfile, la recette: 
Pour créer un dockerfile, il faudra aller à la racine du projet et creer un fichier en lui donne comme nom: **Dockerfile**. 

 - Le ***D*** doit être  en majuscule
 - Le fichier n'a pas d'extension.

Avant toute chose, on doit avoir des informations sur le projet qu'on doit "dockerizer", dans notre cas, comme mentionné plus haut c'est un projet Spring boot écrit en Java. Pour qu'il fonctionne on a besoin du ***JDK*** (Java Development Kit) et ***Maven*** qui est le gestionnaire de dépendances. 

Ouvrez le fichier Dockerfile et tapez cette commande: 

    FROM  openjdk:17-alpine3.14

> Cette instruction détermine l'image de base à utiliser pour le
> conteneur. Dans ce cas, c'est une image avec OpenJDK 17 installé,
> basée sur la version `alpine3.14`, qui est une distribution Linux
> légère. Cela signifie que votre application fonctionnera dans un
> environnement avec Java 17.

Notre seconde recette: 

    WORKDIR  app/

> Cette instruction définit le répertoire de travail dans le conteneur.
> Toutes les commandes ultérieures s'exécuteront à partir de ce
> répertoire. Ici, il crée (ou utilise s'il existe déjà) un répertoire
> appelé `app` à la racine du conteneur.

    COPY  mvnw  .

> Copie le fichier `mvnw` (qui est le Maven Wrapper script pour les
> systèmes Unix-like) du répertoire actuel (sur votre machine) vers le
> répertoire de travail du conteneur (qui est `app/`).

    COPY  .mvn  .mvn

> Copie le dossier `.mvn` (qui contient des configurations pour le Maven
> Wrapper) du répertoire actuel vers le répertoire de travail du
> conteneur.

    COPY  pom.xml  .

> Copie le fichier `pom.xml` (qui est le fichier de configuration
> principal de Maven) du répertoire actuel vers le répertoire de travail
> du conteneur.

    COPY  src  src

> Copie le dossier `src` (qui contient généralement le code source de
> votre application) du répertoire actuel vers le répertoire `src` dans
> le répertoire de travail du conteneur.

    RUN  ./mvnw  clean  package  -DskipTests

> Exécute la commande Maven pour compiler et empaqueter l'application.
> Elle utilise le Maven Wrapper (`./mvnw`), nettoie les anciens builds
> (`clean`), crée un paquet (`package`), et saute les tests
> (`-DskipTests`). À la fin de cette étape, vous aurez généralement un
> fichier `.jar` dans le répertoire `target`.

    EXPOSE  8080

> Indique que le conteneur devrait écouter sur le port 8080. C'est
> généralement le port par défaut pour les applications Spring Boot.

    ENTRYPOINT  ["java",  "-jar",  "target/spring-collab.jar"]

> Définit la commande à exécuter lorsqu'un conteneur est démarré à
> partir de cette image. Ici, il lance l'application Spring Boot en
> exécutant le fichier JAR `spring-collab.jar` dans le répertoire
> `target`.

Au final, on aura un fichier Dockerfile avec ce contenu: 

    # Utilisez l'image openjdk version 17 basée sur alpine3.14 comme image de base pour notre conteneur
    FROM openjdk:17-alpine3.14
    
    # Définit le répertoire de travail à 'app/' dans le conteneur
    WORKDIR app/
    
    # Copie le script Maven Wrapper du répertoire actuel vers le répertoire de travail du conteneur
    COPY mvnw .
    
    # Copie le répertoire de configuration Maven Wrapper du répertoire actuel vers le répertoire de travail du conteneur
    COPY .mvn .mvn
    
    # Copie le fichier de configuration Maven (pom.xml) du répertoire actuel vers le répertoire de travail du conteneur
    COPY pom.xml .
    
    # Copie le dossier contenant le code source de l'application du répertoire actuel vers le répertoire de travail du conteneur
    COPY src src
    
    # Exécute le script Maven Wrapper pour nettoyer les anciens builds, empaqueter l'application et sauter les tests
    RUN  ./mvnw clean package -DskipTests
    
    # Indique que le conteneur écoutera sur le port 8080 une fois qu'il sera en cours d'exécution
    EXPOSE 8080
    
    # Définit la commande à exécuter lorsqu'un conteneur est lancé à partir de cette image (lancement de l'application Spring Boot)
    ENTRYPOINT ["java", "-jar", "target/spring-collab.jar"]


À cet stade nous avons finis de définir notre recette. 

### b. L'Image: Le gâteau fini, prêt à être servi: 
Pour créer une image à partir du Dockerfile qu'on vient d'ecrire, il faudra taper la commande suivante: 

    docker build -t spring-backend:0.0.1 .

***docker build:*** permet de construire l'image
***-t spring-backend:0.0.1:*** permet de donner un tag à l'image dans notre cas `spring-backend:0.0.1`
***.:*** permet de specifier ou se trouve le Dockerfile, on a mis le point parce que l'on est dans le même repertoire. 

La commande suivante permet d'affiche la liste des images: 

    docker images

Vous sentez l'odeur de notre gâteau là  😂 ? 
Félicitations 🎉 , nous avons cuisiné notre gâteau, maintenant place à la dégustation

### c. Conteneur, dégustons !: 
Nous allons utilisez la commande docker run

    docker run -p 8080:8080 spring-backend:0.0.1
  
Bien sûr, voici une explication simple de la commande :

bashCopy code

`docker run -p 8080:8080 spring-backend:0.0.1` 

-   `docker run`: Lance un conteneur Docker à partir d'une image.
-   `-p 8080:8080`: Associe le port 8080 de votre machine au port 8080 du conteneur. Cela signifie que lorsque vous accédez au port 8080 de votre ordinateur, vous accédez en fait au port 8080 du conteneur.
-   `spring-backend:0.0.1`: C'est le nom et la version de l'image Docker que vous souhaitez exécuter. Ici, vous lancez un conteneur à partir de l'image nommée "spring-backend" avec le tag "0.0.1".  
Bien sûr, voici une explication simple de la commande :

bashCopy code

`docker run -p 8080:8080 spring-backend:0.0.1` 

-   `docker run`: Lance un conteneur Docker à partir d'une image.
-   `-p 8080:8080`: Associe le port 8080 de votre machine au port 8080 du conteneur. Cela signifie que lorsque vous accédez au port 8080 de votre ordinateur, vous accédez en fait au port 8080 du conteneur.
-   `spring-backend:0.0.1`: C'est le nom et la version de l'image Docker que vous souhaitez exécuter. Ici, vous lancez un conteneur à partir de l'image nommée "spring-backend" avec le tag "0.0.1".

Vous aviez remarqué que la console n'est plus accessible après  avoir tape cette commande à moins de taper `ctrl + c`. Rajouter l'option -d pour lancer le container en `detach mode`

    docker -d  run -p 8080:8080 spring-backend:0.0.1
En allant a cette adresse `http://127.0.0.1:8080/api/v1/students`  depuis votre navigateur, vous êtes  censé avoir une reponse de notre API. 

Bravo 🎊 🎈 , vous venez de déguster notre gâteau. Je crois qui serait temps de donner un nom à notre gâteau, vous ne trouvez pas ? 

Pour afficher la liste des containers, on va taper la commande suivante: 

    docker ps

Voici une explication de chaque colonne standard :

1.  **CONTAINER ID** :
    
    -   Il s'agit d'un identifiant unique pour chaque conteneur. C'est une chaîne alphanumérique et elle peut être utilisée pour faire référence au conteneur dans d'autres commandes Docker.
2.  **IMAGE** :
    
    -   Le nom de l'image Docker à partir de laquelle le conteneur a été créé.
3.  **COMMAND** :
    
    -   La commande qui a été exécutée dans le conteneur au démarrage. Cela est souvent spécifié dans le Dockerfile ou lors du lancement du conteneur.
4.  **CREATED** :
    
    -   Indique quand le conteneur a été créé. Il vous donne une idée de l'âge du conteneur.
5.  **STATUS** :
    
    -   Le statut actuel du conteneur. Cela pourrait indiquer si le conteneur est en cours d'exécution, s'il est arrêté ou s'il a été exécuté pendant une certaine durée.
6.  **PORTS** :
    
    -   Les ports réseau sur lesquels le conteneur est en écoute et comment ils sont mappés sur les ports de l'hôte. Par exemple, `0.0.0.0:80->80/tcp` signifie que le port 80 du conteneur est mappé sur le port 80 de votre machine.

Lorsqu'on ne precise pas de nom en lançant le conteneur, il lui attribue automatiquement. Maintenant, donnons un nom a notre gâteau en rajoutant l'option `--name`

    docker run -d --name gateau-backend  -p 8081:8080 spring-backend:0.0.1
   
En tapant `docker ps`, vous verez que la colonne `NAMES` a changé. 

Pour stopper un container, il faut taper cette commande: 

    docker stop gateau-backend

Tout est clair non ? On ne va quand même  pas expliquer cette commande. 

Si vous voulez supprimer un container il faut cette commande: 

    docker rm -f gateau-backend

 - rm: c'est l'abréviation de remove
 - -f: force

Et pour supprimer une image 

    docker rmi -f cb24eb9061da  
rmi: remove image 
l'identifiant `cb24eb9061da` est la valeur du `IMAGE ID' lorsqu'on tape la commande: 

    docker images


 ## 5. DockerHub

![Logo docker hub](https://thehackernews.com/images/-vr91El5JoT4/XMQ9hc7zmFI/AAAAAAAAz2g/RAN9QI0xHQcxxLAqiz86bo5Z_mKUQJlEQCLcBGAs/s728-e365/docker-hub-data-breach.jpg)

Après avoir préparé et dégusté notre gâteau, partageons-le avec les autres.

Docker Hub est comme une bibliothèque en ligne pour les images Docker. C'est un endroit où vous pouvez stocker, partager et télécharger ces images, un peu comme vous le feriez avec des applications sur un App Store. 
DockerHub est pour Docker, ce que GitHub est pour Git.
DockerHub est pour Docker, ce que Play Store est pour les applications android. 

Vous n'aviez toujours pas compris ? ce qu'est DockerHub

 Avant toute chose, créez votre compte [DockerHub](https://hub.docker.com/). 

Pour vous connecter à DockerHub en ligne de commande, faudra taper cette commande: 

    docker login
Après il vous sera demander votre nom d'utilisateur et votre mot de passe de DockerHub. 


### a. Push: 
Avant de pusher notre image `spring-backend` sur DockerHub, nous devons renommer notre image. Pour cela on doit taper cette commande: 

    docker tag spring-backend:0.0.1 moudjames23/spring-backend:0.0.1
  
Bien sûr, examinons la commande que vous avez fournie :

bashCopy code

`docker tag spring-backend:0.0.1 moudjames23/spring-backend:0.0.1` 

1.  **`docker tag`** : C'est la commande pour taguer une image Docker.
    
2.  **`spring-backend:0.0.1`** : C'est le nom et le tag de l'image source que vous souhaitez taguer.
    
3.  **`moudjames23/spring-backend:0.0.1`** : C'est le nouveau nom et tag que vous souhaitez donner à cette image.
    

-   **`moudjames23/`** : C'est un préfixe qui représente généralement le nom d'utilisateur ou le nom de l'organisation sur Docker Hub ou une autre registry. Dans ce cas, il semble que vous prépariez cette image à être poussée vers le compte Docker Hub de l'utilisateur "moudjames23".
    
-   **`spring-backend:0.0.1`** : C'est le nom de l'image et sa version (ou tag).

cette commande dit à Docker "Prends l'image que j'ai localement appelée `spring-backend:0.0.1` et donne-lui aussi le nom `moudjames23/spring-backend:0.0.1`". Après avoir exécuté cette commande, aucune nouvelle image n'est créée. Au lieu de cela, vous avez simplement deux noms (ou étiquettes) qui pointent vers la même image. 

> N'oubliez pas de remplacer `moudjames23` par votre d'utilisateur dockerHub

Normalement si vous vous connectez sur le compte dockerHub, vous verrez votre image` [username]/spring-backend`, dans mon cas c'est: `moudjames23/spring-backend`

### b. Pull: 
Pour puller l'image rien de plus simple: 

    docker pull moudjames23/spring-backend
Puis apres lancer la avec un `docker run `

 ## 6. Conteneurison la partie React JS

***Ici, nous allons travailler dans le repertoire `frontend/`. Assurez-vous d’être  dans le bon dossier.***
 Comme mentionner plus haut, avant de conteneuriser une application on doit d'abord chercher à comprendre quels sont les pre requis pour son bon fonctionnement. 
Avec React, il faut NodeJs donc voici le Dockerfile pour le projet: 

    # Utilise une image Docker légère basée sur Node.js 18 et Alpine Linux 3.14.
    FROM node:18-alpine3.14
    
    # Définit le répertoire de travail dans le conteneur à "/app".
    WORKDIR app/
    
    # Copie les fichiers "package.json" et "package-lock.json" (si existant) dans le répertoire de travail du conteneur.
    COPY package.*json .
    
    # Exécute "npm install" dans le conteneur, ce qui installera les dépendances du projet.
    RUN npm install
    
    # Copie le reste des fichiers de votre projet dans le répertoire de travail du conteneur.
    COPY . .
    
    # Informe Docker que l'application à l'intérieur du conteneur écoutera sur le port 5173.
    EXPOSE 5173
    
    # Définit la commande par défaut à exécuter lorsque le conteneur démarre, qui est "npm run dev" dans ce cas.
    CMD npm run dev

Tout est dans les commentaires. 
Taper cette commande pour builder l'image

    docker build -t react-frontend:0.0.1 .

Lancer le container 

    docker run -d -p 5173:5173 react-frontend:0.0.1

Maintenant coller cette url dans votre navigateur `http://127.0.0.1:5173/`
Assurez-vous que votre container `spring-backend` est bien sur le port `8080`. 

Normalement vous êtes  censé la liste des étudiants  affichée  dans votre navigateur. 

 ## 7. Docker Compose

 ## 8. MySQL & Adminer

 ## 9. Les bonnes pratiques avec Docker