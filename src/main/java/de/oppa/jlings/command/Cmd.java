package de.oppa.jlings.command;

public interface Cmd {
    String EXIT_CMD = "exit";
    String GOOGLE_CMD = "google";
    String HELP_CMD = "help";
    String HINT_CMD = "hint";
    String LIST_CMD = "list";
    String NEXT_CMD = "next";
    String RESET_CMD = "reset";
    String RUN_CMD = "run";
    String WATCH_CMD = "watch";

    void execute();
}
