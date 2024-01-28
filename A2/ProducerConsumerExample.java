import java.util.LinkedList;

class SharedResource {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private int capacity;

    public SharedResource(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) throws InterruptedException {
        while (buffer.size() == capacity) {
            // Buffer is full, wait for the consumer to consume
            wait();
        }

        buffer.add(value);
        System.out.println("Produced: " + value);

        // Notify the consumer that a value is produced
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            // Buffer is empty, wait for the producer to produce
            wait();
        }

        int consumedValue = buffer.removeFirst();
        System.out.println("Consumed: " + consumedValue);

        // Notify the producer that a value is consumed
        notify();

        // return consumedValue;
    }
}

class Producer extends Thread {
    private SharedResource sharedResource;

    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                sharedResource.produce(i);
                Thread.sleep(1000); // Simulate some time to produce a value
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private SharedResource sharedResource;

    public Consumer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                sharedResource.consume();
                Thread.sleep(1500); // Simulate some time to consume a value
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(2);

        Producer producer = new Producer(sharedResource);
        Consumer consumer = new Consumer(sharedResource);

        producer.start();
        consumer.start();
    }
}

