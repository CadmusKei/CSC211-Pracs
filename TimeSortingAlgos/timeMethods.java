// 4502894
// Wednesday Practical 3
// CLC Lab

import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class timeMethods{
    public static int N = 30000;
    
    private static class Node {
        int key;
        String value;

        Node(int key, String value)
        {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String args[]) throws IOException {

        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");
        Random randGen = new Random();
        
        long start, finish;
        double linearRunTime = 0, linearRunTime2 = 0, linearTime;
        double binaryRunTime = 0, binaryRunTime2 = 0, binaryTime;
        int n = N;
        int repetition, repetitions = N;
        int index = 0;
        Node[] nodeArr = createNodeArray("ulysses.numbered");

        // Loop for repetitions
        for (repetition = 0; repetition < repetitions; repetition++) {
            
            int randKey = randGen.nextInt(0, 32654);

            // Linear Time lookup timer
            start = System.currentTimeMillis();
            index = linearSearch(nodeArr, randKey);
            finish = System.currentTimeMillis();

            // Update linear Time Stats
            linearTime = (double) (finish - start);
            linearRunTime += linearTime;
            linearRunTime2 += (linearTime * linearTime);

            // Linear Time lookup timer
            start = System.currentTimeMillis();
            index = binarySearch(nodeArr, randKey);
            finish = System.currentTimeMillis();

            // Update linear Time Stats
            binaryTime = (double) (finish - start);
            binaryRunTime += binaryTime;
            binaryRunTime2 += (binaryTime * binaryTime);

        }

        double linearAveRuntime = linearRunTime / repetitions;
        double linearStdDeviation = Math.sqrt(linearRunTime2 - repetitions * linearAveRuntime * linearAveRuntime) / (repetitions - 1);

        double binaryAveRuntime = binaryRunTime / repetitions;
        double binaryStdDeviation = Math.sqrt(binaryRunTime2 - repetitions * binaryAveRuntime * binaryAveRuntime) / (repetitions - 1);


        System.out.printf("\n\n\fStatistics\n");
        System.out.printf("\n\n\fLinear Search\n");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + linearRunTime / 1000 + "s.");
        System.out.println("Total time\u00b2  =           " + linearRunTime2);
        System.out.println("Average time =           " + fiveD.format(linearAveRuntime / 1000)
                + "s. " + '\u00B1' + " " + fourD.format(linearStdDeviation) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(linearStdDeviation));
        System.out.println("n            =           " + n);
        System.out.println("Average time / run =     " + fiveD.format(linearAveRuntime / n * 1000) + '\u00B5' + "s. ");
        System.out.println("Repetitions  =             " + repetitions);
        System.out.println("________________________________________________");
        System.out.println();
        System.out.printf("\n\n\fBinary Search\n");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + binaryRunTime / 1000 + "s.");
        System.out.println("Total time\u00b2  =           " + binaryRunTime2);
        System.out.println("Average time =           " + fiveD.format(binaryAveRuntime / 1000)
                + "s. " + '\u00B1' + " " + fourD.format(binaryStdDeviation) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(binaryStdDeviation));
        System.out.println("n            =           " + n);
        System.out.println("Average time / run =     " + fiveD.format(binaryAveRuntime / n * 1000) + '\u00B5' + "s. ");
        System.out.println("Repetitions  =             " + repetitions);
        System.out.println("________________________________________________");
        System.out.println();
        System.out.println();
    }
        
    // Linear Search for Key
    public static int linearSearch(Node[] nodeArr, int key) {
        for (int i = 0; i < nodeArr.length; i++) {
            if (nodeArr[i].key == key)
                return i;
        }
        return -1;
    }

    // Binary Search for Key
    public static int binarySearch(Node[] nodeArr, int key)
    {
        int left = 0;
        int right = nodeArr.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (key == nodeArr[middle].key)
                return middle;
            else if (key > nodeArr[middle].key)
                left = middle + 1;
            else if (key < nodeArr[middle].key)
                right = middle - 1;
        }
        return -1;
    }
        
    // Read file and create a Node Array
    public static Node[] createNodeArray(String filename) throws IOException {
        Path path = Path.of(filename);
        List<Node> list = new ArrayList<>();
        
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] parts = line.split(" ", 2);
                int key = Integer.parseInt(parts[0]);
                String value = parts[1];
                
                list.add(new Node(key, value));
            });
        }
        
        return list.toArray(new Node[0]);
    }   

}

// The final statement of this code.} }