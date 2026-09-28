import java.util.Random;

public class Broker {

    private static final int MAX_RETRIES = 3;

    private QueueInterface<Message> queue;
    private QueueInterface<Message> deadLetterQueue;
    private Random random;

    public Broker() {
        queue = new LinkedQueue<>();
        deadLetterQueue = new LinkedQueue<>();
        random = new Random();
    }

    public void addMessage(Message message) {
        try {
            queue.enqueue(message);
            System.out.println("ENQUEUED: " + message);
        } catch (QueueOverflowException e) {
            System.out.println("Could not add message: " + e.getMessage());
        }
    }

    public void processBatch() {

        System.out.println("\nProcessing messages...");

        while (!queue.isEmpty()) {

            try {
                Message message = queue.dequeue();

                int randomNumber = random.nextInt(100);

                if (randomNumber < message.getSuccessChance()) {

                    System.out.println("SUCCESS: " + message);

                } else {

                    message.incrementRetryCount();

                    if (message.getRetryCount() >= MAX_RETRIES) {

                        deadLetterQueue.enqueue(message);

                        System.out.println(
                                "MOVED TO DLQ: " + message
                        );

                    } else {

                        queue.enqueue(message);

                        System.out.println(
                                "FAILED - Re-enqueued: " + message
                        );
                    }
                }

            } catch (QueueUnderflowException |
                     QueueOverflowException e) {

                System.out.println(
                        "Queue error: " + e.getMessage()
                );
            }
        }
    }

    public void viewAndClearDLQ() {

        System.out.println("\n--- Dead-Letter Queue ---");

        if (deadLetterQueue.isEmpty()) {
            System.out.println("DLQ is empty.");
            return;
        }

        while (!deadLetterQueue.isEmpty()) {

            try {
                Message message = deadLetterQueue.dequeue();
                System.out.println(message);

            } catch (QueueUnderflowException e) {
                System.out.println(
                        "Queue error: " + e.getMessage()
                );
            }
        }

        System.out.println("DLQ has been cleared.");
    }

    public int getQueueSize() {
        return queue.size();
    }
}