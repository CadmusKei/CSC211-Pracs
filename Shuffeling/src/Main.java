// 4502894
// Maxwell Kei Farouk
// 11/02/2026

import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

public class Main{
    public static void main(String[] args){
        System.out.println("Shuffeling");
        int N = 10;
        int[] shuffled = new int[N];

        System.out.println("\nSlow Shuffle: ");
        int[] newShuffled = slowShuffle(shuffled);
        System.out.println(Arrays.toString(newShuffled));

        System.out.println("\nBiased Shuffle: ");
        System.out.println(Arrays.toString(biasedShuffle(10)));

        System.out.println("\nUnbiased Shuffle: ");
        System.out.println(Arrays.toString(unbiasedShuffle(N)));

        System.out.println("\nBias proof, bias: True");
        checkBias(true);
        System.out.println("\nBias proof, bias: False");
        checkBias(false);

    }

        static int[] slowShuffle(int[] shuffled)
        {

            if (shuffled == null || shuffled.length == 0) return new int[0];

            int N = shuffled.length;
            int i = 0;
            Random random = new Random();
            boolean[] isNotPresent = new boolean[shuffled.length ];
            Arrays.fill(isNotPresent, true);
            int r = 1 + random.nextInt(N);

            while (i < N-1)
            {
                if (isNotPresent[r - 1])
                {
                    shuffled[i] = r;
                    isNotPresent[r - 1] = false;
                    i += 1;
                }
                else {
                    r = 1 + random.nextInt(N);
                }
            }

            for (int j = 0; j < N; j++) {
                if (isNotPresent[j]) {
                    shuffled[N - 1] = j + 1;
                    break;
                }
            }

            return shuffled;
        }

        static int[] biasedShuffle(int N){

            if (N <= 0) return new int[0];

            int[] shuffled = new int[N];
            Random random = new Random();

            for (int i = 0; i < N; i++) {
                shuffled[i] = i + 1;
            }

            for (int i = 0; i < N - 1; i++) {
                int r = random.nextInt(N);
                int temp = shuffled[i];
                shuffled[i] = shuffled[r];
                shuffled[r] = temp;
            }

            return shuffled;
        }

        static int[] unbiasedShuffle(int N){

            if (N <= 0) return new int[0];

            int[] shuffled = new int[N];
            Random random = new Random();

            for (int i = 0; i < N; i++) {
                shuffled[i] = i + 1;
            }

            for (int i = 0; i < N - 1; i++)
            {
                int r = random.nextInt( N - i);
                int temp = shuffled[i];
                shuffled[i] = shuffled[r];
                shuffled[r] = temp;
            }
            return shuffled;
        }

        static void checkBias(boolean isBiased){

            int N = 3;
            HashMap<String, Integer> freqMap = new HashMap<>();
            int[] shuffledarr = new int[N];

            for (int i = 0; i <= 60000; i++)
            {
                if (isBiased) {
                    shuffledarr = biasedShuffle(N);
                }
                else
                {
                    shuffledarr = biasedShuffle(N);
                }

                StringBuilder str = new StringBuilder();

                for (int value : shuffledarr)
                {
                    str.append(value);
                }

                freqMap.put(str.toString(), freqMap.getOrDefault(str.toString(), 0) + 1);
            }

            for (String str : freqMap.keySet()) {
                System.out.println(str + " " + freqMap.get(str));
            }
        }
    }
