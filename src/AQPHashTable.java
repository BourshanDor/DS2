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
		long j = i ;
		long hash = this.modHash.Hash(x);
		return (int)((((hash + alt*(j*j))% this.getTableLen()) + this.getTableLen()) % this.getTableLen());
	}
}
