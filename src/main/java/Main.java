import elevator.ElevatorSystem;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1 - pickup()");
        System.out.println("2 - step()");
        System.out.println("3 - status()");
        System.out.println("4 - update()");
        System.out.println("5 - exit()");
        System.out.println("What do you want to do?");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args.length == 0) {
            System.out.println("You didn't pass any argument to the program. Pass number of elevators");
            System.exit(1);
        } else if (args.length > 1) {
            System.out.println("You passed too many arguments to the program. Pass number of elevators");
            System.exit(1);
        }

        int howManyElevators;

        try {
            howManyElevators = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("You must pass number");
            System.exit(5);
        }

        ElevatorSystem system = new ElevatorSystem(Integer.parseInt(args[0]));

        int input;

        do {
            printMenu();
            input = scanner.nextInt();
            if (input > 5 || input < 1) throw new IllegalArgumentException("Pass value between 1 and 4!");
            switch (input) {
                case 1:
                    System.out.println("Pass current floor.");
                    int currentFloor = scanner.nextInt();
                    scanner.nextLine();
                    system.pickup(currentFloor);
                    break;
                case 2:
                    system.step();
                    break;
                case 3:
                    ArrayList<Triplet<Integer, Integer, Integer>> status = system.status();
                    status.forEach((trip) -> System.out.println("Elevator ID: " + trip.getValue0() +
                            ", current floor: " + trip.getValue1() + ", next floor: " + trip.getValue2() + "."));
                    break;
                case 4:
                    System.out.println("Pass elevator id.");
                    int elId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Pass floor.");
                    int floor = scanner.nextInt();
                    scanner.nextLine();
                    system.update(elId, floor);
                    break;
                default:
                    break;
            }
        } while(input != 5);

    }

}