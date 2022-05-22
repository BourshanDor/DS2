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

	public static class StepHash {
		private final long m;
		private final long a;

		public StepHash(long m, long a) {
			this.m = m;
			this.a = a;
		}
		public static StepHash GetFunc(int m){

			long a = ThreadLocalRandom.current().nextLong(1,m);
			return new StepHash(m,a);
		}

		public int Hash(long key) {

			return (int) ((key) % this.a + 1);
		}

	}

}
