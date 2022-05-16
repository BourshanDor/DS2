import java.util.Objects;

public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	private int freeSlots;
	
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.freeSlots = m;
		// TODO add to constructor as needed
	}
	
	
	@Override
	public HashTableElement Find(long key) {
		// TODO implement find
		return null;
	}
	
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		if (this.freeSlots == 0) {
			throw new TableIsFullException(hte);
		}
		//TODO: key already exists
		for (int i = 0; i < this.table.length; i++) {
			int hashVal = Hash(hte.GetKey(), i);
			HashTableElement current;
			try {
				current = this.table[hashVal];
				if (current.GetKey() == hte.GetKey()) {
					throw new KeyAlreadyExistsException(hte);
				}
			} catch (NullPointerException e) {
				current = null;
			}
			
			if (current == null || current.getIsDeleted()) {
				this.table[hashVal] = hte;
				this.freeSlots--;
				return;
			}
		}
	}
	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		HashTableElement element = Find(key);
		if (element == null) {
			throw new KeyDoesntExistException(key);
		}
		element.setIsDeleted(true);
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
