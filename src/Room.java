public class Room {
    private String roomNumber;
    private String type;
    private double price;
    private boolean isAvailable;

    public Room(String roomNumber, String type, double price){
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true; // Rooms are available when created;
    }

    public double getPrice() {
        return price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
