
public class LPHashTable extends OAHashTable {
	private final ModHash modHash;

	public LPHashTable(int m, long p) {
		super(m);
		this.modHash = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return ((this.modHash.Hash(x) + i)%this.tableLen);
	}
	
}
