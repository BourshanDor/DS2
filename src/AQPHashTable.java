import java.util.Random;

public class AQPHashTable extends OAHashTable {
	private final ModHash modHash;
	public AQPHashTable(int m, long p) {
		super(m);
		this.modHash = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		int alt = i % 2 == 0 ? 1 : -1 ;
		int ret = (this.modHash.Hash(x) + alt*(i * i)) % this.tableLen;
		return (ret + this.tableLen) % tableLen;
	}
}
