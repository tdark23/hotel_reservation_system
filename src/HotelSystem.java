import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import  java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HotelSystem {
    private List<User> users;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private Scanner scanner;

    public HotelSystem() {
        users = new ArrayList<>();
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please, try again.");
            }
        }
    }

    public void register(){
        System.out.println("Enter your username : ");
        String username = scanner.nextLine();
        System.out.println("Enter your password : ");
        String password = scanner.nextLine();
        System.out.println("Enter your name : ");
        String name = scanner.nextLine();
        System.out.println("Enter your email : ");
        String email = scanner.nextLine();

        users.add(new User(name,email,username,password));
        System.out.println("Registration Successful");

    }

    public void login() {
        System.out.println("Enter your username : ");
        String username = scanner.nextLine();
        System.out.println("Enter your password : ");
        String password = scanner.nextLine();

        User user = authenticate(username, password);
        if ( user != null ) {
            System.out.println("Login Successfully");
            manageSystem();
        } else {
            System.out.println("Invalid credentials, try again.");
        }


    }

    public User authenticate(String username, String password){
        for (User user : users){
            if (user.getUsernane().equals(username) && user.getUsernane().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void manageSystem(){
        while (true) {
            System.out.println("1. Manage Rooms");
            System.out.println("2. Manage Reservations");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageRooms();
                    break;
                case 2:
                    manageReservations();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
    
    private void manageRooms(){
        System.out.println("1. Add Room");
        System.out.println("2. Update Room");
        System.out.println("3. Delete Room");
        System.out.println("4. View Rooms");
        System.out.println("5. Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

         switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    updateRoom();
                    break;
                case 3:
                    deleteRoom();
                    break;
                case 4:
                    viewRooms();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
         }
    }


    private Room findRoom(String roomNumber){
        for (Room room : rooms){
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    private User findUser(String username){
        for (User user : users) {
            if (user.getUsernane().equals(username)){
                return user;
            }
        }
        return null;
    }

    private Reservation findReservation(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)){
                return reservation;
            }
        }
        return null;
    }

    private void addRoom(){
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Enter room type (e.g., Single, Double, Suite): ");
        String type = scanner.nextLine();
        System.out.print("Enter room price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        rooms.add(new Room(roomNumber, type, price));
        System.out.println("Room added successfully.");
    }

    private void updateRoom(){
        System.out.println("Enter the room number to update");
        String roomNumber = scanner.nextLine();

        Room room = findRoom(roomNumber);

        if (room != null) {
           System.out.println("Enter new type. (Current : " + room.getType() + ") : " );
           String newType = scanner.nextLine();
           System.out.println("Enter new price. (Current : " + room.getPrice() + ") : " );
           double newPrice = scanner.nextDouble();

           room.setType(newType);
           room.setPrice(newPrice);

           System.out.println("Room updated successfully.");
        } else {
            System.out.println("Room not found");
        }
    }

    private void deleteRoom() {
        System.out.println("Enter the room number to delete");
        String roomNumber = scanner.nextLine();

        Room room = findRoom(roomNumber);

        if (room != null) {
            rooms.remove(room);
            System.out.println("Room deleted successfully.");
        } else {
            System.out.println("Room not found");
        }
    }

    private void viewRooms() {
        if (rooms.isEmpty()){
            System.out.println("No rooms available");
        } else {
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
    }

    private  void manageReservations() {
        while (true) {
            System.out.println("1. Make Reservation");
            System.out.println("2. Update Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    updateReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    viewReservations();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public void makeReservation(){
        System.out.println("Enter room number");
        String roomNumber = scanner.nextLine();

        Room room = findRoom(roomNumber);

        if ( room != null && room.isAvailable()) {
            System.out.println("Enter your username");
            String username = scanner.nextLine();

            User user = findUser(username);

            if (user != null) {
                LocalDate checkInDate = getValidDate("Enter check-in date (yyyy-mm-dd): ");
                LocalDate checkOutDate = getValidDate("Enter check-out date (yyyy-mm-dd): ");

                if (checkOutDate.isAfter(checkInDate)) {
                    String reservationId = UUID.randomUUID().toString();
                    reservations.add(new Reservation(reservationId, roomNumber, username, checkInDate, checkOutDate));
                    room.setAvailable(false);
                    System.out.println("Reservation made successfully.");
                } else {
                    System.out.println("Invalid dates. Check-out date must be after check-in date.");
                }
            } else {
                System.out.println("User not found.");
            }
        } else {
            System.out.println("Room not available or not found");
        }

    }

    public void updateReservation(){
        System.out.println("Enter the reservation id : ");
        String reservationId = scanner.nextLine();

        Reservation reservation = findReservation(reservationId);

        if (reservation != null) {
            LocalDate checkInDate = getValidDate("Enter new check-in date (current: " + reservation.getCheckInDate() + "): ");
            LocalDate checkOutDate = getValidDate("Enter new check-out date (current: " + reservation.getCheckOutDate() + "): ");

            if (checkOutDate.isAfter(checkInDate)) {
                reservation = new Reservation(reservation.getReservationId(), reservation.getRoomNumber(), reservation.getUsername(), checkInDate, checkOutDate);
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Invalid dates. Check-out date must be after check-in date.");
            }
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void cancelReservation() {
        System.out.println("Enter the reservation ID to cancel : ");
        String reservationId = scanner.nextLine();

        Reservation reservation = findReservation(reservationId);
        if ( reservation != null) {
            String roomNumber = reservation.getRoomNumber();
            reservations.remove(reservation);
            Room room = findRoom(roomNumber);
            assert room != null; // since room will never be null
            room.setAvailable(true);

            System.out.println("Reservation canceled successfully.");

        } else {
            System.out.println("Reservation not found.");
        }

    }

    private void viewReservations() {
      if (reservations.isEmpty()) {
          System.out.println("No reservations found.");
      } else {
          for (Reservation reservation : reservations) {
              System.out.println(reservation);
          }
      }
    }
    
    private LocalDate getValidDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine();
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
            }
        }
    }
}
