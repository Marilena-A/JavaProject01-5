package gr.projects.project01;

import java.io.*;
import java.util.*;

public class NumberCombinations {
    public static void main(String[] args) throws IOException {

        // διαβάζει τους αριθμούς από "project01.txt"
        List<Integer> numbers = readNumbersFromFile("project01.txt");

        // ταξινομεί τους αριθμούς
        Collections.sort(numbers);

        // παράγει όλες τις εξάδες (combinations)
        List<List<Integer>> combinations = generateCombinations(numbers, 6);

        // δημιουργεί "outputproject01.txt"
        BufferedWriter writer = new BufferedWriter(new FileWriter("outputproject01.txt"));

        for (List<Integer> combination : combinations) {
            // φιλτράρει την εξάδα με βάσει τα κριτήρια
            if (isValidCombination(combination)) {
                // αν η εξάδα είναι σωστή, γράφει στο "outputproject01.txt"
                writer.write(combination.toString());
                writer.newLine();
            }
        }

        writer.close();
    }

    // διαβάζει τους αριθμούς από το αρχείο
    private static List<Integer> readNumbersFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Integer> numbers = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            for (String token : tokens) {
                numbers.add(Integer.parseInt(token));
            }
        }

        reader.close();
        return numbers;
    }

    // δημιουργεί όλους τους συνδυασμούς
    private static List<List<Integer>> generateCombinations(List<Integer> numbers, int size) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinationsRecursive(numbers, size, 0, new ArrayList<>(), result);
        return result;
    }

    // παραγωγή συνδυασμών
    private static void generateCombinationsRecursive(List<Integer> numbers, int size, int start,
                                                      List<Integer> current, List<List<Integer>> result) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < numbers.size(); i++) {
            current.add(numbers.get(i));
            generateCombinationsRecursive(numbers, size, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // ελέγχει αν η εξάδα τηρεί τα κριτήρια
    private static boolean isValidCombination(List<Integer> combination) {
        return !isEven(combination) && !isOdd(combination) && !isContiguous(combination) &&
                !isSameEnding(combination) && !isSameTen(combination);
    }

    // ελέγχει αν η εξάδα έχει πάνω από 4 άρτιους αριθμούς
    private static boolean isEven(List<Integer> combination) {
        long evenCount = combination.stream().filter(n -> n % 2 == 0).count();
        return evenCount > 4;
    }

    // ελέγχει αν η εξάδα έχει πάνω από 4 περιττούς αριθμούς
    private static boolean isOdd(List<Integer> combination) {
        long oddCount = combination.stream().filter(n -> n % 2 != 0).count();
        return oddCount > 4;
    }

    // ελέγχει αν η εξάδα έχει πάνω από 2 συνεχόμενους αριθμούς
    private static boolean isContiguous(List<Integer> combination) {
        for (int i = 1; i < combination.size(); i++) {
            if (combination.get(i) == combination.get(i - 1) + 1) {
                return true;
            }
        }
        return false;
    }

    // ελέγχει αν η εξάδα έχει πάνω από 3 αριθμούς με ίδια κατάληξη
    private static boolean isSameEnding(List<Integer> combination) {
        Map<Integer, Long> endingCount = new HashMap<>();
        for (int num : combination) {
            int lastDigit = num % 10;
            endingCount.put(lastDigit, endingCount.getOrDefault(lastDigit, 0L) + 1);
        }
        return endingCount.values().stream().anyMatch(count -> count > 3);
    }

    // ελέγχει αν η εξάδα έχει πάνω από 3 αριθμούς στην ίδια δεκάδα
    private static boolean isSameTen(List<Integer> combination) {
        Map<Integer, Long> tenCount = new HashMap<>();
        for (int num : combination) {
            int ten = (num - 1) / 10;
            tenCount.put(ten, tenCount.getOrDefault(ten, 0L) + 1);
        }
        return tenCount.values().stream().anyMatch(count -> count > 3);
    }
}

