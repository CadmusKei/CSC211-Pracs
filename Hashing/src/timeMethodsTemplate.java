import java.text.*;
import java.util.Random;

public class timeMethodsTemplate {
    public static int N = 1_000_000;
    public static int used = 950_000;

    public static class KeyValue {
        public String key;
        public String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        DecimalFormat fiveD = new DecimalFormat("0.00000");
        Random randGen = new Random();

        KeyValue[] data = generateKeyValuePairs(N);

        openHash open = new openHash(N + 50);
        chainHash chained = new chainHash(N / 2);

        for (int i = 0; i < used; i++) {
            KeyValue kv = data[i];
            open.insert(kv.key, kv.value);
            chained.insert(kv.key, kv.value);
        }

        int repetitions = 100;
        double openRunTime = 0, chainedRunTime = 0;

        for (int r = 0; r < repetitions; r++) {
            KeyValue kv = data[randGen.nextInt(used)];

            long start = System.nanoTime();
            open.lookup(kv.key);
            long finish = System.nanoTime();
            openRunTime += (finish - start);

            start = System.nanoTime();
            chained.lookup(kv.key);
            finish = System.nanoTime();
            chainedRunTime += (finish - start);
        }

        System.out.println("Key Size used (N=" + used + "): ");
        System.out.println("Average Open Hash lookup (µs): " + fiveD.format(openRunTime / repetitions / 1000));
        System.out.println("Average Chained Hash lookup (µs):" + fiveD.format(chainedRunTime / repetitions / 1000));
    }

    public static KeyValue[] generateKeyValuePairs(int N) {
        KeyValue[] keyValues = new KeyValue[N];
        int[] keys = new int[N];

        for (int i = 0; i < N; i++) keys[i] = i + 1;

        Random rand = new Random();
        for (int i = N - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = keys[i];
            keys[i] = keys[j];
            keys[j] = temp;
        }

        for (int i = 0; i < N; i++) {
            keyValues[i] = new KeyValue(Integer.toString(keys[i]), Integer.toString(i + 1));
        }

        return keyValues;
    }
}