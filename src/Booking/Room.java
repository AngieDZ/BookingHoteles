package Booking;

public class Room {
    private String type;
    private String description;
    private double price;
    private int capacity;
    private int availableQuantity;

    public Room(String type, String description, double price, int capacity, int availableQuantity) {
        this.type = type;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.availableQuantity = availableQuantity;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public  String toString(){
        return  " Tipo: " + type + " | Descripción: " + description + " | Precio: " + price + " | Disponible: " + availableQuantity;
    }
}
