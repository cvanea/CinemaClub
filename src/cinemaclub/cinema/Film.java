package cinemaclub.cinema;

public class Film {

    String title;
    String imagePath;
    int runTime;
    String description;

    public Film(String title, String imagePath, int runTime, String description) {
        this.title = title;
        this.imagePath = imagePath;
        this.runTime = runTime;
        this.description = description;
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

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getTitle() + ", " + this.getImagePath() + ", " + this.getRunTime() + ", " + this.getDescription() ;
    }
}
