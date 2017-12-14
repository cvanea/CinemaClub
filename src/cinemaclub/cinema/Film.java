package cinemaclub.cinema;

import java.io.Serializable;

/**
 * Object that holds all information for a particular film.
 */
public class Film implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    private String title;
    private String imagePath;
    private String description;
    private String runTime;

    /**
     * Instantiating a film requires all film properties to be passed.
     *
     * @param title film title
     * @param imagePath film image path
     * @param description film description
     * @param runTime film runtime
     */
    public Film(String title, String imagePath, String description, String runTime) {
        this.title = title;
        this.imagePath = imagePath;
        this.description = description;
        this.runTime = runTime;
    }

    /**
     * Gets title property.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title property.
     *
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets image path property.
     *
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets image path property.
     *
     * @param imagePath image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets description property.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description property.
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets runtime property.
     *
     * @return runtime
     */
    public String getRuntime() {
        return runTime;
    }

    /**
     * Sets runtime property.
     *
     * @param runTime runtime
     */
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    /**
     * Overrides toString method to clearly show all film properties.
     *
     * @return string of all film properties
     */
    @Override
    public String toString() {
        return "Film{" + "title='" + title + '\'' + ", imagePath='" + imagePath + '\'' +
            ", description='" + description + '\'' + ", runTime='" + runTime + '\'' + '}';
    }
}
