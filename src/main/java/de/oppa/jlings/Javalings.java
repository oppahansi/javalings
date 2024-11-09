package de.oppa.jlings;

import de.oppa.jlings.cli.Cli;
import de.oppa.jlings.cli.CliPrinter;
import de.oppa.jlings.command.GoogleCmd;
import de.oppa.jlings.command.HelpCmd;
import de.oppa.jlings.command.HintCmd;
import de.oppa.jlings.command.ListCmd;
import de.oppa.jlings.command.NextCmd;
import de.oppa.jlings.command.ResetCmd;
import de.oppa.jlings.command.RunCmd;
import de.oppa.jlings.command.VerifyCmd;
import de.oppa.jlings.command.WatchCmd;

public class Javalings {
    public static void main(String[] args) {
        if (args.length == 0) {
            CliPrinter.print("No arguments provided. Use -h for help.");
            return;
        }

        var cli = Cli.getCli(args);

        if (cli.getOptions().length == 0) {
            CliPrinter.print("No valid options provided. Use -h for help.");
            return;
        }

        if (cli.hasOption(Cli.HELP_OPTION)) {
            new HelpCmd().execute();
        } else if (cli.hasOption(Cli.WATCH_OPTION)) {
            new WatchCmd().execute(cli.getOptionValue(Cli.WATCH_OPTION));
        } else if (cli.hasOption(Cli.LIST_OPTION)) {
            new ListCmd().execute(cli.getOptionValue(Cli.LIST_OPTION));
        } else if (cli.hasOption(Cli.RUN_OPTION)) {
            new RunCmd().execute(cli.getOptionValue(Cli.RUN_OPTION));
        } else if (cli.hasOption(Cli.NEXT_OPTION)) {
            new NextCmd().execute();
        } else if (cli.hasOption(Cli.HINT_OPTION)) {
            new HintCmd().execute(cli.getOptionValue(Cli.HINT_OPTION));
        } else if (cli.hasOption(Cli.GOOGLE_OPTION)) {
            new GoogleCmd().execute(cli.getOptionValue(Cli.GOOGLE_OPTION));
        } else if (cli.hasOption(Cli.VERIFY_OPTION)) {
            new VerifyCmd().execute(cli.getOptionValue(Cli.VERIFY_OPTION));
        } else if (cli.hasOption(Cli.RESET_OPTION)) {
            new ResetCmd().execute(cli.getOptionValue(Cli.RESET_OPTION));
        } else {
            CliPrinter.print("Unknown command. Use -h for help.");
        }
    }

}