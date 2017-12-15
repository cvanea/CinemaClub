package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import cinemaclub.database.UserRepository;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import exceptions.SeatAlreadyTakenException;
import exceptions.SeatNotFoundException;

/**
 * Cinema system which validates and books showings for users.
 */
class BookingSystem {

    private UserRepository userRepository;
    private ScreenRepository screenRepository;

    /**
     * Gets the singleton database instance relevant to the booking system.
     */
    BookingSystem() {
        this.userRepository = DataBase.getUserRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    /**
     * Attempts to book a showing of a film for a particular user.
     * Since only users can book films, the user is first cast to a customer.
     * The seat is then checked to be available.
     * Should that pass, the booking is added to the customer, the seat is set to taken on the showing,
     * and the database is updated.
     *
     * @param user user who makes the booking
     * @param showing showing for the booking
     * @param seatRow the seat row being booked
     * @param seatNumber the seat number being booked
     * @throws SeatAlreadyTakenException prevents a seat being book which is already booked
     * @throws SeatNotFoundException prevents a seat not on the screen from being booked
     */
    void bookFilm(User user, Showing showing, String seatRow, int seatNumber)
        throws SeatAlreadyTakenException, SeatNotFoundException {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;

            validateSeatAvailability(showing, seatRow, seatNumber);

            customer.addBooking(new Booking(showing, seatRow + seatNumber));

            showing.bookSeat(customer.getUsername(), seatRow, seatNumber);

            userRepository.updateDB();
            screenRepository.updateDB();
        }
    }

    /**
     * Validates the seat by checking the seats already taken on the showing.
     *
     * @param showing showing trying to be booked
     * @param seatRow seat row to be validated
     * @param seatNumber seat number to be validated
     * @throws SeatAlreadyTakenException thrown exception
     * @throws SeatNotFoundException exception thrown by showing
     */
    private void validateSeatAvailability(Showing showing, String seatRow, int seatNumber) throws SeatAlreadyTakenException, SeatNotFoundException {
        if (showing.isSeatTaken(seatRow, seatNumber)) {
            throw new SeatAlreadyTakenException();
        }
    }

}
