// 4502894
// Maxwell Kei Farouk
// Wednesday E1/E2

import java.util.Arrays;
import java.util.Random;

public class Main{

    static int countOn3 = 0;
    static int countOn2A = 0;
    static int countOn2B = 0;
    static int countOn = 0;

    public static void main(String[] args)
    {

        int[] arrSizes = {10, 100, 1000};

        int[] arr = createArray(20);
        System.out.println(Arrays.toString(arr));
        System.out.println("Kadanes: " + mscOn(arr) + "steps: " + countOn);
        System.out.println("O(n^3): " + mcsOn3(arr) + "steps: " + countOn3);
        System.out.println("O(n^2)(A): " + mcsOn2A(arr) + "steps: " + countOn2A);
        System.out.println("O(n^2)(B): " + mcsOn2B(arr) + "steps: " + countOn2B);

    }

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
        return maxSoFar;
    }

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


    public static int mscOn(int[] arr)
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