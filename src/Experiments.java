import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiments {

    public static void main(String[] args) throws Exception {
        System.out.println("Q3.1 : ");
        groupSize(6571);
        quadraticExp(false);
//        int m = 10000019;
//        compareRuntimes((int) Math.floor(m/2.0));
//        compareRuntimes((int) Math.floor((19.0 * m) / 20.0));
    }

    public static void groupSize(int q) {
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();
        for (int i = 0; i < q ; i ++) {
            group1.add((i*i)% q);
            group2.add((((int)(Math.pow(-1,i)*i*i))% q + q) % q );
        }
        group1.sort((a,b)-> Integer.compare(a,b));
        group2.sort((a,b)-> Integer.compare(a,b));
        System.out.println("Size of group 1 is : " + countDifferentNumber(group1));
        System.out.println("Size of group 2 is : " + countDifferentNumber(group2));

    }

    public static int countDifferentNumber ( List<Integer> arr){

        int counter = 0 ;
        int i = 0;

        while (i <  arr.size() ){
            counter ++ ;

            while (i + 1 <  arr.size() && arr.get(i).equals(arr.get(i+1))){
                    i++;
            }

            i ++;
        }

        return counter;
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
