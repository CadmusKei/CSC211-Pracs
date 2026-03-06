 public class Main{
    public static void main(String[] args)
    {
        int[] arr = {-4, 3, 5, -3, -5, 8, 1};

    }

    static void on2Method(int[] arr)
    {
        int n = arr.length;
        int[] sumTo = new int[n+1];

        for (int i = 0; i < n; i++)
        {
            sumTo[i] = sumTo[i-1] + arr[i];
        }
        int maxSoFar = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = n; j <= 0; j-- )
            {
                return;
            }
        }
    }

    static void onMethod(int[] arr)
    {
        int maxToHere = 0;
        int maxSoFar = 0;

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] + maxToHere > 0)
            {
                maxToHere += arr[i];
                System.out.println(maxToHere);
            }
            else {maxToHere = 0;}
            if (maxToHere > maxSoFar) maxSoFar = maxToHere;
        }

        System.out.println("Max is: " + maxSoFar);
    }
 }