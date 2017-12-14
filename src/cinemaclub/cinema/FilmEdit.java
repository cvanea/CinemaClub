package cinemaclub.cinema;

import cinemaclub.database.DataBase;
import cinemaclub.database.FilmRepository;
import cinemaclub.database.ScreenRepository;
import exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cinema system which validates and adds, removes, updates, and queries films, showings, and screens on the database.
 * These are all functions that a staff user can perform.
 */
class FilmEdit {

    private FilmRepository filmRepository;
    private ScreenRepository screenRepository;

    /**
     * Gets the singleton database instance relevant to the edit system.
     */
    FilmEdit() {
        this.filmRepository = DataBase.getFilmRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    /*
    Films
     */

    /**
     * Attempts to add a film to the film database.
     * First it validates that the film title doesn't already exist.
     * Then it creates a new Film object from the inputs and adds that film to the database.
     *
     * @param title new film's title
     * @param imagePath new film's image path
     * @param description new film's description
     * @param runTime new film's runtime
     * @throws FilmExistsException prevents a film from being added if there is already one with the same title
     */
    void addFilm(String title, String imagePath, String description, String runTime) throws FilmExistsException {
        validateNewFilm(title);
        Film film = new Film(title, imagePath, description, runTime);
        filmRepository.addFilm(title, film);
    }

    /**
     * Changes a film's title.
     * First it validates that a film with that title doesn't already exist.
     * Then it sets the new title on the Film object and saves that new object to the database.
     *
     * @param film the file whose title is to be changed
     * @param newTitle the new title
     * @throws FilmExistsException prevents the new title from being the same as an existing title
     */
    void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        validateNewFilm(newTitle);
        String oldTitle = film.getTitle();
        film.setTitle(newTitle);
        filmRepository.setFilmTitle(oldTitle, newTitle, film);
    }

    /**
     * Changes a film's image path on the object and updates the database.
     *
     * @param film the film whose path is to be changed
     * @param newImagePath the new image path
     */
    void setFilmImagePath(Film film, String newImagePath) {
        film.setImagePath(newImagePath);
        filmRepository.updateDB();
    }

    /**
     * Changes a film's description on the object and updates the database.
     *
     * @param film the film whose description is to be changed
     * @param newDescription new film description
     */
    void setFilmDescription(Film film, String newDescription) {
        film.setDescription(newDescription);
        filmRepository.updateDB();
    }

    /**
     * Changes a film's runtime on the object and updates the database.
     *
     * @param film the film whose description is to be changed
     * @param newRunTime new film runtime
     */
    void setFilmRunTime(Film film, String newRunTime) {
        film.setRunTime(newRunTime);
        filmRepository.updateDB();
    }

    /**
     * Removes a film from the film database.
     *
     * @param title title of film to be deleted
     */
    void deleteFilm(String title) {
        filmRepository.deleteFilm(title);
    }

    /**
     * Gets all films that could be shown at the cinema.
     *
     * @return all films in the film database
     */
    ArrayList<Film> displayAllFilms() {
        return filmRepository.getAllFilms();
    }

    /**
     * Gets film with the film title.
     *
     * @param title title of film
     * @return Film object matching title
     */
    Film getFilmDetailsByTitle(String title) {
        return filmRepository.getFilmByTitle(title);
    }

    /*
    Showings
     */

    /**
     * Attempts to add a showing to the screens database.
     * First it checks that the showing doesn't already exist across screens.
     * Then it checks that the new showing will not overlap with an existing showing.
     * If successful, it adds the showing to the database.
     *
     * @param screen screen of showing
     * @param date date of the showing
     * @param time time of the showing
     * @param film film of the showing
     * @throws ShowingAlreadyExistsException prevents adding a showing that already exists
     * @throws ShowingOnOtherScreenException prevents a showing from occurring at the same time and date across screens
     * @throws OverlappingRuntimeException prevents adding a showing when it would overlap with an existing showing
     */
    void addShowing(Screen screen, String date, String time, Film film)
        throws ShowingAlreadyExistsException, ShowingOnOtherScreenException, OverlappingRuntimeException {
        validateShowing(screen, date, time, film);
        validateShowingOverlap(screen, date, time, film);
        screenRepository.addShowing(screen, new Showing(screen, date, time, film, new HashMap<>()));
    }

