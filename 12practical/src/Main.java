// 4502894
// Maxwell Kei Farouk
// Wednesday E1/E2

import java.util.Arrays;
import java.util.Random;

public class Main{
    public static void main(String[] args)
    {
        int[] arr = createArray(30);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] createArray(int n)
    {
        int[] arr = new int[n];
        Random randGenartor = new Random();

        for (int i = 0; i < n; i++)
        {
             arr[i] = (int)(randGenartor.nextInt(1, n)* Math.pow((-1), randGenartor.nextInt(2, 4)));
        }

        return arr;
    }
}