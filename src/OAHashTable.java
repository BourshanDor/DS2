
public abstract class OAHashTable implements IHashTable {
	
	private HashTableElement [] table;
	
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		// TODO add to constructor as needed
	}
	
	
	@Override
	public HashTableElement Find(long key) {
		// TODO implement find
		return null;
	}
	
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		// TODO implement insertion	
	}
	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		// TODO implement deletion
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
