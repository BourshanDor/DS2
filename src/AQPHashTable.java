import java.util.Random;

public class AQPHashTable extends OAHashTable {
	private final ModHash modHash;
	public AQPHashTable(int m, long p) {
		super(m);
		this.modHash = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		int alt = (int) Math.pow((-1), i);
		int ret = (this.modHash.Hash(x) + alt*(i * i)) % this.tableLen;
		return (ret + this.tableLen) % tableLen;
	}
}
