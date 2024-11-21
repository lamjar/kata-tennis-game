# Étape 1 : Utiliser une image Java officielle
FROM eclipse-temurin:17-jdk-alpine

# Étape 2 : Définir le répertoire de travail
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
COPY target/kata-tennis-game.jar app.jar

# Étape 4 : Définir la commande par défaut pour exécuter l'application
CMD ["java", "-jar", "app.jar"]
