package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.ScreenRepository;
import cinemaclub.database.UserRepository;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import exceptions.SeatAlreadyTakenException;
import exceptions.SeatNotFoundException;
import exceptions.ShowingDoesNotExistException;

class BookingSystem {

    private UserRepository userRepository;
    private ScreenRepository screenRepository;

    BookingSystem() {
        this.userRepository = DataBase.getUserRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    void bookFilm(User user, String date, String time, Screen screen, String seatRow, int seatNumber)
        throws SeatAlreadyTakenException, SeatNotFoundException, ShowingDoesNotExistException {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            validateShowingExists(screen, date,time);
            Showing showing = screenRepository.getShowingByDateTime(screen, date, time);
            validateSeatAvailability(showing, seatRow, seatNumber);

            customer.addBooking(new Booking(showing, seatRow + seatNumber));
            showing.bookSeat(seatRow, seatNumber);
            userRepository.updateDB();
            screenRepository.updateDB();
        }
    }

    private void validateSeatAvailability(Showing showing, String seatRow, int seatNumber) throws SeatAlreadyTakenException, SeatNotFoundException {
        if (showing.isSeatTaken(seatRow, seatNumber)) {
            throw new SeatAlreadyTakenException();
        }
    }

    private void validateShowingExists(Screen screen, String date, String time) throws ShowingDoesNotExistException {
        if (screenRepository.getShowingByDateTime(screen, date, time) == null) {
            throw new ShowingDoesNotExistException();
        }
    }
}
