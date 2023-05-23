package de.oppa.javalings.cfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

  public static final String HELP_OPT = "help";
  public static final String WATCH_OPT = "watch";
  public static final String LIST_OPT = "list";
  public static final String RUN_OPT = "run";
  public static final String HINT_OPT = "hint";
  public static final String GOOGLE_OPT = "google";
  public static final String RESET_OPT = "reset";
  public static final String NEXT_OPT = "next";
  public static final String QUIT_OPT = "quit";
  public static final String EXIT_OPT = "exit";
  public static final String CLEAR_OPT = "clear";
  public static final String ALL_OPT = "all";
  public static final long MONITOR_TIMEOUT = 2000;
  public static final String I_AM_NOT_DONE = "// I AM NOT DONE";
  public static final String RUN_EXERCISE_CMD = "java -cp build %s";
  public static final String RESET_EXERCISE_CMD = "git checkout HEAD -- %s";
  public static final String EXCERCISES_INFO_PATH = "src/main/resources/exercises.json";
  public static final String EXERCISES_JAVA_BASE_PATH = "exercises/src/main/java/";
  public static final String USAGE_DESCRIPTION = """
      Javalings usage:
        Windows
          .\\javalings.bat <option> <option>
        
        Linux / MacOS
          ./javalings.sh <option> <option>
            
      Available options, required to run:
        help                : Print all available options.
        watch               : Run exercises in the recommended order.
        list                : List all unsolved exercises.
        run <option>        : Run the specified exercise.
        reset <option>      : Resets the specified exercise.
        
        Example             : .\\javalings.bat run Intro1
        
      Available 'run' sub options:
        <exercise>          : Run the specified exercise.
        next                : Run the next unsolved exercise.
        
        Example             : ./javalings.bat run Intro1

      Available 'reset' sub options:
        <exercise>          : Reset the specified exercise.
        all                 : Resets all exercises.
        
        Example             : ./javalings.sh reset Intro1
      """;
  public static final String WATCH_COMMANDS_HINT =
      """
          Type 'help' to see available watch mode commands.
          Type 'hint' for a hint or 'google' for search terms.
          """;
  public static final String WATCH_MODE_USAGE_DESCRIPTION = """
      Commands available to you in watch mode:
        help                : Displays this help message.
        hint                : Prints a hint for the current exercise.
        google              : Prints examples of what to google to find a solution.
        next                : Run the next unsolved exercise.
        reset               : Resets the current exercise.
        clear               : Clears the screen.
        quit or exit        : Stops the exercise and quits javalings.

      Watch mode automatically re-evaluates the current exercise
      when you edit a file's contents.
      """;
  public static final String WELCOME =
      """
          Thanks for installing javalings!
                
          Is this your first time? Don't worry, javalings was made for beginners! We are
          going to teach you a lot of things about Rust, but before we can get
          started, here's a couple of notes about how Javalings operates:
                
          1. The central concept behind Rustlings is that you solve exercises. These
             exercises usually have some sort of syntax error in them, which will cause
             them to fail compilation or testing. Sometimes there's a logic error instead
             of a syntax error. No matter what error, it's your job to find it and fix it!
             You'll know when you fixed it because then, the exercise will compile and
             Javalings will be able to move on to the next exercise.
          2. If you run Javalings in watch mode (which we recommend), it'll automatically
             start with the first unsolved exercise. Don't get confused by an error message popping
             up as soon as you run Javalings! This is part of the exercise that you're
             supposed to solve, so open the exercise file in an editor and start your
             detective work!
          3. If you're stuck on an exercise, there is a helpful hint you can view by typing
             'hint' (in watch mode), or 'google'. 'Google?' you might ask!?. Yes, it is crucial
             to be able to use search engines and find hints or solutions on your own.
          4. If an exercise doesn't make sense to you, feel free to open an issue on GitHub!
             (https://github.com/oppahansi/javalings/issues/new/choose). We look at every issue,
             and sometimes, other learners do too so you can help each other out!
                
          Got all that? Great! To get started, run
          
          `.\\javalings.bat watch` on Windows or
          `./javalings.bat watch` on MacOs / Linux
          
          in order to get the first unsolved exercise.
          
          Make sure to have your editor open!
          """;
  public static final String COMPILING_SUCCESS_MESSAGE = "=== IT IS COMPILING! ===";
  public static final String EXERCISE_OUTPUT_TEMPLATE =
      """
          Output:
          ========================
                    
          %s
                    
          ========================

          """;
  public static final String REMOVE_I_AM_NOT_DONE =
      "Remove --  " + I_AM_NOT_DONE + "  -- to complete the exercise.";
  public static final String FIRST_TRY_ON_YOUR_OWN = "!! First try to figure it out completely by yourself. !!";
  public static final String WHAT_TO_GOOGLE = "Examples what to google:";
  public static final String LINE_BREAK = "%n".formatted();
  public static final String DOUBLE_LINE_BREAK = "%n%n".formatted();
  private static final String[] OPTIONS_ARRAY = new String[]{
      HELP_OPT,
      WATCH_OPT,
      LIST_OPT,
      RUN_OPT,
      HINT_OPT,
      RESET_OPT
  };
  public static final List<String> OPTIONS = new ArrayList<>(Arrays.stream(OPTIONS_ARRAY).toList());
  private static final String[] SUB_OPTIONS_ARRAY = new String[]{NEXT_OPT, ALL_OPT};
  public static final List<String> SUB_OPTIONS = new ArrayList<>(Arrays.stream(SUB_OPTIONS_ARRAY).toList());

  private Config() {
  }
}
