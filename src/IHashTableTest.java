import org.junit.jupiter.api.Test;

import javax.management.openmbean.KeyAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;

class IHashTableTest {


    @Test
    void insertThrowFull() throws IHashTable.KeyAlreadyExistsException, IHashTable.KeyDoesntExistException, IHashTable.TableIsFullException {



        IHashTable linearTable = new LPHashTable(7,7);
//        IHashTable quadraticTable = new QPHashTable(7,7);
//        IHashTable doubleTable = new DoubleHashTable(7,7);
//        IHashTable altTable = new AQPHashTable(7,7);

        for (int i = 0; i < 7; i ++) {
            HashTableElement elem = new HashTableElement(i, i);

            linearTable.Insert(elem);
//            quadraticTable.Insert(elem);
//            doubleTable.Insert(elem);
//            altTable.Insert(elem);

        }
        HashTableElement elem1 = new HashTableElement(7, 7);
        assertThrows(IHashTable.TableIsFullException.class, ()-> linearTable.Insert(elem1));
//        assertThrows(IHashTable.TableIsFullException.class, ()-> quadraticTable.Insert(elem1));
//        assertThrows(IHashTable.TableIsFullException.class, ()-> doubleTable.Insert(elem1));
//        assertThrows(IHashTable.TableIsFullException.class, ()-> altTable.Insert(elem1));

    }

    @Test
    void insertThrowExist() throws IHashTable.KeyAlreadyExistsException, IHashTable.KeyDoesntExistException, IHashTable.TableIsFullException {

        IHashTable linearTable = new LPHashTable(70000000, 1000000007);
//        IHashTable quadraticTable = new QPHashTable(7, 7);
//        IHashTable doubleTable = new DoubleHashTable(7, 7);
//        IHashTable altTable = new AQPHashTable(7, 7);

        for (int i = 0; i < 6; i++) {

            HashTableElement elem = new HashTableElement(7000L+i, 7000L);
            linearTable.Insert(elem);
            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> linearTable.Insert(elem));
//            quadraticTable.Insert(elem);
//            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> quadraticTable.Insert(elem));
//            doubleTable.Insert(elem);
//            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> doubleTable.Insert(elem));
//            altTable.Insert(elem);
//            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> altTable.Insert(elem));

        }
    }


}

