package lab2.task3;


public class HashTable {
    private static final int START_CAPACITY = 17;
    private final Chain[] table;
    private final int capacity;
    public double KNUT_CONSTANT = 0.61803398875;

    public HashTable() {
        capacity = START_CAPACITY;
        table = new Chain[capacity];
        for (int i = 0; i < capacity; ++i) {
            table[i] = null;
        }
    }

    public HashTable(int capacity) {
        table = new Chain[capacity];
        this.capacity = capacity;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = null;
        }
    }

    public void addChainMethod(int key, int value) {
        final int hash = getHashKey(key, KNUT_CONSTANT);
        if (table[hash] == null) {
            table[hash] = new Chain(key, value);
        } else {
            Chain entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key) {
                entry = entry.getNext();
            }
            if (entry.getKey() == key) {
                entry.setValue(value);
            } else {
                entry.setNext(new Chain(key, value));
            }
        }
    }

    public void addDoubleHashMethod(int key, int value) {
        int hash = firstHash(key, KNUT_CONSTANT);
        if (table[hash] == null) {
            table[hash] = new Chain(key, value);
            System.out.println("Hash for key " + key + " is " + hash);
        } else {
            int i = 1;
            while (table[hash] != null) {
                hash = (firstHash(key, KNUT_CONSTANT) + i * secondHash(key)) % START_CAPACITY;
                i++;
            }
            System.out.println("Hash for key " + key + " is " + hash);
            table[hash] = new Chain(key, value);
        }
    }

    public void addLinearMethod(int key, int value) {
        int hash = firstHash(key, KNUT_CONSTANT);
        if (table[hash] == null) {
            table[hash] = new Chain(key, value);
            System.out.println("Hash for key " + key + " is " + hash);
        } else {
            int i = 1;
            while (table[hash] != null) {
                hash = (hash + i) % START_CAPACITY;
                i++;
            }
            System.out.println("Hash for key " + key + " is " + hash);
            table[hash] = new Chain(key, value);
        }
    }

    public int getHashKey(int key, double constant) {
        final int M = 17;
        System.out.println("Hash for key " + key + " is " + (int) (M * ((constant * key) % 1)));
        return (int) (M * ((constant * key) % 1));
    }

    private int firstHash(int key, double constant) {
        final int M = 17;
        return (int) (M * ((constant * key) % 1));
    }

    private int secondHash(int key) {
        final int M = 17;
        return (1 + (key % (M - 2)));
    }

    public String print() {
        final StringBuilder description = new StringBuilder("Hash table: [ ");
        for (int i = 0; i < capacity; i++) {
            description.append("{  ");
            if (table[i] != null) {
                Chain entry = table[i];
                do {
                    description.append(String.format("%d  ", entry.getValue()));
                    entry = entry.getNext();
                } while (entry != null);
            }
            description.append("} ");
        }
        description.append(']');
        return description.toString();
    }

    public int getNumberOfCollision() {
        int count = 0;
        for (Chain chain : table) {
            int num = 1;
            if (chain != null) {
                Chain next = chain;
                while (next.getNext() != null) {
                    num++;
                    next = next.getNext();
                }
                if (num > 1) count++;
            }
        }
        return count;
    }

    public int getLengthOfCollision() {
        int count = 0;
        for (Chain chain : table) {
            int num = 1;
            if (chain != null) {
                Chain next = chain;
                while (next.getNext() != null) {
                    num++;
                    next = next.getNext();
                }
                if (num > count) count = num;
            }
        }
        return count;
    }
}


