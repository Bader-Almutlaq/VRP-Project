import java.util.LinkedList;

public class Vehicle {
    public int capacity;
    public LinkedList<Integer> route;


    public Vehicle(int cap){
        this.capacity = cap;
        route = new LinkedList<>();
    }
}
