package de.oppa.jlings.exercise;

public record RunResult (String result, String error) {
    public boolean hasError() {
        return !error.isEmpty();
    }
}
