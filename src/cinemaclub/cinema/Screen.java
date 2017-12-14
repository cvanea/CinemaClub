package cinemaclub.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Object that holds all information for a particular screen.
 * Serializable allows it to be saved as bytes to an external database file.
 */
public class Screen implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private Integer screenNumber;
    private int numberRow;
    private int seatsPerRow;
    private ArrayList<String> seats = new ArrayList<>();

    /**
     * Instantiating a screen requires a screen number and the number of rows and seats per row.
     * The seat information is then used to initialise all the seats for the screen size.
     *
     * @param screenNumber screen number
     * @param numberRow number of rows
     * @param seatsPerRow seats per row
     */
    public Screen(Integer screenNumber, int numberRow, int seatsPerRow) {
        this.screenNumber = screenNumber;
        this.numberRow = numberRow;
        this.seatsPerRow = seatsPerRow;
        setupSeats(numberRow, seatsPerRow);
    }

    /**
     * Creates the correct number of named seats depending on the numbers of rows and seats per row.
     * Rows are named by letters and seats per row by numbers.
     *
     * @param numberRow number of rows
     * @param seatsPerRow seats per row
     */
    private void setupSeats(int numberRow, int seatsPerRow) {
        for (int i = 0; i < numberRow; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                char rowLetter = (char) ('A' + i);
                seats.add(String.valueOf(rowLetter) + (j + 1));
            }
        }
    }

    /**
     * Gets all the seats.
     *
     * @return all seats
     */
    ArrayList<String> getSeats() {
        return seats;
    }

    /**
     * Gets the screen number.
     *
     * @return screen number
     */
    public Integer getScreenNumber() {
        return screenNumber;
    }

    /**
     * Gets the number of rows.
     *
     * @return number of rows
     */
    public int getNumberRow() {
        return numberRow;
    }

    /**
     * Gets the number of seats per row.
     *
     * @return seats per row
     */
    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    /**
     * Overrides toString method to clearly show all screen properties.
     *
     * @return string of all screen properties
     */
    @Override
    public String toString() {
        return "Screen{" + "screenNumber=" + screenNumber + ", numberRow=" + numberRow +
            ", seatsPerRow=" + seatsPerRow + ", seats=" + seats + '}';
    }
}
