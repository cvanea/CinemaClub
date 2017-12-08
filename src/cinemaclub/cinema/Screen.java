package cinemaclub.cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class Screen implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private Integer screenNumber;
    private ArrayList<String> seats = new ArrayList<>();

    public Screen(Integer screenNumber, int numberRow, int seatsPerRow) {
        this.screenNumber = screenNumber;
        setupSeats(numberRow, seatsPerRow);
    }

    private void setupSeats(int numberRow, int seatsPerRow) {

        for (int i = 0; i < numberRow; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                char rowLetter = (char) ('A' + i);
                seats.add(String.valueOf(rowLetter) + (j + 1));
            }
        }
    }

    ArrayList<String> getSeats() {
        return seats;
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
