package com.kata.application;

import com.kata.domain.Match;

public class MatchService {

    // Instance de la classe Match qui gère la logique de la partie
    private final Match match;

    /**
     * Constructeur de la classe MatchService.
     * Initialise un match avec les noms des joueurs A et B.
     *
     * @param playerAName Le nom du joueur A.
     * @param playerBName Le nom du joueur B.
     */
    public MatchService(String playerAName, String playerBName) {
        // Création d'une instance de Match avec les noms des joueurs
        this.match = new Match(playerAName, playerBName);
    }

    /**
     * Lance une partie en fonction de la séquence de points fournie.
     * La séquence est une chaîne de caractères où chaque caractère 'A' ou 'B'
     * représente un point gagné respectivement par le joueur A ou le joueur B.
     *
     * @param sequence La séquence de points (par exemple, "ABABAA").
     */
    public void playGame(String sequence) {
        // Parcourt chaque caractère de la séquence de jeu
        for (char c : sequence.toCharArray()) {
            // Envoie le caractère à la méthode playPoint de Match pour simuler un point
            String result = match.playPoint(c);

            // Affiche le résultat du point (par exemple, score actuel)
            System.out.println(result);

            // Si un joueur a gagné le jeu, on arrête la boucle
            if (result.contains("wins the game")) {
                break;
            }
        }
    }
}
