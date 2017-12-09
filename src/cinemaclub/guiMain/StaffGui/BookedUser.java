package cinemaclub.guiMain.StaffGui;

import javafx.beans.property.SimpleStringProperty;

public class BookedUser {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty userName;
        private final SimpleStringProperty surname;
        private final SimpleStringProperty seat;


        public BookedUser(String userName, String firstName, String surname, String seat) {
            this.userName = new SimpleStringProperty(userName);
            this.firstName = new SimpleStringProperty(firstName);
            this.surname = new SimpleStringProperty(surname);
            this.seat = new SimpleStringProperty(seat);
        }

        public String getFirstName() {
            return firstName.get();
        }
        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getSurname() {
            return surname.get();
        }
        public void setSurname(String sName) {
            surname.set(sName);
        }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String uName) {
        userName.set(uName);
    }

    public void setSeat(String Seat) {
        seat.set(Seat);
    }

    public String getSeat() {
        return seat.get();
    }

}
