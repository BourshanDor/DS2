import java.util.Random;

public class DoubleHashTable extends OAHashTable {
	private final ModHash initialHash;
	private final ModHash.StepHash secondaryHash;
	public DoubleHashTable(int m, long p) {
		super(m);
		this.initialHash = ModHash.GetFunc(m, p);
		this.secondaryHash = ModHash.StepHash.GetFunc(m);
	}
	
	@Override
	public int Hash(long x, int i) {
		return (int)(((this.initialHash.Hash(x) + this.secondaryHash.Hash(x) * i) + this.tableLen)%this.tableLen);
	}
	
}
