import com.sun.org.apache.regexp.internal.RE;

public class Ex8Lucky {

    private int cnt;

    class LuckyThread extends Thread {
        @Override
        public void run() {
            Repository repository = new Repository();
            synchronized (repository) {
                while (repository.x < 999999) {
                    repository.x++;
                    if ((repository.x % 10) + (repository.x / 10) % 10 + (repository.x / 100) % 10 == (repository.x / 1000)
                            % 10 + (repository.x / 10000) % 10 + (repository.x / 100000) % 10) {
                        System.out.println(repository.x);
                        repository.count++;
                    }
                }
                cnt = repository.count;
            }
        }
    }

    class Repository {
        protected int x = 0;
        protected int count = 0;
    }

    public void test() throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + cnt);
    }
}