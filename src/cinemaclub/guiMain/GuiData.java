package cinemaclub.guiMain;

import cinemaclub.cinema.Film;

public class GuiData {

    private static String successMessage;
    private static Film film;
    private static String date;
    private static String time;

    public static String getSuccessMessage() {
        return successMessage;
    }

    public static void setSuccessMessage(String newSuccessMessage) {
        successMessage = newSuccessMessage;
    }

    public static Film getFilm() {
        return film;
    }

    public static void setFilm(Film film) {
        GuiData.film = film;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        GuiData.date = date;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        GuiData.time = time;
    }
}
