package com.kata.domain;

public class Match {
    // Instances des joueurs A et B
    private final Player playerA;
    private final Player playerB;

    // Indicateur pour savoir si le match est en "deuce" (égalité)
    private boolean isDeuce;

    // Le joueur qui a l'avantage, si applicable
    private Player advantagePlayer;

    /**
     * Constructeur qui initialise le match avec les noms des joueurs.
     * Crée les objets Player pour chaque joueur et initialise les autres attributs.
     *
     * @param playerAName Nom du joueur A
     * @param playerBName Nom du joueur B
     */
    public Match(String playerAName, String playerBName) {
        // Création des joueurs avec leurs noms respectifs
        this.playerA = new Player(playerAName);
        this.playerB = new Player(playerBName);

        // Le match commence sans avantage et sans égalité ("deuce")
        this.isDeuce = false;
        this.advantagePlayer = null;
    }

    /**
     * Gère le déroulement d'un point. Le caractère 'A' ou 'B' représente le joueur gagnant du point.
     * Cette méthode met à jour les scores des joueurs et vérifie si quelqu'un a gagné.
     *
     * @param winner Le caractère représentant le joueur gagnant ('A' ou 'B').
     * @return Un message indiquant le score actuel ou si un joueur a gagné.
     */
    public String playPoint(char winner) {
        // Détermine quel joueur a gagné ce point
        Player winnerPlayer = (winner == 'A') ? playerA : playerB;
        // Le joueur adverse est l'opposant du gagnant
        Player opponentPlayer = (winner == 'A') ? playerB : playerA;

        // Si le match est en "deuce", gérer la logique d'avantage
        if (isDeuce) {
            handleDeuce(winnerPlayer, opponentPlayer);
        } else {
            // Si le joueur a gagné, il remporte la partie
            if (winnerPlayer.hasWonAgainst(opponentPlayer)) {
                return String.format("Player %s wins the game", winnerPlayer.getName());
            }
            if (opponentPlayer.hasWonAgainst(opponentPlayer)) {
                return String.format("Player %s wins the game", opponentPlayer.getName());
            }
            // Sinon, on incrémente le score du joueur gagnant
            winnerPlayer.increaseScore();
            // Vérifie si le score est passé à "deuce"
            checkDeuce();
        }

        // Retourne le score actuel
        return getScore();
    }

    /**
     * Gère la situation de "deuce" (égalité à 40-40).
     * Si un joueur a l'avantage, il est marqué. Si un joueur gagne, l'avantage est annulé.
     * Si les deux joueurs sont à égalité, la situation revient à "deuce".
     *
     * @param winnerPlayer   Le joueur qui a gagné le point dans une situation de "deuce".
     * @param opponentPlayer Le joueur adverse.
     */
    private void handleDeuce(Player winnerPlayer, Player opponentPlayer) {
        // Si aucun joueur n'a d'avantage, on attribue l'avantage au joueur qui a gagné le point
        if (advantagePlayer == null) {
            advantagePlayer = winnerPlayer;
        } else if (advantagePlayer == winnerPlayer) {
            // Si le joueur avec l'avantage gagne à nouveau, il remporte le jeu
//            advantagePlayer = null; // L'avantage disparaît, le joueur gagne
            throw new GameWonException(winnerPlayer.getName());
        } else {
            // Si le joueur sans avantage gagne, on revient à "deuce"
            isDeuce = true;
            advantagePlayer = null;
        }
    }

    /**
     * Vérifie si le match est à "deuce" (égalité parfaite entre les deux joueurs).
     * Si les deux joueurs ont 40 points, la situation passe à "deuce".
     */
    private void checkDeuce() {
        // Si les deux joueurs ont 40 points, le match est en "deuce"
        if (playerA.getScore() == 40 && playerB.getScore() == 40) {
            isDeuce = true;
        }
    }

    /**
     * Retourne le score actuel du match sous forme de chaîne de caractères.
     * Si un joueur a l'avantage, l'avantage est affiché.
     * Si le match est à "deuce", cela est affiché.
     * Sinon, affiche les scores des deux joueurs.
     *
     * @return Le score actuel du match sous forme de chaîne.
     */
    public String getScore() {
        // Si un joueur a l'avantage, on l'affiche
        if (advantagePlayer != null) {
            return String.format("Advantage %s\nPlayer %s wins the game ", advantagePlayer.getName(), advantagePlayer.getName());
        } else if (isDeuce) {
            // Si le match est à "deuce", on l'affiche
            return "Deuce";
        } else {
            // Sinon, on affiche les scores actuels des joueurs
            return "Player A: " + playerA.getScore() + " / Player B: " + playerB.getScore();
        }
    }

    /**
     * Retourne l'objet Player représentant le joueur A.
     *
     * @return L'objet Player pour le joueur A.
     */
    public Player getPlayerA() {
        return playerA;
    }

    /**
     * Retourne l'objet Player représentant le joueur B.
     *
     * @return L'objet Player pour le joueur B.
     */
    public Player getPlayerB() {
        return playerB;
    }

    // Exception levée lorsqu'un joueur remporte la partie
    public static class GameWonException extends RuntimeException {
        public GameWonException(String winnerName) {
            super(String.format("Player %s wins the game", winnerName));
        }
    }
}
