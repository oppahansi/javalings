package de.oppa.jlings.exercise;

import java.io.IOException;

public class ExResetter {
    public void reset(Exercise exercise) {
        var cmdTemplate = "git checkout HEAD -- %s";

        for (var filePath : exercise.files()) {
            var cmds = cmdTemplate.formatted(filePath).split(" ");

            try {
                var process = Runtime.getRuntime().exec(cmds);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                throw new IllegalStateException("Error while resetting exercise: " + exercise.name(), e);
            }
        }
    }

    public void resetAll() {
        try {
            var process = Runtime.getRuntime().exec("git checkout HEAD -- exercises/src/main/java/".split(" "));
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException("Error while resetting all exercise.", e);
        }
    }
}
