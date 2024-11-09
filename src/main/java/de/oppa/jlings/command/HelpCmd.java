package de.oppa.jlings.command;

import org.apache.commons.cli.HelpFormatter;

import de.oppa.jlings.cli.Cli;

public class HelpCmd implements Cmd {
  @Override
  public void execute() {
    var options = Cli.getOptions();
    var formatter = new HelpFormatter();

    formatter.setWidth(100);
    formatter.setLeftPadding(4);
    formatter.setDescPadding(8);
    formatter.setSyntaxPrefix("Usage: ");
    formatter.setOptionComparator(null);

    var header = "Javalings - An interactive learning tool for Java\n\n";
    var footer = """
        Examples:
          javalings -h
          javalings -l all
          javalings -r intro0
          javalings -n
          javalings -hint intro1
          javalings -g intro1
          javalings -v variables1
          javalings -reset
        """;

    formatter.printHelp("javalings", header, options, footer, true);
  }
}
