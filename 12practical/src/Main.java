// 4502894
// Maxwell Kei Farouk
// Wednesday E1/E2

import java.util.Random;

// Main Class
public class Main{

    // count variables
    static int countOn3 = 0;
    static int countOn2A = 0;
    static int countOn2B = 0;
    static int countOn = 0;

    // Reset the counters
    public static void resetCounters() {
        countOn3 = 0;
        countOn2A = 0;
        countOn2B = 0;
        countOn = 0;
    }

    // Main executable method
    public static void main(String[] args)
    {
        int[] sizes = {10, 100, 1000, 3000};

        System.out.printf("%-8s %-15s %-15s %-15s %-15s%n",
                "n", "O(n^3)", "O(n^2)A", "O(n^2)B", "O(n)");

        for (int n : sizes) {

            int[] X = createArray(n);
            resetCounters();

            mcsOn3(X);
            mcsOn2A(X);
            mcsOn2B(X);
            mcsOn(X);

            System.out.printf("%-8d %-15d %-15d %-15d %-15d%n",
                    n, countOn3, countOn2A, countOn2B, countOn);
        }
    }

    // Create the methods
    public static int[] createArray(int n)
    {
        int[] arr = new int[n];
        Random randGen = new Random();

        for (int i = 0; i < n; i++)
        {
             arr[i] = (int)(randGen.nextInt(1, n)* Math.pow((-1), randGen.nextInt(2, 4)));
        }

        return arr;
    }

    // O(n^3) Sort
    public static int mcsOn3(int[] arr)
    {
        int N = arr.length;
        int maxSoFar = 0;

        for (int low = 0; low < N; low++)
        {
            for (int high = low; high < N; high++)
            {
                int sum = 0;
                for (int r = low; r <= high; r++) {
                    sum += arr[r];
                    countOn3++;
                }
                if (sum > maxSoFar)
                {
                    maxSoFar = sum;
                }
            }
        }
        if (maxSoFar < 0) return 0;
        return maxSoFar;
    }

    // O(n^2)(A) Sort
    public static int mcsOn2A(int[] arr) {
        int N = arr.length;
        int maxSoFar = 0;

        for (int low = 0; low < N; low++) {
            int sum = 0;
            for (int r = low; r < N; r++) {
                sum += arr[r];
                countOn2A++;
                if (sum > maxSoFar) {
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;
    }

    // O(n^2)(B) Sort
    public static int mcsOn2B(int[] arr) {
        int N = arr.length;
        int[] sumTo = new int[N + 1];
        sumTo[0] = 0;

        for (int i = 1; i <= N; i++) {
            sumTo[i] = sumTo[i - 1] + arr[i - 1];
        }
        int maxSoFar = 0;
        for (int low = 0; low < N; low++) {
            for (int high = low; high < N; high++) {
                int sum = sumTo[high + 1] - sumTo[low];
                countOn2B++;
                if (sum > maxSoFar) {
                    maxSoFar = sum;
                }
            }
        }
        return maxSoFar;
    }

    // O(n) Sort
    public static int mcsOn(int[] arr)
    {
        int N = arr.length;
        double maxSoFar = 0.0;
        double maxToHere = 0.0;

        for (int j : arr) {
            if (j + maxToHere > 0) {
                maxToHere += j;
            } else {
                maxToHere = 0;
            }
            countOn++;
            if (maxToHere > maxSoFar) maxSoFar = maxToHere;
        }
        if (maxSoFar == 0) return 0;
        else return (int)maxSoFar;
    }

}