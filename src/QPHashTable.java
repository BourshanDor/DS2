
public class QPHashTable extends OAHashTable {

	private ModHash modHash;
	public QPHashTable(int m, long p) {
		super(m);
		 this.modHash = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return ((this.modHash.Hash(x) + (i * i))%this.tableLen);
	}
}
