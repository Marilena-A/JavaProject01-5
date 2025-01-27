package gr.projects.project5;

import java.util.Scanner;

public class TheaterManagement {
    private boolean[][] seats = new boolean[30][12];  // δημιουργεί πίνακα για το θέατρο 30 σειρές, 12 στήλες

    // κατασκευάζει και αρχικοποιεί όλες τις θέσεις ως μη κρατημένες
    public TheaterManagement() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 12; j++) {
                seats[i][j] = false;  // όλες οι θέσεις αρχίζουν μη κρατημένες
            }
        }
    }

    // κάνει κράτηση
    public void book(char column, int row) {
        // μετατρέπει τη στήλη (char) σε δείκτη πίνακα (0-11)
        int col = column - 'A';  // οι στήλες είναι από A έως L

        if (col < 0 || col >= 12 || row < 1 || row > 30) {
            System.out.println("Invalid seat position!");
            return;
        }

        // ελέγχει αν η θέση είναι ήδη κρατημένη
        if (seats[row - 1][col]) {
            System.out.println("The seat is already booked!");
        } else {
            seats[row - 1][col] = true;  // κρατάει τη θέση
            System.out.println("Seat " + column + row + " booked successfully!");
        }
    }

    // ακυρώνει την κράτηση
    public void cancel(char column, int row) {
        // μετατρέπει τη στήλη (char) σε δείκτη πίνακα (0-11)
        int col = column - 'A';  // οι στήλες είναι από A έως L

        if (col < 0 || col >= 12 || row < 1 || row > 30) {
            System.out.println("Invalid seat position!");
            return;
        }

        // ελέγχει αν η θέση είναι κρατημένη
        if (!seats[row - 1][col]) {
            System.out.println("The seat is not booked!");
        } else {
            seats[row - 1][col] = false;  // ακυρώνει την κράτηση
            System.out.println("Seat " + column + row + " canceled successfully!");
        }
    }

    // εμφανίζει το θέατρο με αριθμούς και γράμματα
    public void displaySeats() {
        System.out.print("    "); // κενό για την κεφαλίδα των στηλών
        for (char col = 'A'; col <= 'L'; col++) {
            System.out.print(col + " ");  // εμφανίζει τις στήλες A-L
        }
        System.out.println();

        // εμφανίζει τις θέσεις του θεάτρου με αριθμούς σειρών
        for (int i = 0; i < 30; i++) {
            System.out.print(String.format("%2d ", i + 1));  // εμφανίζει τον αριθμό της σειράς (1-30)

            // εμφανίζει αν η θέση είναι κρατημένη X ή διαθέσιμη O
            for (int j = 0; j < 12; j++) {
                System.out.print(seats[i][j] ? "X " : "O ");  // X για κρατημένη θέση, O για διαθέσιμη
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TheaterManagement theatre = new TheaterManagement();  // δημιουργεί ενα αντικείμενο για το θέατρο

        while (true) {
            // εμφανίζει το θέατρο
            theatre.displaySeats();

            // ζητάμε από τον χρήστη να επιλέξει ενέργεια
            System.out.println("\nSelect action: ");
            System.out.println("1. Book a seat");
            System.out.println("2. Cancel a booking");
            System.out.println("3. Exit");

            int action = scanner.nextInt();
            if (action == 3) {
                break;  // κλείνει το πρόγραμμα
            }

            System.out.print("Enter column (A-L): ");
            char column = scanner.next().charAt(0);  // διαβάζει τη στήλη
            System.out.print("Enter row (1-30): ");
            int row = scanner.nextInt();  // διαβάζει τη σειρά

            // ανάλογα με την επιλογή του χρήστη, καλεί την αντίστοιχη μέθοδο
            if (action == 1) {
                theatre.book(column, row);  // κράτηση θέσης
            } else if (action == 2) {
                theatre.cancel(column, row);  // ακύρωση κράτησης
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();  // κλείνει το Scanner
    }
}
