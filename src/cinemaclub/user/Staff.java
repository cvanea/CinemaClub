package cinemaclub.user;

/**
 * Class for staff users of the cinema system.
 * Inherits most getters and setters from User.
 */
public class Staff extends User {

    private String staffId;

    /**
     * Inherits UserCredentials from User for instantiation.
     *
     * @param userCredentials profile details such as username, first name, email, etc.
     */
    public Staff(UserCredentials userCredentials) {
        super(userCredentials);
    }

    /**
     * Gets staff ID for this staff.
     *
     * @return staff ID
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * Sets staff ID for this staff.
     *
     * @param staffId staff ID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
