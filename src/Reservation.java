import java.time.LocalDate;

public class Reservation {
    private String reservationId;
    private String roomNumber;
    private String username;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(String reservationId, String roomNumber, String username, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.username = username;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "reservationId='" + reservationId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", username='" + username + '\'' +
                ", checkInDate = " + checkInDate +
                ", checkOutDate = " + checkOutDate +
                '}';
    }
}
