import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

class Room {

		private String category;
		private Boolean isAvailable;
		private double price;

		public Room(String category, double price) {
			this.category = category;
			this.price = price;
			this.isAvailable = true;
		}

		public String getCategory() {
			return category;
		}


		public double getPrice() {
			return price;
		}

		public boolean isAvailable() {
			return isAvailable;
		}

		public void reserve() {
			this.isAvailable = false;
		}

		public void release() {
			this.isAvailable = true;
		}
}

class Reservation {
	private String customerName;
	private Room room;
	private double payment;

	public Reservation(String customerName, Room room, double payment) {
		this.customerName = customerName;
		this.room = room;
		this.payment = payment;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Room getRoom() {
		return room;
	}

	public double getPayment() {
		return payment;
	}

}


class HotelReservation {
	private static	ArrayList<Room> rooms = new ArrayList<>();
	private static	ArrayList<Reservation> reservations = new ArrayList<>();
		
		public static void main(String[] args) {
			initializeRooms();
			Scanner scanner = new Scanner(System.in);
			
			while(true) {
				System.out.println();
				System.out.println();
				System.out.println("********* HOTEL MANAGEMENT SYSTEM *********");
				System.out.println();
				System.out.println();
				System.out.println("********* SELECT WHAT WOULD YOU LIKE TO DO: *********");
				System.out.println();
				System.out.println("1 - SEARCH FOR AVAILABLE ROOMS. ");
				System.out.println("2 - MAKE RESERVATIONS. ");
				System.out.println("3 - VIEW BOOKINGS. ");
				System.out.println("4 - EXIT. ");
				System.out.println();
				int choice = scanner.nextInt();
				scanner.nextLine();

				if (choice == 1) {
					searchAvailableRooms();		
				}
				else if (choice == 2) {
					makeReservations(scanner);
				}
				else if (choice == 3) {
					viewBookingDetails(scanner);
				}
				else if (choice == 4) {
					break;
				}
				else {
					System.out.println("INVALID CHOICE!!");
				}
			} scanner.close();
		}

		private  static void initializeRooms() {
			rooms.add(new Room("Single", 100.0));
			rooms.add(new Room("Double", 150.0));
			rooms.add(new Room("Suite", 250.0));   
			rooms.add(new Room("Single", 100.0));
			rooms.add(new Room("Double", 150.0));
			rooms.add(new Room("Suite", 250.0));
		}

		private static void searchAvailableRooms() {
			System.out.println("Avalable Rooms: ");
			for (Room room : rooms) {
				if (room.isAvailable()) {
					System.out.println("Category: " + room.getCategory() + " | Price - $" + room.getPrice());
				}
			}
		}

		private static void makeReservations(Scanner scanner) {
			System.out.print("Enter Customer Name: ");
			String customerName = scanner.nextLine();
			System.out.print("Enter Category (Single, Double, Suite): ");
			String category = scanner.nextLine();
	
			Room roomToReserve=null;
			for (Room room : rooms) { 
					if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
						 roomToReserve = room;
						break;
					}
			}

			if (roomToReserve != null) {
			System.out.print("Enter Payment Amount: ");
				double payment = scanner.nextDouble();
				scanner.nextLine();

				if (payment >= roomToReserve.getPrice()) {
					roomToReserve.reserve();

					reservations.add(new Reservation(customerName, roomToReserve, payment));
					System.out.println("RESERVATIONS SUCCESSFULL!");
				}
				else {
					System.out.println("INVALID PAYMENT AMOUNT!");
				}
			} else {
				System.out.println("NO ROOMS AVAILABLE");
			}
		}

		private static void viewBookingDetails(Scanner scanner) {
			System.out.println("Enter your name: ");
			String customerName = scanner.nextLine();
			boolean found = false;

			for (Reservation reservation : reservations) {
				if (reservation.getCustomerName().equalsIgnoreCase(customerName)) {
					System.out.println("Booking Details:");
				 	System.out.println("Customer Name: " + reservation.getCustomerName());
				 	System.out.println("Room Category: " + reservation.getRoom().getCategory());
				 	System.out.println("Payment: $" + reservation.getPayment());

				 	found = true;
				 	break;					
				}
			}
			if (!found) {
				System.out.println("NO BOOKINGS FOUND FOR THIS NAME!");
			}

		}

}