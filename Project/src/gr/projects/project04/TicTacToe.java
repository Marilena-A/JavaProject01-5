package gr.projects.project04;

import java.util.Scanner;

public class TicTacToe {

    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static final char EMPTY = '-';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        boolean gameWon = false;
        boolean isPlayer1Turn = true;
        int moves = 0;

        while (moves < SIZE * SIZE && !gameWon) {
            printBoard();
            char currentPlayer = isPlayer1Turn ? 'X' : 'O';
            System.out.println("Player " + (isPlayer1Turn ? "1" : "2") + "'s turn (" + currentPlayer + ")");

            int row, col;
            while (true) {
                System.out.print("Enter row (0, 1, 2): ");
                row = scanner.nextInt();
                System.out.print("Enter column (0, 1, 2): ");
                col = scanner.nextInt();

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            moves++;
            gameWon = checkWin(row, col, currentPlayer);

            if (!gameWon) {
                isPlayer1Turn = !isPlayer1Turn;
            }
        }

        printBoard();
        if (gameWon) {
            System.out.println("Player " + (isPlayer1Turn ? "1" : "2") + " wins!");
        } else {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }

    private static void initializeBoard() {
        // αρχικοποίηση πίνακα γεμίζοντάς τον με το σύμβολο EMPTY
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        // εκτυπώνει την τρέχουσα κατάσταση του πίνακα
        System.out.println("\nCurrent board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        // ελέγχει αν η κίνηση είναι σωστή (εντός ορίων και σε άδειο κελί)
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private static boolean checkWin(int row, int col, char player) {
        // έλεγχος για νίκη στη γραμμή
        boolean win = true;
        for (int j = 0; j < SIZE; j++) {
            if (board[row][j] != player) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // έλεγχος για νίκη στη στήλη
        win = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] != player) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // έλεγχος για νίκη στην κύρια διαγώνιο
        if (row == col) {
            win = true;
            for (int i = 0; i < SIZE; i++) {
                if (board[i][i] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // έλεγχος για νίκη στη δευτερεύουσα διαγώνιο
        if (row + col == SIZE - 1) {
            win = true;
            for (int i = 0; i < SIZE; i++) {
                if (board[i][SIZE - 1 - i] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        return false;
    }
}
