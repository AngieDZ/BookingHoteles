package Booking;
import java.util.Date;
public class Booking {
    private String name;
    private String lastName;
    private String email;
    private String nationality;
    private String phone;
    private String arrivalTime;
    private Date startDate;
    private Date endDate;
    private Accommodation accommodation;
    private Room room;
    private int numberRooms;
    private Date birthDate;
    private int adults;
    private int children;

    public Booking(String name, String lastName, String email, String nationality, String phone, String arrivalTime, Date startDate, Date endDate, Accommodation accommodation, Room room, int numberRooms, Date birthDate, int adults, int children) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.nationality = nationality;
        this.phone = phone;
        this.arrivalTime = arrivalTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accommodation = accommodation;
        this.room = room;
        this.numberRooms = numberRooms;
        this.birthDate = birthDate;
        this.adults = adults;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getNumberRooms() {
        return numberRooms;
    }

    public void setNumberRooms(int numberRooms) {
        this.numberRooms = numberRooms;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Reserva de " + name + " " + lastName + " en " + accommodation.getName() + " (" + accommodation.getCity() + ") | Habitación: " + room.getType() + " | Desde: " + startDate + " Hasta: " + endDate + " | Hora de llegada: " + arrivalTime;
    }

}
