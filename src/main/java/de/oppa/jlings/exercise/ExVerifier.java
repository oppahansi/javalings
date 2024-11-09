package de.oppa.jlings.exercise;

public class ExVerifier {
    private final ExCompiler compiler;
    private final ExRunner runner;
    private final ExManager manager;

    public ExVerifier() {
        this.compiler = new ExCompiler();
        this.runner = new ExRunner();
        this.manager = ExManager.getInstance();
    }

    public void verify(ExState state) {
        var exercise = state.getExercise();

        state.setCompileResult(compiler.compile(exercise));
        state.setRunResult(runner.run(exercise));
        state.setSolved(checkIfSolved(state));
        state.setDone(manager.isDone(exercise.name()));
    }

    private boolean checkIfSolved(ExState state) {
        var compileResults = state.getCompileResult().compileResults();
        var runResult = state.getRunResult();
        var exercise = state.getExercise();
        var expectedOutput = exercise.expectedOutput();

        return compileResults.isEmpty() && !runResult.hasError() &&
                (expectedOutput.isBlank() || runResult.result().trim().equalsIgnoreCase(expectedOutput.trim()));
    }
}