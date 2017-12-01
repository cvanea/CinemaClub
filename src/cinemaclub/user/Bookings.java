package cinemaclub.user;

import java.time.LocalDateTime;
import java.util.Map;

public class Bookings {

    Map<String, LocalDateTime> bookings;

    public Bookings(Map<String, LocalDateTime> bookings) {
        this.bookings = bookings;
    }

    public Map<String, LocalDateTime> getBookings() {
        return bookings;
    }

    public void setBookings(Map<String, LocalDateTime> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry entry : bookings.entrySet()) {
            stringBuilder.append("{").append(entry.toString()).append("}");
        }

        return stringBuilder.toString();
    }
}
