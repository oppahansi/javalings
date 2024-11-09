package de.oppa.jlings.command;

import de.oppa.jlings.cli.CliPrinter;

public class ExitCmd implements Cmd {
    @Override
    public void execute() {
        CliPrinter.print("ExitCmd executed");
    }
}
