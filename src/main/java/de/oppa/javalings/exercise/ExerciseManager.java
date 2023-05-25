package de.oppa.javalings.exercise;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.oppa.javalings.cfg.Config;
import de.oppa.javalings.cli.CliRunner;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class ExerciseManager {

  private ExerciseManager() {

  }

  public static List<Exercise> readExercises() throws IOException {
    List<Exercise> exercises = new ArrayList<>();
    File jsonFile = new File(Config.EXERCISES_INFO_PATH);
    ObjectMapper mapper = new ObjectMapper();
    List<ExerciseInfo> exerciseInfos = mapper.readValue(jsonFile, new TypeReference<>() {
    });

    for (ExerciseInfo exerciseInfo : exerciseInfos) {
      String name = exerciseInfo.name();
      String packageName = exerciseInfo.packageName();
      String path = exerciseInfo.path();
      String packagedName = packageName + "." + name.substring(0, 1).toUpperCase() + name.substring(1);
      boolean done = !FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8).contains(Config.I_AM_NOT_DONE);

      Exercise newExercise = new Exercise(name, path, exerciseInfo.hint(), exerciseInfo.google(), packagedName, done);

      compileAndSetResultingState(newExercise);

      exercises.add(newExercise);
    }

    return exercises;
  }

  public static void refreshExercises(List<Exercise> exercises) throws IOException {
    for (Exercise exercise : exercises) {
      File exerciseFile = new File(exercise.getPath());
      String exerciseFileContent = FileUtils.readFileToString(exerciseFile, StandardCharsets.UTF_8);

      exercise.setDone(!exerciseFileContent.contains(Config.I_AM_NOT_DONE));

      compileAndSetResultingState(exercise);
    }
  }

  private static void compileAndSetResultingState(Exercise exercise) throws IOException {
    String compileOutput = CliRunner.compile(exercise.getPath());

    exercise.setCompiling(compileOutput.isEmpty());
    exercise.setCompileOutput(compileOutput);
  }

  public static List<String> collectExerciseNames(List<Exercise> exercises) {
    return exercises.stream().map(exercise -> exercise.getName().toLowerCase()).toList();
  }
}
