import java.util.ArrayList;
import java.util.List;

public class VRP_CSP_Backtracking {

    static final int[][] distanceMatrix = {
            {0, 10, 20, 5, 30, 15, 25},    // Depot (0) to locations (1, 2, 3, 4, 5, 6)
            {10, 0, 15, 20, 25, 30, 35},   // Location 1 to other locations
            {20, 15, 0, 10, 25, 30, 20},   // Location 2 to other locations
            {5, 20, 10, 0, 15, 25, 30},    // Location 3 to other locations
            {30, 25, 25, 15, 0, 20, 10},   // Location 4 to other locations
            {15, 30, 30, 25, 20, 0, 15},   // Location 5 to other locations
            {25, 35, 20, 30, 10, 15, 0}    // Location 6 to other locations
    };

    static final int NUM_VEHICLES = 2;

    public static void main(String[] args) {
        List<List<Integer>> routes = solveVRP();
        if (routes != null) {
            System.out.println("Optimal Routes:");
            for (int i = 0; i < routes.size(); i++) {
                System.out.println("Vehicle " + (i + 1) + ": " + routes.get(i));
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    public static List<List<Integer>> solveVRP() {
        List<List<Integer>> routes = new ArrayList<>();
        for (int i = 0; i < NUM_VEHICLES; i++) {
            routes.add(new ArrayList<>());
        }
        if (backtrack(0, routes)) {
            for (List<Integer> route : routes) {
                route.add(0); // Add depot as the ending location for each vehicle
            }
            return routes;
        }
        return null;
    }

    public static boolean backtrack(int location, List<List<Integer>> routes) {
        if (location == distanceMatrix.length) {
            return true;
        }
        for (int i = 0; i < NUM_VEHICLES; i++) {
            List<Integer> route = routes.get(i);
            if (isValidMove(location, route)) {
                route.add(location);
                if (backtrack(location + 1, routes)) {
                    return true;
                }
                route.remove(route.size() - 1);
            }
        }
        return false;
    }

    public static boolean isValidMove(int location, List<Integer> route) {
        if (route.isEmpty() || route.get(route.size() - 1) == 0) {
            return distanceMatrix[0][location] > 0; // Ensure the first location in the route starts from the depot
        }
        int lastLocation = route.get(route.size() - 1);
        return distanceMatrix[lastLocation][location] > 0;
    }
}
