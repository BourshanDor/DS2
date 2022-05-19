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

	public int FindIndex(long key) {
		for (int i = 0; i < this.tableLen; i++) {
			int hashVal = Hash(key, i);
			HashTableElement current = this.table[hashVal];
			if (current == null) {
				return -1;
			}


				if (!(current instanceof DeletedHashTableElement) && current.GetKey() == key) {
					return hashVal;
//				return current.getIsDeleted() ? null : current;
				}

		}
		return -1;
	}
	
	@Override
	public HashTableElement Find(long key) {
		int index = this.FindIndex(key);
		if (index == -1){
			return null;
		}
		else {
			return this.table[index];
		}
//		for (int i = 0; i < this.tableLen; i++) {
//			int hashVal = Hash(key, i);
//			HashTableElement current = this.table[hashVal];
//			if (current == null) {
//				return null;
//			}
//			if (current.GetKey() == key) {
//				return current instanceof DeletedHashTableElement ? null : current;
////				return current.getIsDeleted() ? null : current;
//			}
//		}
//		return null;
	}
	
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		if (Find(hte.GetKey()) != null) {
			throw new KeyAlreadyExistsException(hte);
		}
		if (this.freeSlots == 0) {
			throw new TableIsFullException(hte);
		}
		for (int i = 0; i < this.tableLen; i++) {
			int hashVal = Hash(hte.GetKey(), i);
			HashTableElement current = this.table[hashVal];
//			try {
//				current = this.table[hashVal];
//			} catch (NullPointerException e) {
//				current = null;
//			}
			
			if (current == null || current instanceof DeletedHashTableElement) {
				this.table[hashVal] = hte;
				this.freeSlots--;
				return;
			}
		}
	}

	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int index  = FindIndex(key);
		if (index == -1) {
			throw new KeyDoesntExistException(key);
		}
		this.freeSlots++;
		DeletedHashTableElement element = new DeletedHashTableElement(this.table[index].GetKey(), this.table[index].GetValue());
		this.table[index] = element;
	}


//	@Override
//	public void Delete(long key) throws KeyDoesntExistException {
//		HashTableElement element = Find(key);
//		if (element == null) {
//			throw new KeyDoesntExistException(key);
//		}
//		this.freeSlots++;
//		DeletedHashTableElement elem = new DeletedHashTableElement(element.GetKey(), element.GetValue());
//		element.setIsDeleted(true);
//	}
//
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
