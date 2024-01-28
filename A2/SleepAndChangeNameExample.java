class SleepAndChangeNameThread extends Thread {
    @Override
    public void run() {
        for (int i = 100; i >= 1; i--) {
            try {
                 // Change the name of the thread
                String newName = "Thread-" + i;
                Thread.currentThread().setName(newName);
                Thread.sleep(6000); // Sleep for 6 seconds
                System.out.println(Thread.currentThread().getName() + ": " + i);
                
               
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SleepAndChangeNameExample {
    public static void main(String[] args) {
        // Creating a thread and starting it
        SleepAndChangeNameThread myThread = new SleepAndChangeNameThread();
        myThread.start();
    }
}
