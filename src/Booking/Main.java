package Booking;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar los alojamientos
        ReservationSystem.initializeAccommodations();

        // Crear un scanner para la interacción por consola
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Sistema de Reservas ---");
            System.out.println("1. Buscar alojamientos");
            System.out.println("2. Confirmar habitaciones disponibles");
            System.out.println("3. Realizar una reserva");
            System.out.println("4. Actualizar una reserva");
            System.out.println("5. Mostrar actividades (Día de Sol)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    buscarAlojamientos(scanner);
                    break;
                case 2:
                    confirmarHabitaciones(scanner);
                    break;
                case 3:
                    // Realizar una reserva: Este es el método principal para crear una nueva reserva
                    ReservationSystem.makeReservation();
                    break;
                case 4:
                    actualizarReserva(scanner);
                    break;
                case 5:
                    mostrarActividades(scanner);
                    break;
                case 6:
                    System.out.println("¡Gracias por usar el sistema de reservas!");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void buscarAlojamientos(Scanner scanner) {
        try {
            System.out.println("Ingrese la ciudad donde desea alojarse: ");
            String city = scanner.nextLine();

            System.out.println("Ingrese el tipo de alojamiento (HOTEL, APARTAMENTO, FINCA, DIA_DE_SOL): ");
            String type = scanner.nextLine().toUpperCase();

            System.out.println("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
            Date start = DateUtils.parseDate(scanner.nextLine()); // Usamos DateUtils.parseDate()

            System.out.println("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
            Date end = DateUtils.parseDate(scanner.nextLine()); // Usamos DateUtils.parseDate()

            System.out.println("Ingrese el número de adultos: ");
            int adults = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el número de niños: ");
            int children = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el número de habitaciones que necesita: ");
            int rooms = Integer.parseInt(scanner.nextLine());

            var results = ReservationSystem.searchAccommodation(city, type, start, end, adults, children, rooms);

            if (results.isEmpty()) {
                System.out.println("No se encontraron alojamientos disponibles con esos criterios.");
            } else {
                System.out.println("Alojamientos disponibles:");
                results.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar alojamientos: " + e.getMessage());
        }
    }

    private static void confirmarHabitaciones(Scanner scanner) {
        try {
            System.out.println("Ingrese el nombre del hotel: ");
            String hotelName = scanner.nextLine();

            System.out.println("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
            Date start = DateUtils.parseDate(scanner.nextLine()); // Usamos DateUtils.parseDate()

            System.out.println("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
            Date end = DateUtils.parseDate(scanner.nextLine()); // Usamos DateUtils.parseDate()

            System.out.println("Ingrese el número de habitaciones: ");
            int rooms = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el número de adultos: ");
            int adults = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el número de niños: ");
            int children = Integer.parseInt(scanner.nextLine());

            ReservationSystem.confirmRooms(hotelName, start, end, rooms, adults, children);
        } catch (Exception e) {
            System.out.println("Error al confirmar habitaciones: " + e.getMessage());
        }
    }

    private static void actualizarReserva(Scanner scanner) {
        try {
            System.out.println("Ingrese el correo electrónico asociado a la reserva: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese la fecha de nacimiento (formato: yyyy-MM-dd): ");
            Date birthDate = DateUtils.parseDate(scanner.nextLine()); // Usamos DateUtils.parseDate()

            ReservationSystem.updateBooking(email, birthDate);
        } catch (Exception e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    private static void mostrarActividades(Scanner scanner) {
        System.out.println("Ingrese el tipo de alojamiento para ver actividades (DIA_DE_SOL): ");
        String type = scanner.nextLine().toUpperCase();
        if (type.equalsIgnoreCase("DIA_DE_SOL")) {
            ReservationSystem.showActivities(type);
        } else {
            System.out.println("No se encontraron actividades disponibles para el tipo de alojamiento ingresado.");
        }
    }
}
