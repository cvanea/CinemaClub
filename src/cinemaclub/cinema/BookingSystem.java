package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import cinemaclub.database.UserRepository;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import exceptions.SeatAlreadyTakenException;
import exceptions.SeatNotFoundException;

class BookingSystem {

    private UserRepository userRepository;
    private ScreenRepository screenRepository;

    BookingSystem() {
        this.userRepository = DataBase.getUserRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

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

    private void validateSeatAvailability(Showing showing, String seatRow, int seatNumber) throws SeatAlreadyTakenException, SeatNotFoundException {
        if (showing.isSeatTaken(seatRow, seatNumber)) {
            throw new SeatAlreadyTakenException();
        }
    }

}
