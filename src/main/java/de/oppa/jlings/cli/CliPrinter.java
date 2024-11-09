package de.oppa.jlings.cli;

import de.oppa.jlings.cfg.Colors;

public class CliPrinter {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printError(String message) {
        print(message, Colors.RED);
    }

    public static void printWarning(String message) {
        print(message, Colors.YELLOW);
    }

    public static void printSuccess(String message) {
        print(message, Colors.GREEN);
    }

    public static void print(String message, String color) {
        System.out.println(color + message + Colors.RESET);
    }

    public static void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void printProgressBar(int solvedCounter, int size) {
        var progressBarLength = 50.0f;
        var percent = solvedCounter != 0.0f ? ((float) solvedCounter / (float) size) : 0.0f;
        var progress = progressBarLength * percent;
        var incompleteProgress = progress != 0 ? (int) progressBarLength - (int) progress : progressBarLength;

        var barChar = " ";
        var completeBar = barChar.repeat((int) progress);
        var incompleteBar = barChar.repeat((int) incompleteProgress);

        printf("Progress %d%% : [%s%s] %d/%d %n%n", (int) (percent * 100),
                colorize(completeBar, Colors.GREEN_BG), colorize(incompleteBar, Colors.BLACK_BG_BT),
                solvedCounter, size);
    }

    public static void printf(String message, Object... args) {
        System.out.printf(message, args);
    }

    public static String colorize(String message, String color) {
        return color + message + Colors.RESET;
    }
}
