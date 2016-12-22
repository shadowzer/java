public class Ex8Lucky {

    protected static int x = 0;
    protected static int count = 0;

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            synchronized (Ex8Lucky.class) {
                while (x < 999999) {
                    int tmp;
                    tmp = x++;
                    if ((tmp % 10) + (tmp / 10) % 10 + (tmp / 100) % 10 == (tmp / 1000)
                            % 10 + (tmp / 10000) % 10 + (tmp / 100000) % 10) {
                        count++;
                        System.out.println(tmp);
                    }
                }
            }
        }
    }

    public static void test() throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}