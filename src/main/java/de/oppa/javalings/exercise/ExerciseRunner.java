package de.oppa.javalings.exercise;

import static de.oppa.javalings.Javalings.colorizeMessage;

import de.oppa.javalings.Javalings;
import de.oppa.javalings.cfg.Colors;
import de.oppa.javalings.cfg.Config;
import de.oppa.javalings.cli.CliRunner;
import de.oppa.javalings.exception.VerifyExerciseException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class ExerciseRunner {

  private final Exercise exercise;

  private FileAlterationMonitor monitor;

  public ExerciseRunner(Exercise exercise) {
    this.exercise = exercise;
  }

  public void watch() throws Exception {
    start();

    verify();
  }

  public void stop() throws Exception {
    if (monitor == null) {
      return;
    }

    monitor.stop();
    monitor = null;
  }

  public void verify() throws IOException, InterruptedException {
    Javalings.printProgress();

    exercise.setDone(!FileUtils.readFileToString(new File(exercise.getPath()), StandardCharsets.UTF_8)
        .contains(Config.I_AM_NOT_DONE));

    if (exercise.isDone()) {
      Javalings.printProgress();

      System.out.println("%s has been completed.%n%n".formatted(exercise.getName()).indent(2));
      System.out.println("Type next to continue with the next unsolved exercise." + Config.D_NEW_LINE);

      return;
    }

    System.out.printf("Compiling %s", exercise.getName());
    String compileOutput = CliRunner.compile(exercise.getPath());
    exercise.setCompiling(compileOutput.isEmpty());
    exercise.setCompileOutput(compileOutput);

    if (!exercise.isCompiling()) {
      System.out.println(colorizeMessage(" .. ERROR!" + Config.NEW_LINE, Colors.RED_B_BT));
      System.out.printf((colorizeMessage("%s", Colors.RED_B_BT)),
          exercise.getCompileOutput().indent(2) + Config.NEW_LINE);
      Javalings.printWatchModeCommandsHint();

      return;
    }

    System.out.println(colorizeMessage(" .. SUCCESS!" + Config.D_NEW_LINE, Colors.GREEN_B_BT));
    System.out.println(colorizeMessage(Config.COMPILING_SUCCESS, Colors.GREEN_B_BT) + Config.NEW_LINE);

    CliRunner.run(exercise);

    System.out.println(
        colorizeMessage(Config.EXERCISE_OUTPUT, Colors.GREEN_B_BT).formatted(exercise.getRunOutput().trim())
            + Config.NEW_LINE);

    if (!exercise.isDone()) {
      System.out.println(colorizeMessage(Config.REMOVE_I_AM_NOT_DONE, Colors.YELLOW_B_BT) + Config.NEW_LINE);
      Javalings.printWatchModeCommandsHint();
    }
  }

  public Exercise getExercise() {
    return exercise;
  }

  private void start() throws Exception {
    monitor = new FileAlterationMonitor(Config.MONITOR_TIMEOUT);

    File exerciseFile = new File(exercise.getPath());
    FileAlterationObserver observer = new FileAlterationObserver(exerciseFile.getParent());
    FileAlterationListener listener = new FileAlterationListenerAdaptor() {
      @Override
      public void onFileCreate(File file) {
        // ignored
      }

      @Override
      public void onFileDelete(File file) {
        // ignored
      }

      @Override
      public void onFileChange(File file) {
        try {
          verify();
        } catch (Exception e) {
          throw new VerifyExerciseException(e.getMessage());
        }
      }
    };

    observer.addListener(listener);
    monitor.addObserver(observer);

    monitor.start();
  }


}
