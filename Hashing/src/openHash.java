// 4502894
// Maxwell Kei Farouk
// Prac 4

public class openHash {

    private KeyValue[] table;
    private int m;
    private final int myPrime = 997;

    public openHash(int m) {
        if (m <= 0) throw new IllegalArgumentException("Table size must be > 0");
        this.m = m;
        table = new KeyValue[m + 1]; // 1-based indexing
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
        return (k % m) + 1; // ensures 1..m
    }


    public void insert(String key, String value) {
        int slot = hash(key);

        while (table[slot] != null) {
            if (table[slot].key.equals(key)) {
                table[slot].value = value; // update existing key
                return;
            }
            slot = slot + myPrime;
            if (slot > m) slot = slot % m;
            if (slot == 0) slot = m;
        }

        table[slot] = new KeyValue(key, value);
    }

    public String lookup(String key) {
        int slot = hash(key);

        while (table[slot] != null) {
            if (table[slot].key.equals(key)) return table[slot].value;
            slot = slot + myPrime;
            if (slot > m) slot = slot % m;
            if (slot == 0) slot = m;
        }

        return null;
    }

    public String remove(String key) {
        int slot = hash(key);

        while (table[slot] != null) {
            if (table[slot].key.equals(key)) {
                String val = table[slot].value;
                table[slot] = null;
                fixAfterRemoval(slot);
                return val;
            }
            slot = slot + myPrime;
            if (slot > m) slot = slot % m;
            if (slot == 0) slot = m;
        }

        return null;
    }

    private void fixAfterRemoval(int startSlot) {
        int slot = startSlot + myPrime;
        if (slot > m) slot = slot % m;
        if (slot == 0) slot = m;

        while (table[slot] != null) {
            KeyValue kv = table[slot];
            table[slot] = null;
            insert(kv.key, kv.value);
            slot = slot + myPrime;
            if (slot > m) slot = slot % m;
            if (slot == 0) slot = m;
        }
    }
}