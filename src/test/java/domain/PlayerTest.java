package domain;

import com.kata.domain.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testInitialScoreIsZero() {
        Player player = new Player("Lamjed");
        assertEquals(0, player.getScore());
    }

    @Test
    void testIncreaseScore() {
        Player player = new Player("Lamjed");

        player.increaseScore();
        assertEquals(15, player.getScore());

        player.increaseScore();
        assertEquals(30, player.getScore());

        player.increaseScore();
        assertEquals(40, player.getScore());
    }

    @Test
    void testResetScore() {
        Player player = new Player("Lamjed");
        player.increaseScore();
        player.resetScore();
        assertEquals(0, player.getScore());
    }

    @Test
    void testHasWonAgainst() {
        Player playerA = new Player("Lamjed");
        Player playerB = new Player("Lamjar");

        playerA.increaseScore();
        playerA.increaseScore();
        playerA.increaseScore();

        assertTrue(playerA.hasWonAgainst(playerB));
        assertFalse(playerB.hasWonAgainst(playerA));
    }
}
