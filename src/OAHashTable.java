import java.util.Objects;

public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	protected int tableLen;
	private int freeSlots;



	
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.tableLen = table.length;
		this.freeSlots = m;
	}

	public int getFreeSlots(){
		return freeSlots;
	}
	public int getTableLen(){
		return tableLen;
	}
	public int FindIndex(long key) {
		for (int i = 0; i < this.tableLen; i++) {
			int hashVal = Hash(key, i);
			HashTableElement current = this.table[hashVal];
			if (current == null) {
				return -1;
			}
			if (!(current.GetKey() < 0 ) && current.GetKey() == key) {
				return hashVal;
			}

		}
		return -2;     // the sequence is full
	}
	
	@Override
	public HashTableElement Find(long key) {
		int index = this.FindIndex(key);
		if (index == -1 || index == -2 ){
			return null;
		}
		else {

			return this.table[index];
		}

	}

	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		int find = FindIndex(hte.GetKey());

		if (find != -1 && find != -2)
		{
			throw new KeyAlreadyExistsException(hte);
		}
		if (this.freeSlots == 0 || find == -2) {
			throw new TableIsFullException(hte);
		}
		for (int i = 0; i < this.tableLen; i++) {
			int hashVal = Hash(hte.GetKey(), i);

			HashTableElement current = this.table[hashVal];

			if (current == null || current.GetKey() < 0 ) {
				this.table[hashVal] = hte;
				this.freeSlots--;
				return;
			}

		}
	}

	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int index  = FindIndex(key);
		if (index == -1 || index == -2) {
			throw new KeyDoesntExistException(key);
		}
		this.freeSlots++;
		this.table[index] = new HashTableElement(-1,0);
	}

	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
