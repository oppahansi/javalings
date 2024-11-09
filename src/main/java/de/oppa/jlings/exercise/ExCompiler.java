package de.oppa.jlings.exercise;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.tools.ToolProvider;

public class ExCompiler {
    public CompileResult compile(Exercise exercise) {
        var compileResults = new ArrayList<String>();

        for (var path : exercise.files()) {
            var result = compile(path);

            if (!result.isBlank()) {
                compileResults.add(result);
            }
        }

        return new CompileResult(compileResults);
    }

    private String compile(String exercisePath) {
        try (var outputStream = new ByteArrayOutputStream()) {

            var compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, outputStream, "-d", "build", "-cp", "src", exercisePath);

            return outputStream.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
