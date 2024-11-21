package com.kata.domain;

public class Player {
    // Nom du joueur
    private final String name;
    // Score du joueur
    private int score;

    /**
     * Constructeur pour initialiser un joueur avec son nom.
     * Le score est initialisé à 0 (début du jeu).
     *
     * @param name Le nom du joueur.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0; // Score initial à 0
    }

    /**
     * Retourne le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne le score actuel du joueur.
     *
     * @return Le score du joueur.
     */
    public int getScore() {
        return score;
    }

    /**
     * Incrémente le score du joueur en fonction du nombre de points gagnés.
     * La séquence des scores est la suivante : 0 -> 15 -> 30 -> 40.
     */
    public void increaseScore() {
        // Si le score est 0, on passe à 15
        if (score == 0) {
            score = 15;
        }
        // Si le score est 15, on passe à 30
        else if (score == 15) {
            score = 30;
        }
        // Si le score est 30, on passe à 40
        else if (score == 30) {
            score = 40;
        }
    }

    /**
     * Vérifie si ce joueur a gagné contre l'adversaire.
     * Un joueur gagne s'il a 40 points et que l'adversaire a un score inférieur à 40.
     *
     * @param opponent L'adversaire du joueur.
     * @return true si le joueur a gagné, false sinon.
     */
    public boolean hasWonAgainst(Player opponent) {
        // Ce joueur a gagné si son score est 40 et que l'adversaire a un score inférieur à 40
        return this.score == 40 && opponent.getScore() < 40;
    }

    /**
     * Réinitialise le score du joueur à 0.
     * Cette méthode peut être utilisée pour recommencer un match.
     */
    public void resetScore() {
        this.score = 0;
    }
}
