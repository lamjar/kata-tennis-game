package application;

import com.kata.application.MatchService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchServiceTest {
    @Test
    void testPlayGame() {
        // Rediriger la sortie standard pour capturer l'affichage
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        MatchService service = new MatchService("A", "B");
        service.playGame("ABABAA");

        String[] outputLines = outContent.toString().split("\n");
        assertEquals("Player A: 15 / Player B: 0", outputLines[0].trim());
        assertEquals("Player A: 15 / Player B: 15", outputLines[1].trim());
        assertEquals("Player A: 30 / Player B: 15", outputLines[2].trim());
        assertEquals("Player A: 30 / Player B: 30", outputLines[3].trim());
        assertEquals("Player A: 40 / Player B: 30", outputLines[4].trim());
        assertEquals("Player A wins the game", outputLines[5].trim());
    }

    /**
     * Méthode utilitaire pour capturer les sorties de la console.
     */
    private String captureOutput(Runnable action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return outputStream.toString();
    }

    @Test
    public void testSimpleWinPlayerA() {
        MatchService matchService = new MatchService("Player A", "Player B");
        String output = captureOutput(() -> matchService.playGame("AAAA"));

        assertTrue(output.contains("Player A: 15 / Player B: 0"));
        assertTrue(output.contains("Player A: 30 / Player B: 0"));
        assertTrue(output.contains("Player A: 40 / Player B: 0"));
        assertTrue(output.contains("Player A wins the game"));
    }

    @Test
    public void testSimpleWinPlayerB() {
        MatchService matchService = new MatchService("Player A", "Player B");
        String output = captureOutput(() -> matchService.playGame("BBBB"));

        assertTrue(output.contains("Player A: 0 / Player B: 15"));
        assertTrue(output.contains("Player A: 0 / Player B: 30"));
        assertTrue(output.contains("Player A: 0 / Player B: 40"));
        assertTrue(output.contains("Player B wins the game"));
    }

    @Test
    public void testDeuceAndWinPlayerA() {
        MatchService matchService = new MatchService("A", "B");
        String output = captureOutput(() -> matchService.playGame("ABABABA"));

        assertTrue(output.contains("Player A: 15 / Player B: 0"));
        assertTrue(output.contains("Player A: 15 / Player B: 15"));
        assertTrue(output.contains("Player A: 30 / Player B: 15"));
        assertTrue(output.contains("Deuce"));
        assertTrue(output.contains("Advantage A"));
        assertTrue(output.contains("Player A wins the game"));
    }

    @Test
    public void testDeuceAndWinPlayerB() {
        MatchService matchService = new MatchService("A", "B");
        String output = captureOutput(() -> matchService.playGame("ABABABAB"));

        assertTrue(output.contains("Player A: 15 / Player B: 0"));
        assertTrue(output.contains("Player A: 15 / Player B: 15"));
        assertTrue(output.contains("Player A: 30 / Player B: 30"));
        assertTrue(output.contains("Deuce"));
        assertTrue(output.contains("Advantage A"));
        assertTrue(output.contains("Player A wins the game"));
    }

    @Test
    public void testMultipleDeuceAndWinPlayerB() {
        MatchService matchService = new MatchService("A", "B");
        String output = captureOutput(() -> matchService.playGame("ABABABAAB"));

        assertTrue(output.contains("Player A: 15 / Player B: 0"));
        assertTrue(output.contains("Player A: 15 / Player B: 15"));
        assertTrue(output.contains("Player A: 40 / Player B: 30"));
        assertTrue(output.contains("Deuce"));
        assertTrue(output.contains("Advantage A"));
        assertTrue(output.contains("Player A wins the game"));
    }

    @Test
    public void testFrequentScoreChangesWithoutDeuce() {
        MatchService matchService = new MatchService("Player A", "Player B");
        String output = captureOutput(() -> matchService.playGame("AABBAB"));

        assertTrue(output.contains("Player A: 15 / Player B: 0"));
        assertTrue(output.contains("Player A: 30 / Player B: 0"));
        assertTrue(output.contains("Player A: 30 / Player B: 15"));
        assertTrue(output.contains("Player A: 40 / Player B: 30"));
    }
}
