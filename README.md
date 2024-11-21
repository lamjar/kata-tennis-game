# Tennis Game Kata

## Description

Ce projet implémente un système de calcul des scores pour un jeu de tennis en utilisant les principes de l'*
*architecture hexagonale**. Le programme gère les règles spécifiques au tennis, y compris les notions de **Deuce** et *
*Avantage**, et affiche le score après chaque point.

---

## Fonctionnalités

- Calcul des scores selon les règles du tennis.
- Gestion des cas spéciaux : **Deuce**, **Avantage**, et victoire d'un joueur.
- Interface console pour saisir les noms des joueurs et la séquence de jeu.
- Affichage interactif des résultats après chaque point.

---

## Prérequis

- **Java JDK 11** ou une version supérieure.
- Un IDE compatible avec Java ou un terminal pour exécuter les commandes Maven.
- **Maven** installé (si vous compilez manuellement).

---

## Installation

1. **Cloner le dépôt** :
   ```bash
   git clone https://github.com/lamjar/kata-tennis-game.git
   cd kata-tennis-game
   ```

2. **Compiler le projet** (si Maven est utilisé) :
   ```bash
   mvn clean install
   ```

3. **Exécuter le programme** :
   ```bash
   mvn exec:java
   ```

---

## Structure du projet

Le projet est structuré selon l'architecture hexagonale. Voici les principaux composants :

- **Domain** :
    - Contient les classes principales comme `Match` et `Player`.
    - Gère la logique métier du jeu.

- **Application** :
    - Inclut le service `MatchService` qui orchestre les interactions entre le domaine et les adaptateurs.

- **Adapters** :
    - Fournit des interfaces utilisateur, comme `ConsoleAdapter`, qui permet d’interagir avec le programme via la
      console.

- **Tests** :
    - Contient les tests unitaires pour valider le fonctionnement des composants.

---

## CI (Continuous Integration)

Le projet utilise l'intégration continue (CI) via GitHub Actions pour automatiser les étapes de construction et de tests. 
Chaque fois qu'il y a un push ou une pull request vers la branche `main`, les étapes suivantes sont exécutées :
- Construction du projet avec Maven.
- Exécution des tests.
- Construction de l'image Docker.

Les résultats des builds sont disponibles sur GitHub Actions :

[Voir les Actions CI sur GitHub](https://github.com/lamjar/kata-tennis-game/actions)

Le fichier de configuration de GitHub Actions se trouve ici :
[build.yml](https://github.com/lamjar/kata-tennis-game/blob/main/.github/workflows/build.yml)


## Manuel d'utilisation

1. Lors de l'exécution, le programme affiche une bannière de bienvenue.
2. Fournissez la séquence des points gagnés (exemple : `ABABAA`) :
   ```
   Enter the game sequence (e.g., ABABAA):
   ABABAA
   ```
3. Le programme affiche les résultats après chaque point et annonce le vainqueur :
   ```
   Point 1: Player A: 15 / Player B: 0
   Point 2: Player A: 15 / Player B: 15
   Point 3: Player A: 30 / Player B: 15
   Point 4: Player A: 30 / Player B: 30
   Point 5: Player A: 40 / Player B: 30
   Player A wins the game
   ```

---

## Tests

Pour exécuter les tests unitaires :

```bash
mvn test
```

Les tests valident les règles du jeu, notamment les cas de **Deuce** et **Avantage**, ainsi que l’affichage des scores.

---

### Exemple des scénarios

* ABABABABABAB

```text
****************************************************
*              Welcome to Tennis Game              *
*           Developed with Hexagonal Design        *
*                   By Lamjed JARRAY               *
****************************************************
Enter the game sequence (e.g., ABABAA):
ABABABABABAB
Player A: 15 / Player B: 0
Player A: 15 / Player B: 15
Player A: 30 / Player B: 15
Player A: 30 / Player B: 30
Player A: 40 / Player B: 30
Deuce
Advantage A
Player A wins the game 
```

* AABBAABBA

```text
****************************************************
*              Welcome to Tennis Game              *
*           Developed with Hexagonal Design        *
*                   By Lamjed JARRAY               *
****************************************************
Enter the game sequence (e.g., ABABAA):
AABBAABBA
Player A: 15 / Player B: 0
Player A: 30 / Player B: 0
Player A: 30 / Player B: 15
Player A: 30 / Player B: 30
Player A: 40 / Player B: 30
Player A wins the game
```

* ABBBBAA

```text
****************************************************
*              Welcome to Tennis Game              *
*           Developed with Hexagonal Design        *
*                   By Lamjed JARRAY               *
****************************************************
Enter the game sequence (e.g., ABABAA):
ABBBBAA
Player A: 15 / Player B: 0
Player A: 15 / Player B: 15
Player A: 15 / Player B: 30
Player A: 15 / Player B: 40
Player B wins the game
```

## Contribution

1. **Fork le projet**.
2. Créez une branche pour vos modifications :
   ```bash
   git checkout -b feature/ma-fonctionnalite
   ```
3. Envoyez vos modifications et créez une Pull Request.

---

## Licence

Ce projet est sous licence MIT. Vous pouvez librement l'utiliser, le modifier et le distribuer.

--- 

