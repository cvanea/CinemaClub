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
    //TODO SeatAlreadyTaken not working. Fix.

    private UserRepository userRepository;
    private ScreenRepository screenRepository;

    BookingSystem() {
        this.userRepository = DataBase.getUserRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    void bookFilm(User user, String date, String time, Film film, Screen screen, String seatRow, int seatNumber) throws SeatAlreadyTakenException, SeatNotFoundException {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            validateSeatAvailability(screen, seatRow, seatNumber);
            customer.addBooking(new Booking(film, date, time, screen.getScreenNumber(), seatRow + seatNumber));
            screen.bookSeat(seatRow, seatNumber);
            userRepository.updateDB();
            screenRepository.updateDB();
        }
    }

    private void validateSeatAvailability(Screen screen, String seatRow, int seatNumber) throws SeatAlreadyTakenException, SeatNotFoundException {
        if (screen.isSeatTaken(seatRow, seatNumber)) {
            throw new SeatAlreadyTakenException();
        }
    }
}
