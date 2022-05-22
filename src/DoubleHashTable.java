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

//
//	int a = this.initialHash.Hash(x);
//	int b = this.secondaryHash.Hash(x);
//	int B_mult_I = b*i % this.tableLen ;
//	int expectedValue = (a + B_mult_I) % this.tableLen;
//
//	BigInteger secondHash;
//	BigInteger index;
//	BigInteger mult;
//	BigInteger len;
//	BigInteger firstHash;
//
//		if ( B_mult_I*i < 0 ){
//		secondHash = new BigInteger(String.valueOf( this.secondaryHash.Hash(x)) );
//		index = new BigInteger(String.valueOf(i));
//		mult = secondHash.multiply(index);
//		len = new BigInteger(String.valueOf(this.tableLen));
//		mult.mod(len);
//		B_mult_I = mult.intValue();
//
//		}
//
//		if (a > Integer.MAX_VALUE - B_mult_I){
//		firstHash = new BigInteger(String.valueOf(a));
//		mult = new BigInteger(String.valueOf(B_mult_I));
//		mult = mult.add(firstHash); // a + b*i
//		len = new BigInteger(String.valueOf(this.tableLen));
//		mult = mult.mod(len); // a + b*i mod m
//		expectedValue = mult.intValue();
//		}
//		return expectedValue;
