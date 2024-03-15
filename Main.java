import java.util.LinkedList;

public class Main {
    public static final int NUMBER_OF_CUSTOMERS = 16;
    public static final int NUMBER_OF_VEHICLES = 3;

    public static void main(String args[]) {

        LinkedList<Customer> customers = new LinkedList<>();

        customers.add(new Customer(0, 0));
        customers.add(new Customer(-2, 4));
        customers.add(new Customer(4, 4));
        customers.add(new Customer(-4, 3));
        customers.add(new Customer(-3, 3));
        customers.add(new Customer(1, 2));
        customers.add(new Customer(3, 2));
        customers.add(new Customer(-1, 1));
        customers.add(new Customer(2, 1));
        customers.add(new Customer(1, -1));
        customers.add(new Customer(4, -1));
        customers.add(new Customer(-3, -2));
        customers.add(new Customer(-2, -2));
        customers.add(new Customer(-1, -3));
        customers.add(new Customer(2, -3));
        customers.add(new Customer(-4, -4));
        customers.add(new Customer(3, -4));

        // To generate points randomly
        // for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
        // customers.add(new Customer(random.nextInt(20), random.nextInt(20)));
        // }

        LinkedList<Vehicle> vehicles = new LinkedList<>();

        for (int i = 0; i < NUMBER_OF_VEHICLES; i++) {
            vehicles.add(new Vehicle(100));
            vehicles.get(i).route.add(0); // Starting with the depot
        }

        double distanceMatrix[][] = Backtrack.generateDistanceMatrix(customers);
        // Backtrack.printDistanceMatrix(distanceMatrix);

        Backtrack.backtrack(vehicles, distanceMatrix);

        for (int i = 0; i < NUMBER_OF_VEHICLES; i++) {
            System.out.println("Vehicle" + i + 1 + "route: " + vehicles.get(i).route);
        }
    }

}