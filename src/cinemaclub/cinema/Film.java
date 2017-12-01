package cinemaclub.cinema;

public class Film {

    String title;
    String imagePath;
    String description;
    int runTime;

    public Film(String title, String imagePath, String description, int runTime) {
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

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return this.getTitle() + ", " + this.getImagePath() + ", " + this.getDescription() + ", " +  this.getRunTime();
    }
}
