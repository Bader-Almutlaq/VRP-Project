import java.util.LinkedList;

public class Vehicle {
    public int capacity;
    public LinkedList<Customer> route;


    public Vehicle(int cap){
        this.capacity = cap;
        route = new LinkedList<>();
    }
}
