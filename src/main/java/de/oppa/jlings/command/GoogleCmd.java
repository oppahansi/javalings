package de.oppa.jlings.command;

import de.oppa.jlings.cli.CliPrinter;
import de.oppa.jlings.exercise.ExManager;

public class GoogleCmd implements CmdArgs {
    @Override
    public void execute(String name) {
        var manager = ExManager.getInstance();

        CliPrinter.print(manager.getExercise(name).google());
    }
}
