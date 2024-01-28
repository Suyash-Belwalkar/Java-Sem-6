class PrintTextThread extends Thread {
    private String text;
    private int count;

    public PrintTextThread(String text, int count) {
        this.text = text;
        this.count = count;
    }

    @Override
    synchronized public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }
}

public class MultiThreadPrinting {
    public static void main(String[] args) {
        // Creating three threads with different text and count parameters
        PrintTextThread thread1 = new PrintTextThread("COVID19", 9);
        PrintTextThread thread2 = new PrintTextThread("LOCKDOWN2020", 5);
        PrintTextThread thread3 = new PrintTextThread("VACCINATED2021", 7);

        // Starting the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

