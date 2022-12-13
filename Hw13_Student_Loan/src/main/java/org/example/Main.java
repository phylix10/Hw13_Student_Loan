package org.example;

import org.example.menu.MainMenu;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        MainMenu mainMenu = new MainMenu();
        mainMenu.run();
    }
}
