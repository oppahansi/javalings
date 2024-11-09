package de.oppa.jlings.exercise;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import de.oppa.jlings.cfg.Cfg;

public class ExManager {
    private static ExManager instance;

    private final Map<String, ExState> states;
    private final Map<Integer, String> orderToName;

    private ExManager() {
        states = new HashMap<>();
        orderToName = new HashMap<>();

        readExerciseOrder();
        readExercises();
    }

    public static synchronized ExManager getInstance() {
        if (instance == null) {
            instance = new ExManager();
        }
        return instance;
    }

    public Exercise getExercise(int order) {
        if (!orderToName.containsKey(order)) {
            throw new IllegalArgumentException("Exercise not found in order: " + order);
        }

        return getExercise(orderToName.get(order));
    }

    public Exercise getExercise(String name) {
        if (!states.containsKey(name)) {
            throw new IllegalArgumentException("Exercise not found: " + name);
        }

        return states.get(name).getExercise();
    }

    public List<ExState> getStates() {
        return states.values().stream()
                .sorted(Comparator.comparingInt(ExState::getOrder))
                .toList();
    }

    public ExState getState(int order) {
        return states.get(orderToName.get(order));
    }

    public ExState getState(String name) {
        return states.get(name);
    }

    public boolean isSolved(int order) {
        return states.get(orderToName.get(order)).isSolved();
    }

    public boolean isSolved(String name) {
        return states.get(name).isSolved();
    }

    public List<Exercise> getSolved() {
        return states.values().stream()
                .sorted(Comparator.comparingInt(ExState::getOrder))
                .filter(ExState::isSolved)
                .map(ExState::getExercise)
                .toList();
    }

    public List<Exercise> getUnsolved() {
        return states.values().stream()
                .sorted(Comparator.comparingInt(ExState::getOrder))
                .filter(state -> !state.isSolved())
                .map(ExState::getExercise)
                .toList();
    }

    public List<Exercise> getExercises() {
        return states.values().stream()
                .sorted(Comparator.comparingInt(ExState::getOrder))
                .map(ExState::getExercise)
                .toList();
    }

    public int getOrder(String name) {
        return orderToName.entrySet().stream()
                .filter(entry -> entry.getValue().equals(name))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found in order: " + name));
    }

    @SuppressWarnings("unchecked")
    private void readExerciseOrder() {
        var mapper = new ObjectMapper(new YAMLFactory());
        var classLoader = getClass().getClassLoader();
        Path orderPath;

        try {
            var url = classLoader.getResource("exercises/exercise_order.yml");

            if (url == null) {
                throw new IllegalStateException("Error locating exercise order file");
            }

            orderPath = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Error locating exercise order file", e);
        }

        try (var inputStream = Files.newInputStream(orderPath)) {
            var orderMap = (Map<String, List<String>>) mapper.readValue(inputStream, Map.class);
            var orderList = orderMap.get("order");

            for (int i = 0; i < orderList.size(); i++) {
                orderToName.put(i, orderList.get(i));
            }
        } catch (RuntimeException | IOException e) {
            throw new IllegalStateException("Error reading exercise order file", e);
        }
    }

    private void readExercises() {
        var mapper = new ObjectMapper(new YAMLFactory());
        var classLoader = getClass().getClassLoader();
        Path exercisesPath;

        try {
            exercisesPath = Paths.get(Objects.requireNonNull(classLoader.getResource("exercises")).toURI());
        } catch (Exception e) {
            throw new IllegalStateException("Error locating exercises directory", e);
        }

        try (var paths = Files.walk(exercisesPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".yml") && !path.toString().contains("exercise_order"))
                    .forEach(path -> {
                        try (var inputStream = Files.newInputStream(path)) {
                            if (inputStream.available() == 0) {
                                throw new IllegalStateException("Empty exercise file: " + path);
                            }

                            var exercise = mapper.readValue(inputStream, Exercise.class);

                            states.put(exercise.name(),
                                    new ExState(exercise, new CompileResult(null), new RunResult(null, null),
                                            getOrder(exercise.name()), false, false));

                        } catch (RuntimeException | IOException e) {
                            throw new IllegalStateException("Error reading exercise file", e);
                        }
                    });
        } catch (RuntimeException | IOException e) {
            throw new IllegalStateException("Error reading exercise files", e);
        }
    }

    public boolean isDone(String name) {
        var exercise = getExercise(name);
        var exercisePath = Paths.get(exercise.files().getFirst());

        try {
            var content = Files.readString(exercisePath);

            if (content == null || content.isBlank()) {
                throw new IllegalStateException("Empty exercise file: " + exercisePath);
            }

            return !content.contains(Cfg.I_AM_NOT_DONE);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading exercise file: " + exercisePath, e);
        }
    }
}