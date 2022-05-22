import java.math.BigInteger;

public class DoubleHashTable extends OAHashTable {
	private final ModHash initialHash;
	private final ModHash.StepHash secondaryHash;
	public DoubleHashTable(int m, long p) {
		super(m);
		this.initialHash = ModHash.GetFunc(m, p);
		this.secondaryHash = ModHash.StepHash.GetFunc(m);
	}
	public ModHash getInitialHash(){
		return this.initialHash;
	}

	public ModHash.StepHash getSecondaryHash(){
		return this.secondaryHash;
	}
	
	@Override
	public int Hash(long x, int i) {
		long j = i;
		long firstHashFunc = getInitialHash().Hash(x);
		long secondHashFunc = getSecondaryHash().Hash(x);
		long step = (j*secondHashFunc)% this.getTableLen();
		return (int) ((firstHashFunc + step) % this.getTableLen());
	}
}

