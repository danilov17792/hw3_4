public class Main {
    static Object mon = new Object();
    static volatile String currentChar = "A";
    static final int num = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currentChar != "A"){
                            mon.wait();
                        }
                        System.out.print("A");
                        currentChar = "B";
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currentChar != "B") {
                            mon.wait();
                        }
                        System.out.print("B");
                        currentChar = "C";
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currentChar != "C") {
                            mon.wait();
                        }
                        System.out.print("C");
                        currentChar = "A";
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
