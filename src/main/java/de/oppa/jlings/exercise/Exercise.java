package de.oppa.jlings.exercise;

import java.util.List;

public record Exercise(
                String name,
                String packageName,
                String description,
                String hint,
                String google,
                List<String> files,
                String expectedOutput) {
}