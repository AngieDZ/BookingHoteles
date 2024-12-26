package Booking;
import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ReservationSystem {
    public static List<Accommodation> accommodations = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    public static void initializeAccommodations() {
        // Crear habitaciones de ejemplo
        Room room1 = new Room("Doble", "2 camas dobles, vista al mar", 150.000, 2, 10);
        Room room2 = new Room("Suite", "Suite con cama king size, jacuzzi", 250.000, 2, 5);
        Room room3 = new Room("Cabana", "Cabaña rústica, vista al campo", 120.000, 2, 3);
        Room room4 = new Room("Doble Vista Mar", "2 camas dobles, vista al mar, aire acondicionado", 200.000, 2, 10);
        Room room5 = new Room("Suite Familiar", "Suite grande con 2 camas king size, capacidad hasta 4 personas", 350.000, 4, 4);
        Room room6 = new Room("Estudio", "Estudio con cama queen, cocina pequeña", 100.000, 2, 8);
        Room room7 = new Room("Cabaña Rústica", "Cabaña con vista a la naturaleza, sin aire acondicionado", 90.000, 2, 6);
        Room room8 = new Room("Suite Luna", "Suite con vista al mar, cama king, jacuzzi", 350.000, 3, 4);

        // Crear alojamientos
        Accommodation hotelPlaya = new Accommodation("Hotel Playa", "Cancún", AccommodationType.HOTEL, 4, 200.000);
        hotelPlaya.getRooms().add(room1);
        hotelPlaya.getRooms().add(room2);

        Accommodation apartmentMar = new Accommodation("Apartamento Mar", "Cancún", AccommodationType.APARTAMENTO, 5, 100.000);
        apartmentMar.getRooms().add(room3);

        Accommodation fincaSierra = new Accommodation("Finca La Sierra", "Zacatlán", AccommodationType.FINCA, 3, 80.000);
        fincaSierra.getRooms().add(room1);

        Accommodation dayOfSun = new Accommodation("Día de Sol", "Playa del Carmen", AccommodationType.DIA_SOL, 5, 150.000);

        Accommodation hotelGranVista = new Accommodation("Hotel Gran Vista", "Tulum", AccommodationType.HOTEL, 5, 300.000);
        hotelGranVista.getRooms().add(room4);
        hotelGranVista.getRooms().add(room5);

        Accommodation apartmentLuna = new Accommodation("Apartamento Luna", "Playa del Carmen", AccommodationType.APARTAMENTO, 4, 120.000);
        apartmentLuna.getRooms().add(room6);

        Accommodation fincaEncanto = new Accommodation("Finca El Encanto", "Puebla", AccommodationType.FINCA, 3, 80.000);
        fincaEncanto.getRooms().add(room7);

        Accommodation hotelParaisoDelMar = new Accommodation("Hotel Paraíso del Mar", "Cozumel", AccommodationType.HOTEL, 4, 250.000);
        hotelParaisoDelMar.getRooms().add(room4);
        hotelParaisoDelMar.getRooms().add(room8);

        Accommodation villaSol = new Accommodation("Villa Sol", "Cabo San Lucas", AccommodationType.DIA_SOL, 5, 500.000);

        // Agregar los alojamientos a la lista global
        ReservationSystem.accommodations.add(hotelPlaya);
        ReservationSystem.accommodations.add(apartmentMar);
        ReservationSystem.accommodations.add(fincaSierra);
        ReservationSystem.accommodations.add(dayOfSun);
        ReservationSystem.accommodations.add(hotelGranVista);
        ReservationSystem.accommodations.add(apartmentLuna);
        ReservationSystem.accommodations.add(fincaEncanto);
        ReservationSystem.accommodations.add(hotelParaisoDelMar);
        ReservationSystem.accommodations.add(villaSol);
    }

    // gestiona el flujo de la reserva
    public static void makeReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre: ");
        String name = scanner.nextLine();

        System.out.println("Ingrese su apellido: ");
        String lastName = scanner.nextLine();

        System.out.println("Ingrese su correo electrónico: ");
        String email = scanner.nextLine();

        System.out.println("Ingrese su nacionalidad: ");
        String nationality = scanner.nextLine();

        System.out.println("Ingrese su número de teléfono: ");
        String phone = scanner.nextLine();

        System.out.println("Ingrese su hora aproximada de llegada: ");
        String arrivalTime = scanner.nextLine();

        System.out.println("Ingrese su fecha de nacimiento (formato: yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();
        Date birthDate = DateUtils.parseDate(birthDateStr);

        System.out.println("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        Date startDate = DateUtils.parseDate(startDateStr);

        System.out.println("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();
        Date endDate = DateUtils.parseDate(endDateStr);

        System.out.println("Ingrese la ciudad donde desea alojarse: ");
        String city = scanner.nextLine();

        System.out.println("Ingrese el tipo de alojamiento (HOTEL, APARTAMENTO, FINCA, DIA_DE_SOL): ");
        String type = scanner.nextLine().toUpperCase();

        // Si el tipo es 'DIA_DE_SOL'
        if (type.equalsIgnoreCase("DIA_DE_SOL")) {
            showActivities("Dia de Sol");
            System.out.println("Gracias por seleccionar 'Día de Sol'. Procediendo con la reserva.");

            reserve(name, lastName, email, nationality, phone, arrivalTime, startDate, endDate, "Día de Sol", "", 0, birthDate, 0, 0);
        } else {
            System.out.println("Ingrese el número de habitaciones que desea: ");
            int roomsRequired = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el número de adultos: ");
            int adults = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el número de niños: ");
            int children = Integer.parseInt(scanner.nextLine());

            List<Accommodation> availableAccommodations = searchAccommodation(city, type, startDate, endDate, adults, children, roomsRequired);

            if (availableAccommodations.isEmpty()) {
                System.out.println("No se encontraron alojamientos disponibles con esos criterios.");
                return;
            }

            System.out.println("Alojamientos disponibles:");
            for (Accommodation accommodation : availableAccommodations) {
                System.out.println(accommodation);
            }

            System.out.println("Seleccione el alojamiento por nombre (exacto): ");
            String hotelName = scanner.nextLine();

            Accommodation selectedAccommodation = availableAccommodations.stream()
                    .filter(a -> a.getName().equalsIgnoreCase(hotelName))
                    .findFirst()
                    .orElse(null);

            if (selectedAccommodation == null) {
                System.out.println("Alojamiento no encontrado.");
                return;
            }
            confirmRooms(hotelName, startDate, endDate, roomsRequired, adults, children);

            System.out.println("Ingrese el tipo de habitación que desea reservar (por ejemplo, Doble, Suite): ");
            String roomType = scanner.nextLine();

            reserve(name, lastName, email, nationality, phone, arrivalTime, startDate, endDate, hotelName, roomType, roomsRequired, birthDate, adults, children);
        }
    }

    //Filtra por ciudad, tipo de alojamiento, fechas, adultos, niños y habitaciones
    public static List<Accommodation> searchAccommodation(String city, String type, Date start, Date end, int adults, int children, int roomsRequired) {
        List<Accommodation> results = new ArrayList<>();
        System.out.println("Buscando alojamientos en " + city + " del tipo " + type + " entre " + start + " y " + end);

        for (Accommodation accommodation : accommodations) {
            System.out.println("Comprobando alojamiento: " + accommodation.getName());
            if (accommodation.getCity().equalsIgnoreCase(city) && accommodation.getType().name().equalsIgnoreCase(type)) {
                for (Room room : accommodation.getRooms()) {
                    System.out.println("Comprobando habitación: " + room.getType());
                    if (isRoomAvailable(room, accommodation, start, end, adults, children, roomsRequired)) {
                        System.out.println("Habitación disponible: " + room.getType());
                        results.add(accommodation);
                        break;
                    }
                }
            }
        }
        return results;
    }

    //Lista las habitaciones disponibles para un alojamiento en las fechas dadas.
    public static void confirmRooms(String hotelName, Date start, Date end, int roomsRequired, int adults, int children) {
        if (start.after(end)) {
            System.out.println("Error: La fecha de inicio no puede ser posterior a la fecha de fin.");
            return;
        }
        if (roomsRequired <= 0) {
            System.out.println("Error: La cantidad de habitaciones debe ser al menos 1.");
            return;
        }

        Accommodation accommodation = accommodations.stream()
                .filter(a -> a.getName().equalsIgnoreCase(hotelName))
                .findFirst().orElse(null);

        if (accommodation != null) {
            if (accommodation.getType().equals(AccommodationType.DIA_SOL)) {
                System.out.println("Actividades disponibles en " + hotelName + ":");
                showActivities("Dia de Sol");
            } else {
                System.out.println("Habitaciones disponibles en: " + hotelName + ":");
                for (Room room : accommodation.getRooms()) {
                    if (isRoomAvailable(room, accommodation, start, end, adults, children, roomsRequired)) {
                        System.out.println(room);
                    }
                }
            }
        } else {
            System.out.println("Hotel no encontrado.");
        }
    }

    //Calcula el precio base y aplica ajustes de precio (recargos y descuentos).
    public static void showHotelDetails(Accommodation accommodation, Date startDate, Date endDate) {
        long days = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
        double basePrice = accommodation.getPricePerNight() * days;

        DecimalFormat df = new DecimalFormat("#.000");

        Map<String, Double> adjustments = calculatePriceAdjustment(startDate, endDate, basePrice);
        double totalAdjustment = adjustments.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalPrice = basePrice + totalAdjustment;

        System.out.println("Hotel: " + accommodation.getName());
        System.out.println("Calificación: " + accommodation.getQualification() + " estrellas");
        System.out.println("Precio por noche: $" + df.format(accommodation.getPricePerNight()));
        System.out.println("Estadía total (" + days + " noches): $" + df.format(basePrice));
        if (!adjustments.isEmpty()) {
            System.out.println("Ajustes aplicados:");
            for (Map.Entry<String, Double> entry : adjustments.entrySet()) {
                System.out.printf("- %s: $%s%n", entry.getKey(), df.format(entry.getValue()));
            }
        }
        System.out.println("Precio total: $" + df.format(totalPrice));
    }

    private static double calculateTotalPrice(Accommodation accommodation, Date start, Date end, int roomsRequired) {
        long days = ChronoUnit.DAYS.between(start.toInstant(), end.toInstant());

        // Encuentra la habitación más barata que cumpla con la cantidad de habitaciones requeridas
        Room cheapestRoom = accommodation.getRooms().stream()
                .filter(room -> room.getAvailableQuantity() >= roomsRequired)
                .min(Comparator.comparingDouble(Room::getPrice))
                .orElseThrow(() -> new RuntimeException("No hay habitaciones disponibles"));

        // Calcula el precio base
        double basePrice = cheapestRoom.getPrice() * roomsRequired * days;

        // Obtiene el desglose de ajustes usando el método calculatePriceAdjustment
        Map<String, Double> adjustments = calculatePriceAdjustment(start, end, basePrice);

        // Suma todos los ajustes para obtener el ajuste total
        double totalAdjustment = adjustments.values().stream().mapToDouble(Double::doubleValue).sum();

        // Devuelve el precio total (base + ajustes)
        return basePrice + totalAdjustment;
    }

    private static Map<String, Double> calculatePriceAdjustment(Date start, Date end, double basePrice) {
        Map<String, Double> adjustments = new HashMap<>();
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        int startDay = startCal.get(Calendar.DAY_OF_MONTH);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        int endDay = endCal.get(Calendar.DAY_OF_MONTH);

        DecimalFormat df = new DecimalFormat("#.000");

        // Aumento del 15% si los días incluyen los últimos días del mes
        if (endDay >= 25) {
            double adjustment = 0.15 * basePrice;
            adjustments.put("Recargo 15% (últimos días del mes)", Double.parseDouble(df.format(adjustment)));
        }

        // Aumento del 10% si los días incluyen el 10 al 15
        if (startDay >= 10 && startDay <= 15 || endDay >= 10 && endDay <= 15) {
            double adjustment = 0.10 * basePrice;
            adjustments.put("Recargo 10% (días del 10 al 15)", Double.parseDouble(df.format(adjustment)));
        }

        // Descuento del 8% si los días incluyen entre el 5 y el 10
        if (startDay >= 5 && startDay <= 10 || endDay >= 5 && endDay <= 10) {
            double adjustment = -0.08 * basePrice;
            adjustments.put("Descuento 8% (días del 5 al 10)", Double.parseDouble(df.format(adjustment)));
        }

        return adjustments;
    }

    public static void showActivities(String type) {
        if (type.equalsIgnoreCase("DIA_DE_SOL")) {
            System.out.println("Actividades disponibles: ");
            System.out.println("- Piscina, Spa, Juegos al aire libre.");
            System.out.println("- Incluye almuerzo y refrigerio.");
            System.out.println("Precio: $50.000 por persona.");
        } else {
            System.out.println("No hay actividades disponibles para el tipo de alojamiento: " + type);
        }
    }

    // Crea reservas y actualiza la cantidad de habitaciones disponibles.
    public static void reserve(String name, String lastName, String email, String nationality, String phone,
                               String arrivalTime, Date start, Date end, String hotelName, String roomType,
                               int roomsRequired, Date birthDate, int adults, int children) {
        try {
            Accommodation accommodation = accommodations.stream()
                    .filter(a -> a.getName().equalsIgnoreCase(hotelName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado. Por favor, verifique el nombre del hotel."));

            Room room = accommodation.getRooms().stream()
                    .filter(h -> h.getType().equalsIgnoreCase(roomType))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de habitación no encontrado. Verifique el tipo de habitación."));

            if (!isRoomAvailable(room, accommodation, start, end, adults, children, roomsRequired)) {
                throw new IllegalStateException("No hay habitaciones suficientes disponibles para las fechas seleccionadas.");
            }

            room.setAvailableQuantity(room.getAvailableQuantity() - roomsRequired);
            Booking booking = new Booking(name, lastName, email, nationality, phone, arrivalTime, start, end,
                    accommodation, room, roomsRequired, birthDate, adults, children);
            bookings.add(booking);
            System.out.println("Reserva añadida correctamente a la lista global.");

            System.out.println("Reservas actuales:");
            for (Booking b : bookings) {
                System.out.println(b);
            }

            double totalPrice = calculateTotalPrice(accommodation, start, end, roomsRequired);
            DecimalFormat df = new DecimalFormat("0.000");
            System.out.println("Reserva realizada con éxito. Detalles de la reserva:");
            System.out.println(booking);
            System.out.println("Precio total: $" + totalPrice);
            System.out.println("Gracias por reservar con nosotros. Si necesita actualizar su reserva, por favor contáctenos.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error en la reserva: " + e.getMessage());
        }
    }

    //omprueba correctamente la disponibilidad, capacidad y conflictos de fechas.
    private static boolean isRoomAvailable(Room room, Accommodation accommodation, Date start, Date end, int adults, int children, int roomsRequired) {
        if (room.getAvailableQuantity() < roomsRequired) {
            return false;
        }

        if (room.getCapacity() < (adults + children)) {
            return false;
        }

        for (Booking booking : bookings) {
            if (booking.getAccommodation().equals(accommodation) && booking.getRoom().equals(room)) {

                if (datesOverlap(start, end, booking.getStartDate(), booking.getEndDate())) {
                    return false;
                }
            }
        }

        return true;
    }

    //determina si dos períodos de tiempo se solapan.
    private static boolean datesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return (start1.before(end2) && end1.after(start2));
    }

    //Permite cambiar la habitación o el alojamiento.
    public static void updateBooking(String email, Date birthDate) {
        Scanner scanner = new Scanner(System.in);
        Date adjustedBirthDate = stripTimeFromDate(birthDate);

        System.out.println("Buscando reserva con:");
        System.out.println("Correo: " + email);
        System.out.println("Fecha de nacimiento: " + adjustedBirthDate);

        Booking booking = bookings.stream()
                .filter(b -> {
                    boolean emailMatch = b.getEmail().trim().equalsIgnoreCase(email.trim());
                    boolean dateMatch = stripTimeFromDate(b.getBirthDate()).equals(adjustedBirthDate);
                    System.out.println("Email coincide: " + emailMatch);
                    System.out.println("Fecha coincide: " + dateMatch);
                    return emailMatch && dateMatch;
                })
                .findFirst()
                .orElse(null);

        if (booking != null) {
            System.out.println("Datos actuales de la reserva: \n" + booking);
            System.out.println("¿Qué desea cambiar? (1. Habitación, 2. Alojamiento)");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Usted tiene reservada la siguiente habitación:");
                System.out.println(booking.getRoom());
                System.out.println("Desea cambiar de habitacion? (S/N)");
                String confirmation = scanner.nextLine();
                if (!confirmation.equalsIgnoreCase("S")) {
                    System.out.println("Cambio de habitación cancelado.");
                    return;
                }

                System.out.println("Habitaciones disponibles en " + booking.getAccommodation().getName() + ":");
                for (Room room : booking.getAccommodation().getRooms()) {
                    if (!room.equals(booking.getRoom()) && isRoomAvailable(room, booking.getAccommodation(),
                            booking.getStartDate(), booking.getEndDate(), booking.getAdults(), booking.getChildren(), booking.getNumberRooms())) {
                        System.out.println(room);
                    }
                }

                System.out.println("Seleccione el tipo de habitación a la que desea cambiar:");
                String newRoomType = scanner.nextLine();
                Room newRoom = booking.getAccommodation().getRooms().stream()
                        .filter(r -> r.getType().equalsIgnoreCase(newRoomType))
                        .findFirst()
                        .orElse(null);

                // Validar disponibilidad de la nueva habitación
                if (newRoom != null && isRoomAvailable(newRoom, booking.getAccommodation(),
                        booking.getStartDate(), booking.getEndDate(), booking.getAdults(), booking.getChildren(), booking.getNumberRooms())) {
                    System.out.println("¿Está seguro de cambiar a la nueva habitación? (S/N)");
                    String finalConfirmation = scanner.nextLine();
                    if (finalConfirmation.equalsIgnoreCase("S")) {
                        // Liberar la habitación actual
                        booking.getRoom().setAvailableQuantity(booking.getRoom().getAvailableQuantity() + booking.getNumberRooms());
                        // Reservar la nueva habitación
                        newRoom.setAvailableQuantity(newRoom.getAvailableQuantity() - booking.getNumberRooms());
                        booking.setRoom(newRoom);
                        System.out.println("Reserva actualizada con la nueva habitación.");
                    } else {
                        System.out.println("Cambio cancelado.");
                    }
                } else {
                    System.out.println("No hay disponibilidad para la nueva habitación seleccionada.");
                }
            } else if (option == 2) {
                System.out.println("¿Está seguro de cambiar de alojamiento? Esto eliminará la reserva actual. (S/N)");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("S")) {
                    bookings.remove(booking);
                    System.out.println("Reserva eliminada. Por favor, cree una nueva reserva.");
                } else {
                    System.out.println("Cambio cancelado.");
                }
            }
        } else {
            System.out.println("Reserva no encontrada o datos incorrectos.");
        }
    }
    private static Date stripTimeFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}