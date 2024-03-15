import java.util.List; ///
import java.util.LinkedList;

public class Backtrack {
    // A helper function to prepare the backtracking with required parameters.
    public static void backtrack(LinkedList<Vehicle> vehicles, double[][] distanceMatrix) {
        LinkedList<Integer> unassignedPoints = new LinkedList<Integer>(); // List to store the unassigned locations.
        for (int i = 1; i < distanceMatrix.length; i++) {
            unassignedPoints.add(i); // Adding all the points to the list except the depot.
        }
        LinkedList<Integer> assignedPoints = new LinkedList<Integer>(); // List to store the assigned locations.
        assignedPoints.add(0); // Adding the depot since all the vehicles start form this location.

        if (!(backtrack(vehicles, distanceMatrix, assignedPoints, unassignedPoints))) {
            System.out.println("No solution");
        }

        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).route.add(0); // Adding the depot at the end of each vehicle route.
        }
    }

    private static boolean backtrack(LinkedList<Vehicle> vehicles, double[][] distanceMatrix,
            LinkedList<Integer> assignedPoints, LinkedList<Integer> unassignedPoints) {
        // Base case: If all points are assigned, return true.
        if (assignedPoints.size() == distanceMatrix.length)
            return true;

        // Find the closest unassigned point to the depot.
        double minDistanceToDepot = Double.MAX_VALUE;
        int closestPointToDepot = -1;

        for (int point : unassignedPoints) {
            double distance = distanceMatrix[0][point];
            if (distance < minDistanceToDepot) {
                minDistanceToDepot = distance;
                closestPointToDepot = point;
            }
        }

        // If no closest point found, return false (no solution).
        if (closestPointToDepot == -1)
            return false;

        // Find the nearest vehicle to the closest point.
        double minDistanceToVehicle = Double.MAX_VALUE;
        Vehicle nearestVehicle = null;

        for (Vehicle vehicle : vehicles) {
            // System.out.println(vehicle.route);
            int lastPoint = vehicle.route.getLast();
            double distance = distanceMatrix[lastPoint][closestPointToDepot];
            if (distance < minDistanceToVehicle) {
                minDistanceToVehicle = distance;
                nearestVehicle = vehicle;
            }
        }

        // If no suitable vehicle found, return false.
        if (nearestVehicle == null)
            return false;

        // Assign the closest point to the nearest vehicle.
        nearestVehicle.route.add(closestPointToDepot);
        unassignedPoints.remove((Integer) closestPointToDepot);
        assignedPoints.add((Integer) closestPointToDepot);

        // Recursively call backtrack with the updated points.
        return backtrack(vehicles, distanceMatrix, assignedPoints, unassignedPoints);
    }

    // This function calculate the distance between all the points.
    public static double[][] generateDistanceMatrix(List<Customer> customers) {
        int size = customers.size();
        double matrix[][] = new double[size][size];
        for (int i = 0; i < size; i++) {
            Customer c1 = customers.get(i);
            for (int j = 0; j < size; j++) {
                Customer c2 = customers.get(j);
                double distance = Math.sqrt(
                        Math.pow(c2.xCoordinate - c1.xCoordinate, 2) + Math.pow(c2.yCoordinate - c1.yCoordinate, 2));
                matrix[i][j] = roundToPrecision(distance, 6); // Round distance to 5 decimal places
            }
        }
        return matrix;
    }

    // Round the values to the precision specified.
    public static double roundToPrecision(double number, int precision) {
        double scale = Math.pow(10, precision);
        return Math.round(number * scale) / scale;
    }

    public static void printDistanceMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double distance : row) {
                System.out.printf("%.2f\t", distance);
            }
            System.out.println();
        }
    }
}
