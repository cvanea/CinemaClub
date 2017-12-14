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

class FilmEdit {

    private FilmRepository filmRepository;
    private ScreenRepository screenRepository;

    FilmEdit() {
        this.filmRepository = DataBase.getFilmRepository();
        this.screenRepository = DataBase.getScreenRepository();
    }

    void addFilm(String title, String imagePath, String description, String runTime) throws FilmExistsException {
        validateNewFilm(title);
        Film film = new Film(title, imagePath, description, runTime);
        filmRepository.addFilm(title, film);
    }

    void setFilmTitle(Film film, String newTitle) throws FilmExistsException {
        validateNewFilm(newTitle);
        String oldTitle = film.getTitle();
        film.setTitle(newTitle);
        filmRepository.setFilmTitle(oldTitle, newTitle, film);
    }

    void setFilmImagePath(Film film, String newImagePath) {
        film.setImagePath(newImagePath);
        filmRepository.updateDB();
    }

    void setFilmDescription(Film film, String newDescription) {
        film.setDescription(newDescription);
        filmRepository.updateDB();
    }

    void setFilmRunTime(Film film, String newRunTime) {
        film.setRunTime(newRunTime);
        filmRepository.updateDB();
    }

    void deleteFilm(String title) {
        filmRepository.deleteFilm(title);
    }

    ArrayList<Film> displayAllFilms() {
        return filmRepository.getAllFilms();
    }

    Film getFilmDetailsByTitle(String title) {
        return filmRepository.getFilmByTitle(title);
    }

    void addShowing(Screen screen, String date, String time, Film film)
        throws ShowingAlreadyExistsException, ShowingOnOtherScreenException, OverlappingRuntimeException {
        validateShowing(screen, date, time, film);
        validateShowingOverlap(screen, date, time, film);
        screenRepository.addShowing(screen, new Showing(screen, date, time, film, new HashMap<>()));
    }

    void deleteShowing(Screen screen, String date, String time) {
        screenRepository.deleteShowing(screen, date, time);
    }

    void addScreen(Screen screen) throws ScreenNumberAlreadyExistsException {
        validateNewScreen(screen.getScreenNumber());
        screenRepository.addScreen(screen);
    }

    void deleteScreen(Screen screen) {
        screenRepository.deleteScreen(screen);
    }

    ArrayList<Screen> getScreens() {
        return screenRepository.getAllScreens();
    }

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

    private void validateNewFilm(String title) throws FilmExistsException {
        if (filmRepository.checkForFilm(title)) {
            throw new FilmExistsException();
        }
    }

    private void validateNewScreen(Integer screenNumber) throws ScreenNumberAlreadyExistsException {
        for (Screen screen : screenRepository.getAllScreens()) {
            if (screen.getScreenNumber().equals(screenNumber)) {
                throw new ScreenNumberAlreadyExistsException();
            }
        }
    }

    private void validateShowing(Screen screen, String date, String time, Film film) throws ShowingAlreadyExistsException, ShowingOnOtherScreenException {
        if (screenRepository.getShowingByDateTimeScreen(screen, date, time) != null) {
            throw new ShowingAlreadyExistsException();
        } else if (screenRepository.getShowingByDateTimeFilm(date, time, film) != null) {
            throw new ShowingOnOtherScreenException();
        }
    }

    private void validateShowingOverlap(Screen screen, String date, String time, Film film) throws OverlappingRuntimeException {
        ArrayList<Showing> showingsByDateScreen = screenRepository.getShowingsByDateScreen(screen, date);

        for (Showing showing : showingsByDateScreen) {
            String existingTime = showing.getTime();
            String runtime = showing.getFilm().getRuntime();

            String[] splitExistingTime = existingTime.split(":");
            String[] splitRuntime = runtime.split(":");

            // Hour
            String existingHourNumberString = splitExistingTime[0];
            String runtimeHourString = splitRuntime[0];

            Integer existingTimeHour = Integer.parseInt(existingHourNumberString);
            Integer runtimeHour = Integer.parseInt(runtimeHourString);

            Integer timePlusRuntimeHour = existingTimeHour + runtimeHour;

            // Minute
            String existingMinuteNumberString = splitExistingTime[1];
            String runtimeMinuteString = splitRuntime[1];

            Integer existingTimeMinute = Integer.parseInt(existingMinuteNumberString);
            Integer runtimeMinute = Integer.parseInt(runtimeMinuteString);

            Integer timePlusRuntimeMinute = existingTimeMinute + runtimeMinute;

            String timePlusRuntimeMinuteString;

            if (timePlusRuntimeMinute > 60) {
                ++timePlusRuntimeHour;
                timePlusRuntimeMinute -= 60;
                if (timePlusRuntimeMinute < 10) {
                    timePlusRuntimeMinuteString = "0" + Integer.toString(timePlusRuntimeMinute);
                } else timePlusRuntimeMinuteString = Integer.toString(timePlusRuntimeMinute);
            } else {
                timePlusRuntimeMinuteString = Integer.toString(timePlusRuntimeMinute);
            }

            String timePlusRuntimeHourString = Integer.toString(timePlusRuntimeHour); //hour add

            // Time Plus Runtime Time
            String timePlusRuntime = timePlusRuntimeHourString + ":" + timePlusRuntimeMinuteString;

            // Future
            if ((time.compareTo(existingTime) > 0) && (timePlusRuntime.compareTo(time)) > 0) {
                throw new OverlappingRuntimeException();
            }

            String newRuntime = film.getRuntime();

            String[] splitNewRuntime = newRuntime.split(":");
            String[] splitTime = time.split(":");

            // Hour
            String newRuntimeHourString = splitNewRuntime[0];
            String timeHourString = splitTime[0];

            Integer newRuntimeHour = Integer.parseInt(newRuntimeHourString);
            Integer timeHour = Integer.parseInt(timeHourString);

            Integer newRuntimePlusNewTimeHour = newRuntimeHour + timeHour;

            // Minute
            String newRuntimeMinuteString = splitNewRuntime[1];
            String timeMinuteString = splitTime[1];

            Integer newRuntimeMinute = Integer.parseInt(newRuntimeMinuteString);
            Integer timeMinute = Integer.parseInt(timeMinuteString);

            Integer newRuntimePlusNewTimeMinute = newRuntimeMinute + timeMinute;

            String newRunTimePlusNewTimeMinuteString;

            if (newRuntimePlusNewTimeMinute > 60) {
                ++newRuntimePlusNewTimeHour;
                newRuntimePlusNewTimeMinute -= 60;
                if (newRuntimePlusNewTimeMinute < 10) {
                    newRunTimePlusNewTimeMinuteString = "0" + Integer.toString(newRuntimePlusNewTimeMinute);
                } else newRunTimePlusNewTimeMinuteString = Integer.toString(newRuntimePlusNewTimeMinute);
            } else {
                newRunTimePlusNewTimeMinuteString = Integer.toString(newRuntimePlusNewTimeMinute);
            }

            String newRunTimePlusNewTimeHourString = Integer.toString(newRuntimePlusNewTimeHour); //hour add

            // Time Plus Runtime Time
            String runtimePlusTime = newRunTimePlusNewTimeHourString + ":" + newRunTimePlusNewTimeMinuteString;

            // Past
            if ((time.compareTo(existingTime) < 0) && (existingTime.compareTo(runtimePlusTime) < 0)) {
                throw new OverlappingRuntimeException();
            }
        }
    }
}