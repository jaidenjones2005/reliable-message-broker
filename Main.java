public class Main {

    public static void main(String[] args) {

        QueueInterface<Message> queue = new LinkedQueue<>();

        try {
            // Enqueue three messages
            queue.enqueue(new Message("MSG-001", "First message"));
            queue.enqueue(new Message("MSG-002", "Second message"));
            queue.enqueue(new Message("MSG-003", "Third message"));

            System.out.println("Messages added to queue.");
            System.out.println();

            // Dequeue messages to verify FIFO order
            System.out.println("Dequeuing messages:");

            while (!queue.isEmpty()) {
                Message message = queue.dequeue();
                System.out.println(message);
            }

        } catch (QueueOverflowException | QueueUnderflowException e) {
            System.out.println("Queue error: " + e.getMessage());
        }
    }
}