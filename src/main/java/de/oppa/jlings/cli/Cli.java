package de.oppa.jlings.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli {

        public final static String HELP_OPTION = "h";
        public final static String WATCH_OPTION = "w";
        public final static String LIST_OPTION = "l";
        public final static String RUN_OPTION = "r";
        public final static String NEXT_OPTION = "n";
        public final static String HINT_OPTION = "hint";
        public final static String GOOGLE_OPTION = "g";
        public final static String VERIFY_OPTION = "v";
        public final static String RESET_OPTION = "reset";

        public static CommandLine getCli(String[] args) {
                try {
                        var parser = new DefaultParser();
                        return parser.parse(getOptions(), args);
                } catch (ParseException exception) {
                        throw new RuntimeException(exception);
                }
        }

        public static Options getOptions() {
                var options = new Options();

                var help = Option.builder("h")
                                .longOpt("help")
                                .desc("Prints this help")
                                .build();

                var watch = Option.builder("w")
                                .longOpt("watch")
                                .desc("Start watching exercises")
                                .hasArg()
                                .optionalArg(true)
                                .argName("all|exerciseName")
                                .build();

                var list = Option.builder("l")
                                .longOpt("list")
                                .desc("List all available exercises")
                                .hasArg()
                                .optionalArg(true)
                                .argName("all|unsolved|solved|extended")
                                .build();

                var run = Option.builder("r")
                                .longOpt("run")
                                .desc("Run an exercise by name")
                                .hasArg()
                                .argName("exerciseName")
                                .build();

                var next = Option.builder("n")
                                .longOpt("next")
                                .desc("Run the next unsolved exercise")
                                .build();

                var hint = Option.builder("hint")
                                .longOpt(HINT_OPTION)
                                .desc("Prints hint for exercise")
                                .hasArg()
                                .argName("exerciseName")
                                .build();

                var google = Option.builder("g")
                                .longOpt(GOOGLE_OPTION)
                                .desc("Prints what to google for help")
                                .hasArg()
                                .argName("exerciseName")
                                .build();

                var verify = Option.builder(VERIFY_OPTION)
                                .longOpt("verify")
                                .desc("Verify exercises")
                                .hasArg()
                                .optionalArg(true)
                                .argName("all|exerciseName")
                                .build();

                var reset = Option.builder("reset")
                                .longOpt(RESET_OPTION)
                                .desc("Reset exercises")
                                .hasArgs()
                                .optionalArg(true)
                                .argName("all|exerciseName")
                                .build();

                options.addOption(help);
                options.addOption(watch);
                options.addOption(list);
                options.addOption(run);
                options.addOption(next);
                options.addOption(hint);
                options.addOption(google);
                options.addOption(verify);
                options.addOption(reset);

                return options;
        }
}
