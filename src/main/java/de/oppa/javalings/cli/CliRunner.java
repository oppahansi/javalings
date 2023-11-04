package de.oppa.javalings.cli;

import de.oppa.javalings.cfg.Config;
import de.oppa.javalings.exercise.Exercise;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CliRunner {

  private CliRunner() {

  }

  public static String compile(String exercisePath) throws IOException {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      compiler.run(null, null, outputStream, "-d", "build", "-cp", "src", exercisePath);

      return outputStream.toString();
    }
  }

  public static void run(Exercise exercise) throws IOException, InterruptedException {
    Process process = Runtime.getRuntime()
        .exec(Config.RUN_EXERCISE_CMD.formatted(exercise.getPackagedName()).split(" "));

    exercise.setRunOutput(getProcessConsoleOutput(process.getErrorStream()));
    exercise.setRunOutput(getProcessConsoleOutput(process.getInputStream()));

    process.waitFor();
  }

  public static void reset(Exercise exercise) throws IOException, InterruptedException {
    Process process = Runtime.getRuntime().exec(String.format(Config.RESET_EXERCISE_CMD, exercise.getPath()));
    process.waitFor();
  }

  public static void resetAll() throws IOException, InterruptedException {
    String[] cmds = Config.RESET_EXERCISE_CMD.formatted(Config.EXERCISES_JAVA_BASE_PATH).split(" ");

    Process process = Runtime.getRuntime().exec(cmds);
    process.waitFor();
  }

  private static String getProcessConsoleOutput(InputStream ins) throws IOException {
    String line;
    StringBuilder result = new StringBuilder();
    BufferedReader in = new BufferedReader(new InputStreamReader(ins));

    while ((line = in.readLine()) != null) {
      result.append("%s%n".formatted(line));
    }

    return result.toString();
  }
}
