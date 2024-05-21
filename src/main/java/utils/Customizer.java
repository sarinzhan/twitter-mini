package utils;

import java.util.Scanner;

public class Customizer {
    private static String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    private static String GREEN = "\u001B[32m";
    private static String YELLOW = "\u001B[33m";
    private static String WHITE_BG = "\u001B[47m";

    public static void start(String text) {
        System.out.println(GREEN + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + text + " >>>>>" + RESET);
    }

    public static void end(String text) {
        System.out.println(GREEN + "<<<<< " + text + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + RESET);
    }

    public static void commands() {
        start("Доступные команды");
        command("exit","выход");
        command("register","регистрация");
        command("login","авторизация");
        command("logout","выход из аккаунта");
        command("info","информация о текущем пользователе");
        command("info by login","информация о пользователе по логину");
        command("info all","информация о всех пользователях");
        command("add post","добавить публикацию под текущим пользователем");
        command("my posts","мои публикации");
        command("all posts","публикации");
        command("post by tag","публикации по тегу");
        command("post by login","публикации по логину пользователя");
        command("post by user type","публикации по типу пользователя");
        command("help","список команд");
    }
    public static void mainMenu(){
        start("Добро пожаловать!");
        commands();
    }

    private static void command(String command, String des) {
        System.out.printf("%s %s %s - %s\n", YELLOW, command, RESET, des);
    }
}

