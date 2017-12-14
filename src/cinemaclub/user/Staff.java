package cinemaclub.user;

public class Staff extends User {

    private String staffId;

    public Staff(UserCredentials userCredentials) {
        super(userCredentials);
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
