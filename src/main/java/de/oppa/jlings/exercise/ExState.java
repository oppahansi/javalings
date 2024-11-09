package de.oppa.jlings.exercise;

import de.oppa.jlings.cli.CliPrinter;

public class ExState {
    private Exercise exercise;
    private CompileResult compileResult;
    private RunResult runResult;
    private int order;
    private boolean solved;
    private boolean done;

    public ExState(Exercise exercise,
            CompileResult compileResult,
            RunResult runResult,
            int order,
            boolean solved,
            boolean done) {

        this.exercise = exercise;
        this.compileResult = compileResult;
        this.runResult = runResult;
        this.order = order;
        this.solved = solved;
        this.done = done;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public CompileResult getCompileResult() {
        return compileResult;
    }

    public void setCompileResult(CompileResult compileResult) {
        this.compileResult = compileResult;
    }

    public RunResult getRunResult() {
        return runResult;
    }

    public void setRunResult(RunResult runResult) {
        this.runResult = runResult;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void printRunResults() {
        if (runResult.hasError()) {
            CliPrinter.printError(runResult.error());
        } else {
            CliPrinter.printSuccess(runResult.result());
        }
    }

    public void printCompileResults() {
        compileResult.compileResults().forEach(CliPrinter::printError);
    }

    public boolean compiles() {
        return compileResult.compileResults().isEmpty();
    }

    public boolean runs() {
        return !runResult.hasError();
    }

    @Override
    public String toString() {
        return "ExState{" +
                "exercise=" + exercise +
                ", compileResult=" + compileResult +
                ", runResult=" + runResult +
                ", order=" + order +
                ", solved=" + solved +
                ", done=" + done +
                '}';
    }
}
