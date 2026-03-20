// Maxwell Kei Farouk
// 4502894
// clc lab
// 18 March 2026

// Please allow time for my code to run the 30 reps. It takes about 5-10 seconds to finish the print.

import java.nio.file.Path;
import java.text.DecimalFormat;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Stream;

public class tryHeapSort {

    // Read the file into an ArrayList
    private static String[] readBook(String filename)  throws IOException {
        if (filename.isEmpty()) return new String[0];
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
        return (wordArray.toArray(new String[0]));
    }

    private static void siftDown(String[] arr, int i, int n) {
        int largest = i;
        // Since this is a zero index array
        // (The tut and lecture worked with a 1 index array.)
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) > 0) largest = left;
        if (right < n && arr[right].compareTo(arr[largest]) > 0) largest = right;

        if (largest != i) {
            String temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            // Recursively sift down each step
            siftDown(arr, largest, n);
        }
    }

    private static void siftUp(String[] array, int i) {
        // repeat until root
        while (i > 0) {
            int parent = (i - 1) / 2;

            // If sorted, leave
            if (array[i].compareTo(array[parent]) <= 0) {
                break;
            }

            // Otherwise, use a simple bubblesort
            String temporary = array[i];
            array[i] = array[parent];
            array[parent] = temporary;

            i = parent;
        }
    }

    private static void buildBottomUp(String[] words){
        int n = words.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(words, i, n);
        }
    }

    private static void buildTopDown(String[] words) {

        for (int i = 1; i < words.length; i++) {
            siftUp(words, i);
        }

    }

    // The actual Heap Sort method
    private static void heapSort(String[] arr) {
        int n = arr.length;
        for (int i = n - 1; i > 0; i--) {
            // bubblr sort and Sift
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            siftDown(arr, 0, i);
        }
    }

    private static void printTimes(String label, DecimalFormat four_D, DecimalFormat five_D, double run_time, double run_time_2, int n, int repetitions) {

        double ave_run_time = run_time / repetitions;
        double variance = (run_time_2 - repetitions * ave_run_time * ave_run_time) / (repetitions - 1);
        double std_deviation = Math.sqrt(Math.max(0.0, variance));

        System.out.println("\n________________________________________________");
        System.out.println("Statistics (" + label + ")");
        System.out.println("________________________________________________");
        System.out.println("Total Time = " + run_time / 1000 + " s.");
        System.out.println("Average Time = " + five_D.format(ave_run_time / 1000) + " s. ± " + four_D.format(std_deviation) + " ms.");
        System.out.println("Standard Deviation = " + four_D.format(std_deviation) + " ms.");
        System.out.println("n = " + n);
        System.out.println("Average Time / Run = " + five_D.format(ave_run_time / n * 1000) + " µs.");
        System.out.println("Repetitions = " + repetitions);
        System.out.println("________________________________________________");
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        String[] words = readBook("joyce1922_ulysses-1.text");

        // Test for small array first

        String[] smallWords = Arrays.copyOf(words, 20);

        String[] smallBottom = smallWords.clone();
        buildBottomUp(smallBottom);
        heapSort(smallBottom);

        String[] smallTop = smallWords.clone();
        buildTopDown(smallTop);
        heapSort(smallTop);

        System.out.println("Bottom-Up Sorted (Small Array):");
        System.out.println(Arrays.toString(smallBottom));
        System.out.println();

        System.out.println("Top-Down Sorted (Small Array):");
        System.out.println(Arrays.toString(smallTop));
        System.out.println();


        long start, finish;
        double bottomUpRunTime = 0, bottomUpRunTime2 = 0, bottomUpTime;
        double topDownRunTime = 0, topDownRunTime2 = 0, topDownTime;

        int repetitions = 30;

        for (int rep = 0; rep < repetitions; rep++) {

            // Bottom-Up Version
            String[] bottomUpWords = words.clone();
            start = System.currentTimeMillis();
            buildBottomUp(bottomUpWords);
            heapSort(bottomUpWords);
            finish = System.currentTimeMillis();

            bottomUpTime = (double) (finish - start);
            bottomUpRunTime += bottomUpTime;
            bottomUpRunTime2 += bottomUpTime * bottomUpTime;


            // Top-Down Version
            String[] topDownWords = words.clone();
            start = System.currentTimeMillis();
            buildTopDown(topDownWords);
            heapSort(topDownWords);
            finish = System.currentTimeMillis();

            topDownTime = (double) (finish - start);
            topDownRunTime += topDownTime;
            topDownRunTime2 += topDownTime * topDownTime;
        }

        // Print for whole book

        printTimes("Bottom-Up Heap Sort", fourD, fiveD,
                bottomUpRunTime, bottomUpRunTime2,
                words.length, repetitions);

        printTimes("Top-Down Heap Sort", fourD, fiveD,
                topDownRunTime, topDownRunTime2,
                words.length, repetitions);
    }

}
