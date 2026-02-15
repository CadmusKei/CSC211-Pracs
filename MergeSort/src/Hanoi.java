public class Hanoi {
    public static void main(String[] args){
        hanoi(20, 'A', 'B', 'C');

        int[] arr = {1,2,3,4,5,6};

    }
    public static void hanoi(int n, char source, char aux, char destination)
    {
        if (n == 1)
        {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }
        hanoi(n - 1, source, destination, aux);
        System.out.println("Moving disk: " + n + " to point " + destination);
        hanoi(n -1, aux, source, destination);
    }

    static int sumOf(int i, int[] arr)
    {
        if (i == arr.length) return 0;
        else return arr[i] + sumOf(i + 1, arr);
    }

    static int findFaction(int n)
    {
        if (n == 0) return 1;
        else return n*findFaction(n-1);
    }
}