    /**
     * Exports all the showings to a .csv file in the correct format.
     * First it creates a writer and either creates a new Showings.csv file or overwrites an existing one.
     * Then it sets the first line of the file as headers for the showings content to come.
     * Then it gets all showings and calls the .toCsv method on them to write them in the correct format.
     * Finally it closes the writer.
     */
    void exportShowingsToCsv() {
        try {
            FileWriter writer = new FileWriter("Showings.csv");
            writer.write("Film, Date, Time, Screen, Number of Booked Seats, Number of Available Seats, Booked Seats, Username\n");

            Map<Screen, ArrayList<Showing>> showings = screenRepository.getShowings();
            ArrayList<Showing> allShowings = new ArrayList<>();

            for (Screen screen : showings.keySet()) {
                allShowings.addAll(showings.get(screen));
            }

            for (Showing showing : allShowings) {
                writer.write(showing.toCsv());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a showing from the screens database.
     *
     * @param screen screen of the showing
     * @param date date of the showing
     * @param time time of the showing
     */
    void deleteShowing(Screen screen, String date, String time) {
        screenRepository.deleteShowing(screen, date, time);
    }

    /*
    Screens
     */

    /**
     * Attempts to add a new screen to the database.
     * First it validates that the screen number isn't already taken.
     *
     * @param screen screen to be added
     * @throws ScreenNumberAlreadyExistsException prevents a screen from being added with the same screen number
     */
    void addScreen(Screen screen) throws ScreenNumberAlreadyExistsException {
        validateNewScreen(screen.getScreenNumber());
        screenRepository.addScreen(screen);
    }

    /**
     * Gets all screens in the screens database.
     *
     * @return all screens
     */
    ArrayList<Screen> getScreens() {
        return screenRepository.getAllScreens();
    }

    /**
     * Removes a screen from the screens database.
     *
     * @param screen screen to be removed
     */
    void deleteScreen(Screen screen) {
        screenRepository.deleteScreen(screen);
    }

    /*
    Validation
     */

    /**
     * Validates that a film with the same title does not already exist.
     *
     * @param title title to be validated
     * @throws FilmExistsException exception thrown
     */
    private void validateNewFilm(String title) throws FilmExistsException {
        if (filmRepository.checkForFilm(title)) {
            throw new FilmExistsException();
        }
    }

    /**
     * Validates that a screen with the same screen number does not already exist.
     *
     * @param screenNumber screen number to be validated
     * @throws ScreenNumberAlreadyExistsException exception thrown
     */
    private void validateNewScreen(Integer screenNumber) throws ScreenNumberAlreadyExistsException {
        for (Screen screen : screenRepository.getAllScreens()) {
            if (screen.getScreenNumber().equals(screenNumber)) {
                throw new ScreenNumberAlreadyExistsException();
            }
        }
    }

    /**
     * Validates that a showing does not already exist on the same or across screens.
     *
     * @param screen screen of showing
     * @param date date of showing
     * @param time time of showing
     * @param film film showing
     * @throws ShowingAlreadyExistsException exception thrown
     * @throws ShowingOnOtherScreenException exception thrown
     */
    private void validateShowing(Screen screen, String date, String time, Film film) throws ShowingAlreadyExistsException, ShowingOnOtherScreenException {
        if (screenRepository.getShowingByDateTimeScreen(screen, date, time) != null) {
            throw new ShowingAlreadyExistsException();
        } else if (screenRepository.getShowingByDateTimeFilm(date, time, film) != null) {
            throw new ShowingOnOtherScreenException();
        }
    }

    /**
     * Validates that a new showing will not overlap with any existing showing.
     * It does this both for past and future showings.
     * By adding the film runtime to the showing time, the method obtains an endtime for the film
     * against which the new or old showing is checked.
     * Please see inline comments for full explanation of the method.
     *
     * @param screen screen of showing
     * @param date date of showing
     * @param newTime time of showing
     * @param film film showing
     * @throws OverlappingRuntimeException exception thrown
     */
    private void validateShowingOverlap(Screen screen, String date, String newTime, Film film) throws OverlappingRuntimeException {
        // First all the showings on a screen and date are retrieved.
        ArrayList<Showing> showingsByDateScreen = screenRepository.getShowingsByDateScreen(screen, date);

        // These showings are looped through to check against all possible overlaps.
        for (Showing showing : showingsByDateScreen) {

            /*
            Checking the existing showing endtime against the new showing time
            */

            String oldTime = showing.getTime(); // Time of the showing
            String runtime = showing.getFilm().getRuntime(); // Runtime of the showing

            // Split to obtain hours and minutes.
            String[] splitExistingTime = oldTime.split(":");
            String[] splitRuntime = runtime.split(":");

            // Hours
            String existingHourNumberString = splitExistingTime[0];
            String runtimeHourString = splitRuntime[0];
            Integer existingTimeHour = Integer.parseInt(existingHourNumberString);
            Integer runtimeHour = Integer.parseInt(runtimeHourString);
            Integer oldShowingEndtimeHour = existingTimeHour + runtimeHour;

            // Minutes
            String existingMinuteNumberString = splitExistingTime[1];
            String runtimeMinuteString = splitRuntime[1];
            Integer existingTimeMinute = Integer.parseInt(existingMinuteNumberString);
            Integer runtimeMinute = Integer.parseInt(runtimeMinuteString);
            Integer oldShowingEndtimeMinute = existingTimeMinute + runtimeMinute;

            // Creating the endtime
            String oldShowingEndtimeMinuteString;

            // Prevents minutes going over 60
            if (oldShowingEndtimeMinute > 60) {
                ++oldShowingEndtimeHour; //Add an hour
                oldShowingEndtimeMinute -= 60; //Remove the extra minutes

                if (oldShowingEndtimeMinute < 10) {
                    oldShowingEndtimeMinuteString = "0" + Integer.toString(oldShowingEndtimeMinute); //Keep the leading 0
                } else oldShowingEndtimeMinuteString = Integer.toString(oldShowingEndtimeMinute);

            } else {
                oldShowingEndtimeMinuteString = Integer.toString(oldShowingEndtimeMinute);
            }

            String oldShowingEndtimeHourString = Integer.toString(oldShowingEndtimeHour);

            // Final endtime
            String oldShowingEndtime = oldShowingEndtimeHourString + ":" + oldShowingEndtimeMinuteString;

            // Checking the existing showing endtime against the new showing time
            if ((newTime.compareTo(oldTime) > 0) && (oldShowingEndtime.compareTo(newTime)) > 0) {
                throw new OverlappingRuntimeException();
            }

            /*
            Checking the new showing endtime against the old showing time
            */

            String newRuntime = film.getRuntime(); // Runtime of new showing

            // Split to obtain hours and minutes.
            String[] splitNewRuntime = newRuntime.split(":");
            String[] splitTime = newTime.split(":");

            // Hour
            String newRuntimeHourString = splitNewRuntime[0];
            String timeHourString = splitTime[0];
            Integer newRuntimeHour = Integer.parseInt(newRuntimeHourString);
            Integer timeHour = Integer.parseInt(timeHourString);
            Integer newShowingEndtimeHour = newRuntimeHour + timeHour;

            // Minute
            String newRuntimeMinuteString = splitNewRuntime[1];
            String timeMinuteString = splitTime[1];
            Integer newRuntimeMinute = Integer.parseInt(newRuntimeMinuteString);
            Integer timeMinute = Integer.parseInt(timeMinuteString);
            Integer newShowingEndtimeMinute = newRuntimeMinute + timeMinute;

            // Creating the endtime
            String newShowingEndtimeMinuteString;

            // Prevents minutes going over 60
            if (newShowingEndtimeMinute > 60) {
                ++newShowingEndtimeHour; //Add an hour
                newShowingEndtimeMinute -= 60; //Remove the extra minutes

                if (newShowingEndtimeMinute < 10) {
                    newShowingEndtimeMinuteString = "0" + Integer.toString(newShowingEndtimeMinute); //Keep the leading 0
                } else newShowingEndtimeMinuteString = Integer.toString(newShowingEndtimeMinute);

            } else {
                newShowingEndtimeMinuteString = Integer.toString(newShowingEndtimeMinute);
            }

            String newShowingEndtimeHourString = Integer.toString(newShowingEndtimeHour); //hour add

            // Final endtime
            String newShowingEndtime = newShowingEndtimeHourString + ":" + newShowingEndtimeMinuteString;

            // Checking the new showing endtime against the old showing time
            if ((newTime.compareTo(oldTime) < 0) && (oldTime.compareTo(newShowingEndtime) < 0)) {
                throw new OverlappingRuntimeException();
            }
        }
    }
}