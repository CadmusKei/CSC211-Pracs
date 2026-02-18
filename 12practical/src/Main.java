// 4502894
// Maxwell Kei Farouk
// Wednesday E1/E2

import java.util.Arrays;
import java.util.Random;

public class Main{
    public static void main(String[] args)
    {
        int[] arr = createArray(5);
        System.out.println(Arrays.toString(arr));
        System.out.println(mscOn(arr));
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
            if (maxToHere > maxSoFar) maxSoFar = maxToHere;
        }
        if (maxSoFar == 0) return 0;
        else return (int)maxSoFar;
    }



}