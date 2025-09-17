import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Container {
    private String id;
    private String description;
    private String weight;

    public Container(String id, String description, String weight) {
        this.id = id;
        this.description = description;
        this.weight = weight;
    }

    public String toString() {
        return id + " | " + description + " | " + weight;
    }
}

class Ship {
    private String name;
    private String captain;

    public Ship(String name, String captain) {
        this.name = name;
        this.captain = captain;
    }

    public String toString() {
        return name + " | Capt. " + captain;
    }
}

public class PortManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Container> containerStack = new ArrayDeque<>(); // LIFO
        Deque<Ship> shipQueue = new ArrayDeque<>();           // FIFO

        while (true) {
            System.out.println("\n=== Port Container Management System ===");
            System.out.println("[1] Store container (push)");
            System.out.println("[2] View port containers");
            System.out.println("[3] Register arriving ship (enqueue)");
            System.out.println("[4] View waiting ships");
            System.out.println("[5] Load next ship (pop container + poll ship)");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": 
                    System.out.print("Enter Container ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter Weight (e.g., 200kg): ");
                    String weight = sc.nextLine();
                    containerStack.push(new Container(id, desc, weight));
                    System.out.println("\nStored: " + containerStack.peek());
                    break;

                case "2": 
                    if (containerStack.isEmpty()) {
                        System.out.println("\nNo containers stored.");
                    } else {
                        System.out.println("\nTOP →");
                        containerStack.forEach(c -> System.out.println(c));
                        System.out.println("← BOTTOM");
                    }
                    break;

                case "3": 
                    System.out.print("Enter Ship Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Captain Name: ");
                    String captain = sc.nextLine();
                    shipQueue.offer(new Ship(name, captain));
                    System.out.println("\nRegistered: " + shipQueue.getLast());
                    break;

                case "4": 
                    if (shipQueue.isEmpty()) {
                        System.out.println("\nNo ships waiting.");
                    } else {
                        System.out.println("\nFRONT →");
                        shipQueue.forEach(s -> System.out.println(s));
                        System.out.println("← REAR");
                    }
                    break;

                case "5": 
                    if (containerStack.isEmpty() || shipQueue.isEmpty()) {
                        System.out.println("\nCannot load. Need at least 1 container and 1 ship.");
                    } else {
                        Container c = containerStack.pop();
                        Ship s = shipQueue.poll();
                        System.out.println("\nLoaded: " + c + " → " + s);
                        System.out.println("Remaining containers: " + containerStack.size());
                        System.out.println("Remaining ships waiting: " + shipQueue.size());
                    }
                    break;

                case "0": 
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
