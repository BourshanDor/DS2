import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.management.openmbean.KeyAlreadyExistsException;


class IHashTableTest {


    @Test
    void insertThrowFull() throws IHashTable.KeyAlreadyExistsException, IHashTable.KeyDoesntExistException, IHashTable.TableIsFullException {

        IHashTable linearTable = new LPHashTable(7,7);
//        IHashTable quadraticTable = new QPHashTable(7,7);
        IHashTable doubleTable = new DoubleHashTable(7,7);
        IHashTable altTable = new AQPHashTable(7,7);

        for (int i = 0; i < 7; i ++) {
            HashTableElement elem = new HashTableElement(i, i);
            linearTable.Insert(elem);
//            if (i % 2 == 0 ){
//            quadraticTable.Insert(elem);
//            }
            doubleTable.Insert(elem);
            altTable.Insert(elem);
        }
        HashTableElement elem1 = new HashTableElement(7, 7);
        assertThrows(IHashTable.TableIsFullException.class, ()-> linearTable.Insert(elem1));
//        assertThrows(IHashTable.TableIsFullException.class, ()-> quadraticTable.Insert(elem1));
        assertThrows(IHashTable.TableIsFullException.class, ()-> doubleTable.Insert(elem1));
        assertThrows(IHashTable.TableIsFullException.class, ()-> altTable.Insert(elem1));

        for (int i = 0; i < 7; i ++) {
            HashTableElement elem = new HashTableElement(i, i);
            linearTable.Delete(elem.GetKey());
            doubleTable.Delete(elem.GetKey());
            altTable.Delete(elem.GetKey());
            assertDoesNotThrow(()-> linearTable.Insert(elem));
            assertDoesNotThrow(()-> doubleTable.Insert(elem));
            assertDoesNotThrow(()-> altTable.Insert(elem));

        }
    }




    @Test
    void insertThrowExist() throws IHashTable.KeyAlreadyExistsException, IHashTable.KeyDoesntExistException, IHashTable.TableIsFullException {

        IHashTable linearTable = new LPHashTable(11, 11);
        IHashTable quadraticTable = new QPHashTable(11, 11);
        IHashTable doubleTable = new DoubleHashTable(11, 11);
        IHashTable altTable = new AQPHashTable(11, 11);

        for (int i = 0; i < 5; i++) {

            HashTableElement elem = new HashTableElement(i, i);
            linearTable.Insert(elem);
            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> linearTable.Insert(elem));
            quadraticTable.Insert(elem);
            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> quadraticTable.Insert(elem));
            doubleTable.Insert(elem);
            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> doubleTable.Insert(elem));
            altTable.Insert(elem);
            assertThrows(IHashTable.KeyAlreadyExistsException.class, () -> altTable.Insert(elem));

        }
    }


}

