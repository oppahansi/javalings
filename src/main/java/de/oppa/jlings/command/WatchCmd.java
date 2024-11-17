package de.oppa.jlings.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.oppa.jlings.cli.CliPrinter;
import de.oppa.jlings.exercise.ExManager;
import de.oppa.jlings.exercise.ExVerifier;
import de.oppa.jlings.exercise.ExWatcher;
import de.oppa.jlings.exercise.Exercise;

public class WatchCmd implements CmdArgs {
    private volatile boolean running = true;

    @Override
    public void execute(String option) {
        var manager = ExManager.getInstance();

        if (option == null || option.isBlank() || option.equals("all")) {
            manager.getExercises().forEach(this::watchExercise);
        } else {
            watchExercise(manager.getExercise(option));
        }
    }

    private void watchExercise(Exercise exercise) {
        var manager = ExManager.getInstance();
        var watcher = new ExWatcher();
        var verifier = new ExVerifier();

        running = true;
        checkState(exercise, manager, verifier);

        watcher.watch(exercise, () -> checkState(exercise, manager, verifier));

        processInput(watcher);
    }

    private void processInput(ExWatcher watcher) {
        var console = System.console();
        if (console != null) {
            while (running) {
                try {
                    if (!console.reader().ready()) {
                        continue;
                    }

                    String input = console.readLine();

                    processInput(watcher, input);
                } catch (IOException e) {
                    throw new IllegalStateException("Error reading input", e);
                }

            }
        } else {
            try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (running) {
                    if (!reader.ready()) {
                        continue;
                    }

                    String input = reader.readLine();

                    processInput(watcher, input);
                }
            } catch (IOException e) {
                throw new IllegalStateException("Error reading input", e);
            }
        }
    }

    private void processInput(ExWatcher watcher, String input) {
        if (input == null) {
            return;
        }

        input = input.trim();

        if ("exit".equalsIgnoreCase(input)) {
            running = false;
            watcher.stop();
        }
    }

    private void checkState(Exercise exercise, ExManager manager, ExVerifier verifier) {
        var state = manager.getState(exercise.name());
        verifier.verify(state);

        CliPrinter.clear();
        CliPrinter.printProgressBar(manager.getSolved().size(), manager.getExercises().size());

        if (state.getCompileResult().compileResults().isEmpty()) {
            state.printRunResults();
        } else {
            CliPrinter.printError("Compilation failed");
            state.printCompileResults();
        }
    }
}