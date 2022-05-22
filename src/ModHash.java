import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ModHash {
	private final long m;
	private final long p;
	private final long a;
	private final long b;


	public ModHash(long a, long b, long p, long m) {
		this.a = a;
		this.b = b;
		this.p = p;
		this.m = m;
	}

	public static ModHash GetFunc(int m, long p){

		long a = ThreadLocalRandom.current().nextLong(1,p);
		long b = ThreadLocalRandom.current().nextLong(p);
		return new ModHash(a, b, p, m);
	}
	
	public int Hash(long key) {
		return (int) (((this.a * key + this.b) % this.p) % this.m);
	}

	/**
	 * Produces function from the family of function wear the form   f:[p] → [k] ,   x (mod k) + 1
	 * This class randomly choose k from the set {1, 2, ..., m-1}
	 */
	public static class StepHash {
		private final long m;
		private final long k;

		public StepHash(long m, long a) {
			this.m = m;
			this.k = a;
		}
		public static StepHash GetFunc(int m){

			long k = ThreadLocalRandom.current().nextLong(1,m);
			return new StepHash(m,k);
		}

		public int Hash(long key) {

			return (int) ((key) % this.k + 1);
		}

	}

}
