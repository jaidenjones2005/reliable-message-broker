import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Broker broker = new Broker();

        boolean running = true;

        while (running) {

            System.out.println("\n=== Reliable Message Broker ===");
            System.out.println("1. Enqueue New Message");
            System.out.println("2. Process Current Batch");
            System.out.println("3. View and Clear DLQ");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter Message ID: ");
                    String messageId = scanner.nextLine();

                    System.out.print("Enter Payload: ");
                    String payload = scanner.nextLine();

                    int successChance;

                    while (true) {
                        try {
                            System.out.print(
                                    "Enter Success Chance (0-100): "
                            );

                            successChance =
                                    Integer.parseInt(scanner.nextLine());

                            if (successChance >= 0 &&
                                    successChance <= 100) {
                                break;
                            }

                            System.out.println(
                                    "Success chance must be between 0 and 100."
                            );

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Please enter a valid number."
                            );
                        }
                    }

                    Message message = new Message(
                            messageId,
                            payload,
                            successChance
                    );

                    broker.addMessage(message);
                    break;

                case "2":
                    broker.processBatch();
                    break;

                case "3":
                    broker.viewAndClearDLQ();
                    break;

                case "4":
                    running = false;
                    System.out.println("Exiting broker...");
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1-4."
                    );
            }
        }

        scanner.close();
    }
}4