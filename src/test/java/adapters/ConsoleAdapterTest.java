package adapters;

import com.kata.adapters.ConsoleAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleAdapterTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        // Redirige les flux pour les tests
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
        // Réinitialise les flux après chaque test
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    void testGameFlow() {
        ConsoleAdapter adapter = new ConsoleAdapter();
        // Fournir une séquence d'entrée
        String input = "ABABAA\n";
        provideInput(input);

        // Appeler la méthode main pour simuler un flux complet
        adapter.start();

        // Récupérer et analyser la sortie
        String[] outputLines = getOutput().split("\n");

        // Vérifie les étapes clés dans l'affichage
        assertTrue(outputLines[1].contains("Welcome to Tennis Game")); // Bannière
        assertTrue(outputLines[5].contains("Enter the game sequence")); // Demande de séquence
        assertTrue(outputLines[6].contains("Player A: 15 / Player B: 0")); // Premier point
        assertTrue(outputLines[11].contains("Player A wins the game")); // Résultat final
    }
}
