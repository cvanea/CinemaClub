package cinemaclub.cinema;

import exceptions.SeatNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Screen {

    private final Map<String, Boolean> seats;

    public Screen(int numberRow, int seatsPerRow) {
        seats = setupSeats(numberRow, seatsPerRow);
    }

    private Map<String, Boolean> setupSeats(int row, int seatsPerRow) {
        Map<String, Boolean> seats = new LinkedHashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                char rowLetter = (char) ('A' + i);
                seats.put(String.valueOf(rowLetter) + (j + 1) , false);
            }
        }
        return seats;
    }

    public Map<String, Boolean> getSeats() {
        return seats;
    }

    public Boolean checkSeatAvailability(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        return seats.get(row + number);
    }

    public void bookSeat(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        seats.put(row + number, true);
    }

    public void unbookSeat(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        seats.put(row + number, false);
    }

    private void validateSeat(String row, int number) throws SeatNotFoundException {
        if (seats.containsKey(row + number)) {
            throw new SeatNotFoundException();
        }
    }

}
