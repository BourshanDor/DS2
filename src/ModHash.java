import java.util.Random;

public class ModHash {

	private final int m;
	private final long p;
	private final int a;
	private final int b;

	public ModHash(int a, int b, long p, int m) {
		this.a = a;
		this.b = b;
		this.p = p;
		this.m = m;
	}

	public static ModHash GetFunc(int m, long p){
		Random random = new Random();
		int a = random.nextInt((int) p) + 1; // ensure a >= 1
		int b = random.nextInt((int) p);

		return new ModHash(a, b, p, m);
	}
	
	public int Hash(long key) {
		return (int) (((this.a * key + this.b) % this.p) % this.m);
	}
}
