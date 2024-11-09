package de.oppa.jlings.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExRunner {

    public RunResult run(Exercise exercise) {
        try {
            var cmds = "java -cp build %s".formatted(exercise.files().getFirst()).split(" ");
            var process = Runtime.getRuntime().exec(cmds);

            var result = new RunResult(getProcessConsoleOutput(process.getInputStream()),
                    getProcessConsoleOutput(process.getErrorStream()));

            process.waitFor();

            return result;
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException("Error running exercise", e);
        }
    }

    private static String getProcessConsoleOutput(InputStream ins) throws IOException {
        var result = new StringBuilder();
        var in = new BufferedReader(new InputStreamReader(ins));

        String line;
        while ((line = in.readLine()) != null) {
            result.append("%s%n".formatted(line));
        }

        return result.toString();
    }
}
