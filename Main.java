public class Main {

    public static void main(String[] args) {

        Broker broker = new Broker();

        // Messages with different chances of succeeding
        broker.addMessage(
                new Message("MSG-001", "Process payment", 90)
        );

        broker.addMessage(
                new Message("MSG-002", "Send email", 50)
        );

        broker.addMessage(
                new Message("MSG-003", "Update database", 20)
        );

        broker.addMessage(
                new Message("MSG-004", "Poison message", 0)
        );

        System.out.println("Starting queue size: "
                + broker.getQueueSize());

        broker.processBatch();

        System.out.println("\nMessages remaining after batch: "
                + broker.getQueueSize());
    }
}