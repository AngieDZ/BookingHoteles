package Booking;
import java.util.*;

public class Accommodation {

    private String name;
    private String city;
    private AccommodationType type;
    private int qualification;
    private double pricePerNight;
    private List<Room> rooms;

    public Accommodation(String name, String city, AccommodationType type, int qualification, double pricePerNight) {
        this.name = name;
        this.city = city;
        this.type = type;
        this.qualification = qualification;
        this.pricePerNight = pricePerNight;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public AccommodationType getType() {
        return type;
    }

    public int getQualification() {
        return qualification;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public  String toString(){
        return "Hotel: " + name +  " | Ciudad: " + city + " | Tipo: " + type + " | Calificación: " + qualification + " | Precio por noche: " + pricePerNight;
    }


}
