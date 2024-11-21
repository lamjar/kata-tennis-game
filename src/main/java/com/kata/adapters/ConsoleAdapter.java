package com.kata.adapters;

import com.kata.application.MatchService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleAdapter {

    /**
     * Affiche la bannière de bienvenue.
     * - Essaie de lire la bannière depuis un fichier texte.
     * - Si le fichier n'est pas disponible, une bannière par défaut est affichée.
     */
    private static void displayBanner() {
        // Chemin du fichier de bannière dans les ressources du projet
        String bannerFilePath = "src/main/resources/banner.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(bannerFilePath))) {
            String line;
            // Lecture et affichage du contenu du fichier ligne par ligne
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // Si une exception est levée (fichier non trouvé, problème d'accès, etc.),
            // on affiche une bannière par défaut
            System.out.println("****************************************************");
            System.out.println("*              Welcome to Tennis Game              *");
            System.out.println("*           Developed with Hexagonal Design        *");
            System.out.println("*                   By Lamjed JARRAY               *");
            System.out.println("****************************************************");
        }
    }

    /**
     * Méthode principale qui orchestre l'ensemble du flux de l'application en mode console.
     * - Affiche la bannière de bienvenue.
     * - Demande à l'utilisateur les noms des joueurs.
     * - Lance une partie avec la séquence de jeu donnée par l'utilisateur.
     */
    public void start() {
        // Afficher la bannière de bienvenue ou une bannière par défaut
        displayBanner();
        // Création d'un objet Scanner pour lire les entrées utilisateur
        Scanner scanner = new Scanner(System.in);
        String playerAName = "A";
        String playerBName = "B";
        // Création d'un service de match qui va gérer le jeu avec les noms des joueurs
        MatchService service = new MatchService(playerAName, playerBName);

        // Demande la séquence de jeu, représentant les points gagnés par chaque joueur
        System.out.println("Enter the game sequence (e.g., ABABAA):");
        String sequence = scanner.nextLine();

        // Appel à MatchService pour jouer la partie avec la séquence donnée
        service.playGame(sequence);
    }
}
