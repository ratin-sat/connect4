import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final int NUM_ROWS = 6;
        final int NUM_COLS = 7;
        final int MAX_TURNS = NUM_ROWS * NUM_COLS;
        final String P1 = "Player 1";
        final String P2 = "Player 2";
        final String P1_COLOR = "RED";
        final String P2_COLOR = "GREEN";
        final char P1_DISC = 'R';
        final char P2_DISC = 'G';

        Grid grid = new Grid(NUM_ROWS, NUM_COLS);
        Player p1 = new Player(P1, P1_COLOR, P1_DISC);
        Player p2 = new Player(P2, P2_COLOR, P2_DISC);

        Player currentPlayer = p1;
        int countTurns = 0;
        boolean hasWinner = false;

        System.out.println("Connect4 by Satit Ratinukulkit");
        grid.draw();

        // Game loop
        while (true) {
            int colInsert = -1;
            System.out.print(String.format("%s [%s] - choose column (1-%d):",
                    currentPlayer.getName(),
                    currentPlayer.getColor(),
                    NUM_COLS));
            try {
                colInsert = in.nextInt();
            }
            catch (InputMismatchException inputMismatchException) {
                in.nextLine();
                System.out.println(String.format("Invalid input. %s, please try again", currentPlayer.getName()));
                continue;
            }

            if (!grid.tryInsert(currentPlayer.getDisc(), colInsert)) {
                System.out.println(String.format("%s, please try again", currentPlayer.getName()));
                continue;
            }

            countTurns++;
            grid.draw();

            if (grid.isConnect4(currentPlayer.getDisc(), colInsert)) {
                hasWinner = true;
                break;
            }

            if (countTurns >= MAX_TURNS) {
                break;
            }

            currentPlayer = currentPlayer == p1 ? p2 : p1;
        }

        if (hasWinner) {
            System.out.println(String.format("%s [%s] wins", currentPlayer.getName(), currentPlayer.getColor()));
        }
        else {
            System.out.println("Tie");
        }
    }
}