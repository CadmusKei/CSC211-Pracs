// 4502894
// Maxwell Kei Farouk
// Prac 4

import java.util.LinkedList;

public class chainHash {

    private LinkedList<KeyValue>[] table;
    private final int m;

        public chainHash(int m) {
            if (m <= 0) throw new IllegalArgumentException("Table size must be > 0");
            this.m = m;
            table = new LinkedList[m + 1];
            for (int i = 1; i <= m; i++) {
                table[i] = new LinkedList<>();
            }
        }

        public static class KeyValue {
            public String key;
            public String value;

            public KeyValue(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }

        private int hash(String key) {
            int k = Integer.parseInt(key);
            return (k % m) + 1;
        }

    public void insert(String key, String value) {
        int slot = hash(key);
        LinkedList<KeyValue> list = table[slot];

        for (KeyValue kv : list) {
            if (kv.key.equals(key)) {
                kv.value = value;
                return;
            }
        }

        list.add(new KeyValue(key, value));
    }

    public String lookup(String key) {
        int slot = hash(key);
        LinkedList<KeyValue> list = table[slot];

        for (KeyValue kv : list) {
            if (kv.key.equals(key)) return kv.value;
        }

        return null;
    }


    public String remove(String key) {
        int slot = hash(key);
        LinkedList<KeyValue> list = table[slot];

        for (KeyValue kv : list) {
            if (kv.key.equals(key)) {
                String val = kv.value;
                list.remove(kv);
                return val;
            }
        }

        return null;
    }
}