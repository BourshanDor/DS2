import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ModHash {
	private final int m;
	private final long p;
	private final long a;
	private final long b;


	public ModHash(long a, long b, long p, int m) {
		this.a = a;
		this.b = b;
		this.p = p;
		this.m = m;
	}

	public static ModHash GetFunc(int m, long p){
		long a = ThreadLocalRandom.current().nextLong(1, p);
		long b = ThreadLocalRandom.current().nextLong(0, p);

		return new ModHash(a, b, p, m);
	}
	
	public int Hash(long key) {
		return (int) (((this.a * key + this.b) % this.p) % this.m);
	}

	public static class StepHash {
		private final int m;
		private final long t;

		public StepHash(int m, long t) {
			this.m = m;
			this.t = t;
		}
		public static StepHash GetFunc(int m){
			long t = ThreadLocalRandom.current().nextLong(1, m); // ensure a >= 1
			return new StepHash(m,t);
		}

		public long Hash(long key) {
			return (int) ((key) % this.t) + 1;
		}

	}

}
