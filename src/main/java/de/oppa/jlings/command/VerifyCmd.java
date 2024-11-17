package de.oppa.jlings.command;

import de.oppa.jlings.cli.CliPrinter;
import de.oppa.jlings.exercise.ExManager;
import de.oppa.jlings.exercise.ExVerifier;

public class VerifyCmd implements CmdArgs {

    @Override
    public void execute(String name) {
        if (name == null || name.isBlank() || name.isEmpty() || "all".equalsIgnoreCase(name)) {
            verifyAll();
            return;
        }

        var manager = ExManager.getInstance();
        var verifier = new ExVerifier();
        var state = manager.getState(name);

        verifier.verify(state);

        CliPrinter.print("Verifying exercise %s...".formatted(name));

        if (!state.compiles()) {
            CliPrinter.print("Exercise %s does not compile.".formatted(name));
            state.printCompileResults();
            return;
        }

        CliPrinter.printSuccess("Exercise %s compiles.".formatted(name));

        if (!state.runs()) {
            CliPrinter.print("Exercise %s does not run.".formatted(name));
            CliPrinter.printError(state.getRunResult().error());
            return;
        }

        CliPrinter.printSuccess("Exercise %s runs.".formatted(name));

        if (state.isSolved()) {
            CliPrinter.printSuccess("Exercise %s has been solved.".formatted(name));
        } else {
            CliPrinter.printWarning("Exercise %s has not been solved.".formatted(name));
        }
        if (state.isDone()) {
            CliPrinter.printSuccess("Exercise %s has been completed.".formatted(name));
        } else {
            CliPrinter.printWarning("Exercise %s has not been completed.".formatted(name));
            CliPrinter.printWarning("Remove the '// I AM NOT DONE' comment to mark it as done.");
        }
    }

    private void verifyAll() {
        var manager = ExManager.getInstance();
        var verifier = new ExVerifier();
        var solvedCounter = 0;

        for (var state : manager.getNameToState()) {
            verifier.verify(state);

            if (state.isSolved() && state.isDone()) {
                solvedCounter++;
            }

            CliPrinter.clear();
            CliPrinter.printProgressBar(solvedCounter, manager.getNameToState().size());
        }
    }

}
