package de.oppa.javalings;

import de.oppa.javalings.cfg.Colors;
import de.oppa.javalings.cfg.Config;
import de.oppa.javalings.cli.CliRunner;
import de.oppa.javalings.exception.ResetExerciseException;
import de.oppa.javalings.exercise.Exercise;
import de.oppa.javalings.exercise.ExerciseManager;
import de.oppa.javalings.exercise.ExerciseRunner;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Javalings {

  private static final Scanner scanner = new Scanner(System.in);

  private static List<Exercise> exercises;
  private static List<String> exerciseNames;
  private static ExerciseRunner exerciseRunner;

  public static void main(String[] options) throws Exception {
    initExercises();
    lowerCaseOptions(options);
    verifyOptions(options);
    processOptions(options);
  }

  public static void printProgress() {
    clearConsole();

    float progressBarLength = 50;
    float doneExercises = exercises.stream().filter(Exercise::isDone).count();
    float percent = doneExercises != 0 ? (doneExercises / exercises.size()) : 0;
    float progress = progressBarLength * percent;
    float incompleteProgress = progress != 0 ? (int) progressBarLength - (int) progress : progressBarLength;

    String barChar = " ";
    String completeBar = barChar.repeat((int) progress);
    String incompleteBar = barChar.repeat((int) incompleteProgress);

    System.out.printf("Progress %d%% : [%s%s] %d/%d %n%n%n", (int) (percent * 100),
        colorizeMessage(completeBar, Colors.GREEN_BG), colorizeMessage(incompleteBar, Colors.BLACK_BG_BT),
        (int) doneExercises, exercises.size());
  }

  public static void clearConsole() {
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }

  public static String colorizeMessage(String message, String color) {
    return color + message + Colors.RESET;
  }

  public static void printWatchModeCommandsHint() {
    System.out.println(Config.WATCH_CMD_HINT);
  }

  private static void initExercises() throws IOException {
    exercises = ExerciseManager.readExercises();
    exerciseNames = ExerciseManager.collectExerciseNames(exercises);
  }

  private static void processOptions(String[] options) throws Exception {
    switch (options[0]) {
      case Config.HELP_OPT -> usage(false);
      case Config.LIST_OPT -> list();
      case Config.WATCH_OPT -> watch();
      case Config.RUN_OPT -> run(options[1]);
      case Config.RESET_OPT -> reset(options[1]);
      default -> exitInvalidOptions("Option '%s' not recognised.".formatted(options[0]));
    }
  }

  private static void usage(boolean watchMode) {
    if (watchMode) {
      System.out.println(Config.WATCH_MODE_USAGE);
    } else {
      System.out.println(Config.USAGE);
    }
  }

  private static void list() {
    printProgress();

    System.out.printf("%-15s | %-13s | %s%n", "Exercise", "compiles?",
        "is done?");
    System.out.println("-".repeat(43));
    for (Exercise exercise : exercises) {
      if (exercise.isDone() && exercise.isCompiling()) {
        continue;
      }

      System.out.printf("%-15s | %-13s | %s %n", exercise.getName(), exercise.isCompiling() ? "YES" : "NO",
          exercise.isDone() ? "YES" : "NO");
      System.out.println("- ".repeat(22));
    }

    System.out.println(Config.NEW_LINE);
  }

  private static void watch() throws Exception {
    Exercise exercise = findExercise();
    if (exercise == null) {
      System.out.println("No unsolved exercises found.");
      quit();
      System.exit(0);
    }

    run(exercise.getName());
  }

  private static void run(String exerciseName) throws Exception {
    Exercise exercise;

    if (exerciseName.equalsIgnoreCase(Config.NEXT_OPT)) {
      int nextExerciseIndex = findNextExerciseIndex();
      if (nextExerciseIndex >= exercises.size() && exerciseRunner != null) {
        System.out.println("All Exercises have been completed. Good job!!");
        quit();
        System.exit(0);
      }

      exercise = exercises.get(nextExerciseIndex);
    } else {
      exercise = findExercise(exerciseName);
    }

    if (exerciseRunner != null && exerciseName.equalsIgnoreCase(Config.NEXT_OPT)) {
      exerciseRunner.stop();
    }

    exerciseRunner = new ExerciseRunner(exercise);
    exerciseRunner.watch();

    waitForInput();
  }

  private static int findNextExerciseIndex() {
    if (exerciseRunner == null) {
      return IntStream.range(0, exercises.size()).filter(i -> !exercises.get(i).isDone()).findFirst().orElse(0);
    }

    int currentIndex = exercises.indexOf(exerciseRunner.getExercise());

    return IntStream.range(currentIndex + 1, exercises.size()).filter(i -> !exercises.get(i).isDone()).findFirst()
        .orElse(currentIndex);
  }

  private static void waitForInput() throws Exception {
    while (true) {
      String input = scanner.nextLine().trim();
      System.out.println();

      if (input == null || input.isEmpty() || input.isBlank()) {
        continue;
      }

      processWatchModeOptions(input);
    }
  }

  private static void processWatchModeOptions(String input) throws Exception {
    switch (input.toLowerCase()) {
      case Config.QUIT_OPT, Config.EXIT_OPT -> {
        quit();

        System.out.println("Quitting javalings.." + Config.NEW_LINE);
        System.exit(0);
      }
      case Config.NEXT_OPT -> next();
      case Config.CLEAR_OPT -> clear();
      case Config.HINT_OPT -> {
        System.out.println(colorizeMessage(Config.FIRST_TRY_ON_YOUR_OWN, Colors.CYAN_B_BT) + Config.NEW_LINE);
        exerciseRunner.getExercise().printHint();
      }
      case Config.GOOGLE_OPT -> {
        System.out.println(Config.WHAT_TO_GOOGLE + Config.NEW_LINE);
        exerciseRunner.getExercise().printGoogle();
      }
      case Config.RESET_OPT -> {
        System.out.println("Resetting exercise..");
        reset(exerciseRunner.getExercise());
        System.out.println("Resetting exercise done.");
      }
      default -> usage(true);
    }
  }

  private static void quit() throws Exception {
    System.out.println("Stopping exercises..");
    exerciseRunner.stop();
  }

  private static void next() throws Exception {
    if (exerciseRunner != null && !exerciseRunner.getExercise().isDone()) {
      System.out.printf("%s is not done yet.%n%n", exerciseRunner.getExercise().getName());
      return;
    }

    System.out.println("Refreshing exercises..");
    ExerciseManager.refreshExercises(exercises);

    run(Config.NEXT_OPT);
  }

  private static void clear() throws IOException, InterruptedException {
    clearConsole();
    exerciseRunner.verify();
  }

  private static void reset(String exerciseName) throws IOException, InterruptedException {
    if (exerciseName.equalsIgnoreCase(Config.ALL_OPT)) {
      System.out.println("Resetting all exercises..");
      reset();
      System.out.println("Resetting all exercises done.");
      return;
    }

    Exercise exercise = findExercise(exerciseName);

    if (exercise == null) {
      throw new ResetExerciseException("Could not find exercise by name: %s".formatted(exerciseName));
    }

    System.out.println("Resetting exercise..");
    reset(exercise);
    System.out.println("Resetting exercise done.");
  }

  private static void reset() throws IOException, InterruptedException {
    CliRunner.resetAll();
  }

  private static void reset(Exercise exercise) throws IOException, InterruptedException {
    CliRunner.reset(exercise);
  }

  private static Exercise findExercise() {
    return exercises.stream().filter(exercise -> !exercise.isDone()).findFirst().orElse(null);
  }

  private static Exercise findExercise(String exerciseName) {
    return exercises.stream().filter(exercise -> exercise.getName().equalsIgnoreCase(exerciseName)).findFirst()
        .orElse(null);
  }

  private static void lowerCaseOptions(String[] options) {
    IntStream.range(0, options.length).forEach(i -> options[i] = options[i].toLowerCase());
  }

  private static void verifyOptions(String[] options) {
    if (options.length == 0) {
      System.out.println(colorizeMessage(Config.WELCOME, Colors.GREEN_B_BT));
      System.exit(0);
    }

    int maxOptionsAmount = 2;

    if (options.length > maxOptionsAmount) {
      exitInvalidOptions("The amount of passed in options is not supported. "
          + "Options amount passed in is: %d%nmax %d options.".formatted(options.length, maxOptionsAmount));
    }

    if (!Config.OPTIONS.contains(options[0])) {
      exitInvalidOptions("Option '%s' not recognised.".formatted(options[0]));
    }

    if ((options[0].equalsIgnoreCase(Config.RUN_OPT) || options[0].equalsIgnoreCase(Config.RESET_OPT)) && options.length == 1) {
      exitInvalidOptions("Sub option for '%s' not provided.".formatted(options[0]));
    }

    if (options.length < maxOptionsAmount) {
      return;
    }

    if (Config.SUB_OPTIONS.contains(options[1])) {
      return;
    }

    if (!exerciseNames.contains(options[1])) {
      exitInvalidOptions("Sub option or exercise with the name '%s' not recognised.".formatted(options[1]));
    }
  }

  private static void exitInvalidOptions(String message) {
    System.out.println(
        colorizeMessage(message, Colors.RED) + Config.D_NEW_LINE + "Rerun with 'help' to see all options."
            + Config.D_NEW_LINE);
    System.exit(0);
  }
}
