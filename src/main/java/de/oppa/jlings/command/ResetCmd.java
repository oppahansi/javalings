package de.oppa.jlings.command;

import de.oppa.jlings.cli.CliPrinter;
import de.oppa.jlings.exercise.ExManager;
import de.oppa.jlings.exercise.ExResetter;

public class ResetCmd implements CmdArgs {

    @Override
    public void execute(String name) {
        var exResetter = new ExResetter();

        if (name == null || name.isBlank() || name.isEmpty() || "all".equalsIgnoreCase(name)) {
            exResetter.resetAll();
            CliPrinter.print("All exercises have been reset.");
        } else {
            var manager = ExManager.getInstance();

            exResetter.reset(manager.getExercise(name));

            CliPrinter.print("Exercise %s has been reset.".formatted(name));
        }
    }
}
