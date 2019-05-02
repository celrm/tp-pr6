
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EjemploExecutors {

	private static final Executor exec = Executors.newFixedThreadPool(10);

	// private static final Executor exec = Executors.newCachedThreadPool();
	// private static final Executor exec = Executors.newSingleThreadExecutor();

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			final int x = i;
			exec.execute(new Runnable() {
				public void run() {
					for (int j = 0; j < 10; j++)
						System.out.println("I am job " + x
								+ ", running on thread "
								+ Thread.currentThread().getName()
								+ ", and my j is " + j);
				}
			});
		}
	}
}
