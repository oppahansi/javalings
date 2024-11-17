package de.oppa.jlings.command;

import de.oppa.jlings.cfg.Colors;
import de.oppa.jlings.cli.CliPrinter;
import de.oppa.jlings.exercise.ExManager;

public class ListCmd implements CmdArgs {
    @Override
    public void execute(String option) {
        var manager = ExManager.getInstance();

        if (option == null || option.isBlank() || option.isEmpty() || "all".equalsIgnoreCase(option)) {
            manager.getNameToState().forEach((state) -> {
                var status = (state.isSolved() ? Colors.GREEN + "solved" : Colors.RED + "unsolved") + Colors.RESET;
                CliPrinter.print(state.getExercise().name() + " - " + status);
            });
        } else if ("unsolved".equalsIgnoreCase(option)) {
            manager.getUnsolved().forEach(exercise -> CliPrinter.print(exercise.name()));
        } else if ("solved".equalsIgnoreCase(option)) {
            manager.getSolved().forEach(exercise -> CliPrinter.print(exercise.name()));
        } else if ("extended".equalsIgnoreCase(option)) {
            manager.getNameToState().forEach((state) -> {
                var status = (state.isSolved() ? Colors.GREEN + "solved" : Colors.RED + "unsolved") + Colors.RESET;
                CliPrinter.print(state.getExercise().name() + " - " + status);
            });
        } else {
            CliPrinter.print("Invalid option for list command: " + option);
        }
    }
}
