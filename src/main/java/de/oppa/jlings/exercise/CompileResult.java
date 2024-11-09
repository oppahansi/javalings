package de.oppa.jlings.exercise;

import java.util.List;

import de.oppa.jlings.cli.CliPrinter;

public record CompileResult(List<String> compileResults) {

    public void print() {
        for (var result : compileResults) {
            CliPrinter.printError(result);
        }
    }
}
