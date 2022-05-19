
public class HashTableElement{
	private long key;
	private long value;
//	private boolean isDeleted;
	
	public HashTableElement(long key, long value) {
		this.key = key;
		this.value = value;
//		this.isDeleted = false;
	}
	
	public long GetKey() { return this.key;}
	
	public long GetValue() { return this.value;}

//	public boolean getIsDeleted() {
//		return this.isDeleted;
//	}
//
//	public void setIsDeleted(boolean newValue) {
//		this.isDeleted = newValue;
//	}
}