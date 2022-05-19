import java.util.Random;

public class Experiments {

    public static void main(String[] args) throws Exception {
        quadraticExp(false);
        int m = 10000019;
        compareRuntimes((int) Math.floor(m/2.0));
        compareRuntimes((int) Math.floor((19.0 * m) / 20.0));
    }

    public static void quadraticExp(boolean alternating) {
        Random random = new Random();
        int b;
        long a;
        int m = 6571;
        long p = 1000000007;
        HashTableElement element;
        for (int i = 0; i < 100; i++) {
            IHashTable hashTable = alternating ? new AQPHashTable(m,p): new QPHashTable(m, p);
//            System.out.println(i);
            for (int j = 0; j < m; j++) {
                try {
                    b = random.nextInt(100);
                    a = b + (100 * j);
                    element = new HashTableElement(a, j);
                    hashTable.Insert(element);
                } catch (Exception e) {
                    System.out.println(e.getClass());
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public static void compareRuntimes(int n) throws Exception {
        long p = 1000000007;
        int m = 10000019;
        long a, start, finish;
        int b;
        HashTableElement element;
        Random random = new Random();
        System.out.println("COMPARING FOR: n = " + n);
        IHashTable lpTable = new LPHashTable(m, p);
        IHashTable qpTable = new QPHashTable(m, p);
        IHashTable aqpTable = new AQPHashTable(m, p);
        IHashTable doubleTable = new DoubleHashTable(m, p);
        IHashTable[] tables = {lpTable, qpTable, aqpTable, doubleTable};
        for (IHashTable table : tables) {
            start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                b = random.nextInt(100);
                a = b + (100L * i);
                element = new HashTableElement(a, i);
                table.Insert(element);
            }
            finish = System.nanoTime();
            double runtime = (finish - start)/1000000.0;
            System.out.println("TABLE:" + table.getClass() + ", RUNTIME:" + runtime);
        }
    }
}
