package gr.projects.project03;
import java.io.*;
import java.util.*;

public class CharacterFrequency {
    public static void main(String[] args) {
        // δημιουργεί πίνακα για την καταγραφή συχνοτήτων και χαρακτήρων
        int[] frequencies = new int[128];  // αποθηκεύει τις συχνότητες για κάθε χαρακτήρα

        // άνοιγμα αρχείου
        try (BufferedReader reader = new BufferedReader(new FileReader("project03.txt"))) {
            int c;
            while ((c = reader.read()) != -1) {
                // αγνοεί αν ο χαρακτήρας είναι whitespace η αλλαγή γραμμής
                if (Character.isWhitespace(c)) {
                    continue;
                }

                // ενημερώνει της συχνότητας του χαρακτήρα
                frequencies[c]++;
            }

            // δημιουργεί μιας λίστα χαρακτήρων για ταξινόμηση βάσει της συχνότητας
            List<Map.Entry<Character, Integer>> list = new ArrayList<>();
            for (int i = 0; i < 128; i++) {
                if (frequencies[i] > 0) {
                    list.add(new AbstractMap.SimpleEntry<>((char) i, frequencies[i]));
                }
            }

            // ταξινόμηση της λίστας κατά φθίνουσα συχνότητα
            list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

            // εκτυπώνει τα αποτελέσματα
            System.out.println("Character Frequency (Sorted by Frequency):");
            for (Map.Entry<Character, Integer> entry : list) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
}
