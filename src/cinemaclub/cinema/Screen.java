package cinemaclub.cinema;

import exceptions.SeatNotFoundException;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Screen implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private Integer screenNumber;
    private final Map<String, Boolean> seats;

    public Screen(Integer screenNumber, int numberRow, int seatsPerRow) {
        this.screenNumber = screenNumber;
        seats = setupSeats(numberRow, seatsPerRow);
    }

    private Map<String, Boolean> setupSeats(int numberRow, int seatsPerRow) {
        Map<String, Boolean> seats = new LinkedHashMap<>();
        for (int i = 0; i < numberRow; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                char rowLetter = (char) ('A' + i);
                seats.put(String.valueOf(rowLetter) + (j + 1) , false);
            }
        }
        return seats;
    }

    Map<String, Boolean> getSeats() {
        return seats;
    }

    public Boolean isSeatTaken(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        return seats.get(row + number);
    }

    void bookSeat(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        seats.put(row + number, true);
    }

    public void unbookSeat(String row, int number) throws SeatNotFoundException {
        validateSeat(row, number);
        seats.put(row + number, false);
    }

    private void validateSeat(String row, int number) throws SeatNotFoundException {
        if (!seats.containsKey(row + number)) {
            throw new SeatNotFoundException();
        }
    }

    public Integer getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(Integer screenNumber) {
        this.screenNumber = screenNumber;
    }

    @Override
    public String toString() {
        return "Screen{" + "seats=" + seats + '}';
    }
}
