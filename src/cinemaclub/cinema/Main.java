package cinemaclub.cinema;

import cinemaclub.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Cinema cinema = new Cinema();

        User user = cinema.loginUser();
        System.out.println(user.IExist());
        System.out.println(user.getName() + "'s login status is: " + user.getLoggedOn());



    }
}