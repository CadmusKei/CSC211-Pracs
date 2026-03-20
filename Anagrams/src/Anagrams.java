// 4502894
// Maxwell Kei Farouk
// Anagrams

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;
import java.io.*;

public class Anagrams {
    public static void main(String[] args) throws IOException {

        ArrayList<String> words = readBook("joyce1922_ulysses-1.text");
        HashMap<String, ArrayList<String>> anagrams = generateAnagram(words);
        readAnagrams(anagrams);
        writeAnagrams(anagrams, "theAnagrams.tex");
    }

    // Read the file into an ArrayList
    private static ArrayList<String> readBook(String filename)  throws IOException {
        if (filename.isEmpty()) return new ArrayList<>();
        Path path = Path.of(filename);
        ArrayList<String> wordArray = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] words = line.split("\\s");
                for (String word : words) {
                    String cleaned = word.replaceAll("[^a-zA-Z']", "").toLowerCase();
                    wordArray.add(cleaned);
                }
            });
        }
        return (wordArray);
    }

    // Helper Method for Hashing
    private static String signature(String word) {
        if (word.isEmpty()) return "";
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }

    private static HashMap<String, ArrayList<String>> generateAnagram(ArrayList<String> words) {
        if (words.isEmpty()) return new HashMap<>();
        HashMap<String, ArrayList<String>> anagrams = new HashMap<>();
        for (String word : words) {
            String sig = signature(word);

            ArrayList<String> anagramWords = anagrams.get(sig);
            // AnagramWords is the Words related to this specific signature (because of for loop. )
            if (anagramWords == null) {
                anagramWords = new ArrayList<>();
                anagrams.put(sig, anagramWords);
            }

            if (!anagramWords.contains(word)) {
                anagramWords.add(word);
            }
        }
        return anagrams;
    }

    private static void readAnagrams(HashMap<String, ArrayList<String>> anagrams) {
        if (anagrams.isEmpty()) return;
        for (Map.Entry<String, ArrayList<String>> entry : anagrams.entrySet()) {
            System.out.println("Signature: " + entry.getKey() + ", Anagrams: " + entry.getValue());
        }
    }

    public static void writeAnagrams(HashMap<String, ArrayList<String>> anagrams, String outputFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            for (Map.Entry<String, ArrayList<String>> entry : anagrams.entrySet()) {
                ArrayList<String> words = entry.getValue();
                if (words.size() > 1) {
                    String wordList = String.join(", ", words);
                    writer.println("Sig: {" + entry.getKey() + "} - Anagram: " + wordList + " \\\\");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
