package domain;

import com.kata.domain.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchTest {
    @Test
    void testInitialState() {
        Match match = new Match("A", "B");
        assertEquals("Player A: 0 / Player B: 0", match.getScore());
    }

    @Test
    void testScoreProgression() {
        Match match = new Match("A", "B");

        match.playPoint('A');
        assertEquals("Player A: 15 / Player B: 0", match.getScore());

        match.playPoint('B');
        assertEquals("Player A: 15 / Player B: 15", match.getScore());
    }

    @Test
    void testDeuceScenario() {
        Match match = new Match("A", "B");

        match.playPoint('A'); // 15
        match.playPoint('A'); // 30
        match.playPoint('A'); // 40
        match.playPoint('B'); // 15
        match.playPoint('B'); // 30
        match.playPoint('B'); // 40

        assertEquals("Deuce", match.getScore());
    }

    @Test
    void testAdvantageScenario() {
        Match match = new Match("A", "B");

        match.playPoint('A'); // 15
        match.playPoint('A'); // 30
        match.playPoint('A'); // 40
        match.playPoint('B'); // 15
        match.playPoint('B'); // 30
        match.playPoint('B'); // 40
        match.playPoint('A'); // Advantage A

        assertTrue(match.getScore().contains("Advantage A"));
    }

    @Test
    void testWinScenario() {
        Match match = new Match("A", "B");

        match.playPoint('A'); // 15
        match.playPoint('A'); // 30
        match.playPoint('A'); // 40
        match.playPoint('A'); // Alice wins

        assertEquals("Player A wins the game", match.playPoint('A'));
    }
}
