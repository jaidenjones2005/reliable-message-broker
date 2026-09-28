import java.util.Random;

public class Broker {

    private QueueInterface<Message> queue;
    private Random random;

    public Broker() {
        queue = new LinkedQueue<>();
        random = new Random();
    }

    public void addMessage(Message message) {
        try {
            queue.enqueue(message);
        } catch (QueueOverflowException e) {
            System.out.println("Could not add message: " + e.getMessage());
        }
    }

    public void processBatch() {
        int batchSize = queue.size();

        System.out.println("\nProcessing batch of " + batchSize + " messages...");

        for (int i = 0; i < batchSize; i++) {
            try {
                Message message = queue.dequeue();

                int randomNumber = random.nextInt(100);

                if (randomNumber < message.getSuccessChance()) {
                    System.out.println("SUCCESS: " + message);
                } else {
                    message.incrementRetryCount();
                    queue.enqueue(message);

                    System.out.println("FAILED - Re-enqueued: " + message);
                }

            } catch (QueueUnderflowException | QueueOverflowException e) {
                System.out.println("Queue error: " + e.getMessage());
            }
        }
    }

    public int getQueueSize() {
        return queue.size();
    }
}