package cinemaclub.cinema;

import java.io.Serializable;

public class Film implements Serializable {

    private static final long serialVersionUID = 8762368738673278L;

    String title;
    String imagePath;
    String description;
    String runTime;

    public Film(String title, String imagePath, String description, String runTime) {
        this.title = title;
        this.imagePath = imagePath;
        this.description = description;
        this.runTime = runTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return "Film{" +
            "title='" + title + '\'' +
            ", imagePath='" + imagePath + '\'' +
            ", description='" + description + '\'' +
            ", runTime='" + runTime + '\'' +
            '}';
    }
}
