package de.oppa.jlings.command;

import de.oppa.jlings.cli.CliPrinter;
import de.oppa.jlings.exercise.ExCompiler;
import de.oppa.jlings.exercise.ExManager;
import de.oppa.jlings.exercise.ExRunner;

public class RunCmd implements CmdArgs {
    @Override
    public void execute(String name) {
        var manager = ExManager.getInstance();
        var exercise = manager.getExercise(name);
        var compiler = new ExCompiler();

        CliPrinter.clear();
        CliPrinter.print("Running exercise: " + exercise.name() + "\n");

        var compileResult = compiler.compile(exercise);
        if (!compileResult.compileResults().isEmpty()) {
            CliPrinter.printError("Compilation failed!");
            compileResult.print();
            return;
        }

        CliPrinter.printSuccess("Exercise compiled successfully!");

        var runner = new ExRunner();
        var runResult = runner.run(exercise);
        if (runResult.hasError()) {
            CliPrinter.printError("Execution failed!");
            CliPrinter.printError(runResult.error());
            return;
        }

        CliPrinter.printSuccess("Exercise executed successfully!\n");
        CliPrinter.print("Output:\n" + runResult.result());

        if (exercise.expectedOutput().isBlank()) {
            CliPrinter.printSuccess("No expected output!");
        } else if (runResult.result().trim().toLowerCase().equals(exercise.expectedOutput().trim().toLowerCase())) {
            CliPrinter.printSuccess("Output is correct!");
        } else {
            CliPrinter.printError("Output is not correct!");
        }

        if (manager.isDone(name)) {
            CliPrinter.printSuccess("Exercise is marked as done!");
        } else {
            CliPrinter.printWarning(
                    "Exercise is not done yet! Remove the '// I AM NOT DONE' comment to mark it as done.");
        }

    }
}
