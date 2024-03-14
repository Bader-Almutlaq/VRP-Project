import java.util.List;
import java.util.LinkedList;

public class Backtrack {

    public static void backtrack(LinkedList<Vehicle> vehicles, LinkedList<Customer> customers, double[][] distanceMatrix) {
        backtrack();
    }

    private static void backtrack(){

    }


    public static double[][] generateDistanceMatrix(List<Customer> customers) {
        int size = customers.size();
        double matrix[][] = new double[size][size];
        for (int i = 0; i < size; i++) {
            Customer c1 = customers.get(i);
            for (int j = 0; j < size; j++) {
                Customer c2 = customers.get(j);
                double distance = Math.sqrt(
                        Math.pow(c2.xCoordinate - c1.xCoordinate, 2) + Math.pow(c2.yCoordinate - c1.yCoordinate, 2));
                matrix[i][j] = roundToPrecision(distance, 5); // Round distance to 5 decimal places
            }
        }
        return matrix;
    }

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
