import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Experiments {

    public static void main(String[] args) throws Exception {
//        System.out.println("Q3.1 : ");
//        groupSize(6571);
//        check ( );
        quadraticExp(true);
        for (int j = 0 ; j < 50 ; j++) {
            question5();
        }
        double [] average = {0,0,0,0};
        int m = 10000019;
        for (int j = 0; j < 100 ; j ++) {
            List<HashTableElement> seq = randomSequence((int) Math.floor(m / 2.0));
            for (int i = 1; i < 5; i++) {
                double time  = compareRuntimes( i, seq);
                average[i] +=  time ;
            }
        }

            System.out.println("LPHashTable: " + average[0]/100);
            System.out.println("QPHashTable: " + average[1]/100);
            System.out.println("AQPHashTable: " + average[2]/100);
            System.out.println("DoubleHashTable: " + average[3]/100);


//        double [] average = {0,0,0,0};
//        int m = 10000019;
//        for (int j = 0; j < 1 ; j ++) {
//            List<HashTableElement> seq = randomSequence((int) Math.floor((19.0 * m) / 20.0));
//            for (int i = 1; i < 5; i++) {
//                if (i == 2){
//                    continue;
//                }
//                double time  = compareRuntimes( i, seq);
//                average[i-1] +=  time ;
//            }
//        }
//
//        System.out.println("LPHashTable: " + average[0]);
////        System.out.println("QPHashTable: " + average[1]);
//        System.out.println("AQPHashTable: " + average[2]);
//        System.out.println("DoubleHashTable: " + average[3]);
////
    }

    public static void groupSize(int q) {
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            group1.add((i * i) % q);
            group2.add((((int) (Math.pow(-1, i) * i * i)) % q + q) % q);
        }
        group1.sort((a, b) -> Integer.compare(a, b));
        group2.sort((a, b) -> Integer.compare(a, b));
        System.out.println("Size of group 1 is : " + countDifferentNumber(group1));
        System.out.println("Size of group 2 is : " + countDifferentNumber(group2));

    }

    public static int countDifferentNumber(List<Integer> arr) {

        int counter = 0;
        int i = 0;

        while (i < arr.size()) {
            counter++;

            while (i + 1 < arr.size() && arr.get(i).equals(arr.get(i + 1))) {
                i++;
            }

            i++;
        }

        return counter;
    }
//    public static void check ( ) {
//        Random random = new Random();
//
//        int b;
//        long a;
//        int m = 6571;
//        long q = 1000000007;
//        List<Integer> group1 = new ArrayList<>();
//        QPHashTable hashTable =new QPHashTable(m, q);
//
//        for (int j = 0; j < m; j++) {
//
//                    b = random.nextInt(100);
//                    a = b + (100 * j);
//                    group1.add(hashTable.Hash(a,0));
//
//        }
//        group1.sort((x, y) -> Integer.compare(x, y));
//        return;
//    }

    public static void quadraticExp(boolean alternating) {
        Random random = new Random();
        int b;
        long a;
        int m = 6571;
        long p = 1000000007;
        HashTableElement element;
        for (int i = 0; i < 100; i++) {
            OAHashTable hashTable = alternating ? new AQPHashTable(m, p) : new QPHashTable(m, p);
            boolean flag = false;
            for (int j = 0; j < m; j++) {
                try {
                    b = random.nextInt(100);
                    a = b + (100 * j);
                    element = new HashTableElement(a, j);
                    hashTable.Insert(element);
                } catch (Exception e) {
                    System.out.println(e.getClass());
                    flag = true;
                    break;
                }

            }
            if (flag) {
                break;
            }
        }
    }

    public static double compareRuntimes( int tableNumber , List<HashTableElement> sequence) throws Exception {
        long p = 1000000007;
        int m = 10000019;
        long a, start, finish;
        IHashTable table;
//        System.out.println("COMPARING FOR: n = " + sequence.size());
        switch (tableNumber) {
            case 1: {
                table = new LPHashTable(m, p);
                break;
            }
            case 2: {
                table = new QPHashTable(m, p);
                break;
            }
            case 3: {
                table = new AQPHashTable(m, p);
                break;
            }
            case 4: {
                table = new DoubleHashTable(m, p);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + tableNumber);
        }

        start = System.nanoTime();
        for (HashTableElement element : sequence) {
            try {
                table.Insert(element);
            } catch (Exception e) {
                System.out.println("There is a problem with: " + table.getClass() + "Exception: "  + e.getClass());
                break;
            }
        }
        finish = System.nanoTime();
        double runtime = (finish - start) / 1000000000.0;
        return runtime;
//        System.out.println("TABLE:" + table.getClass() + ", RUNTIME:" + runtime + " Sec");

    }

    public static List<HashTableElement> randomSequence(int n) {
        long a, b;
        Random random = new Random();
        HashTableElement element;
        List<HashTableElement> sequence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            b = random.nextInt(100);
            a = b + (100L * i);
            element = new HashTableElement(a, i);
            sequence.add(element);
        }
        return sequence;
    }

    public static void question5() throws Exception {
        System.out.println("#### QUESTION 5 ####");
        long start, finish;
        double runtime;
        double firstHalf = 0, secondHalf = 0;
        int m = 10000019;
        long p = 1000000007;
        IHashTable hashTable = new DoubleHashTable(m, p);
        for (int i = 0; i < 6; i++) {
            List<HashTableElement> seq = randomSequence((int) Math.floor(m / 2.0));
            for (HashTableElement element : seq) {
                hashTable.Insert(element);
            }
            start = System.nanoTime();
            for (HashTableElement element : seq) {
                hashTable.Delete(element.GetKey());
            }
            finish = System.nanoTime();
            runtime = (finish - start) / 1000000000.0;
            if (i < 3) {
                firstHalf += runtime;
            } else {
                secondHalf += runtime;
            }
            System.out.printf("Iteration %d: %f%n", i, runtime);
        }
        System.out.println("First half: " + firstHalf);
        System.out.println("Second half: " + secondHalf);
        System.out.println("#### END QUESTION 5 ####");
    }
}


