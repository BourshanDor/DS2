import java.util.Objects;

public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	protected long tableLen;
	private long freeSlots;
	
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.tableLen = table.length;
		this.freeSlots = m;
	}

	public long getFreeSlots(){
		return freeSlots;
	}
	public long getTableLen(){
		return tableLen;
	}
	public void setFreeSlots(long free){
		this.freeSlots = free;
	}

	/**
	 * -1 - there is null in the sequence
	 * -2 - all the element in the sequence are real
	 * -3 - the sequence is full but have deleted elements
	 *
	 * @param key
	 * @return
	 */
	public int FindIndex(long key) {
		boolean flag = false ;
		for (int i = 0; i < this.tableLen; i++) {
			int hashVal = Hash(key, i);
			HashTableElement current = this.table[hashVal];
			if (current == null ) // change
			{
				return -1;
			}
			if (current.GetKey() < 0){
				flag = true;
			}
			if (!(current.GetKey() < 0 ) && current.GetKey() == key) {
				return hashVal;
			}
		}

		return flag? -3 : -2;                              // the sequence is full
	}
	
	@Override
	public HashTableElement Find(long key) {
		int index = this.FindIndex(key);
		if (index == -1 || index == -2 || index == -3 ){
			return null;
		}
		else {

			return this.table[index];
		}

	}

	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		int find = FindIndex(hte.GetKey());

		if (find != -1 && find != -2 && find !=-3)
		{
			throw new KeyAlreadyExistsException(hte);
		}
		if (this.getFreeSlots() == 0 || find == -2) {
			throw new TableIsFullException(hte);
		}
		for (int i = 0; i < this.tableLen; i++) {
			int hashVal = Hash(hte.GetKey(), i);

			HashTableElement current = this.table[hashVal];

			if (current == null || current.GetKey() < 0 ) {
				this.table[hashVal] = hte;
				this.setFreeSlots(this.getFreeSlots() - 1);
				return;
			}

		}
	}

	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int index  = FindIndex(key);
		if (index == -1 || index == -2 || index == -3) {
			throw new KeyDoesntExistException(key);
		}
		this.setFreeSlots(this.getFreeSlots() + 1);
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
