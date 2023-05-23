package de.oppa.javalings.exercise;

public class Exercise {

  private final String name;
  private final String path;
  private final String hint;
  private final String google;
  private final String packagedName;

  private boolean compiling;
  private boolean done;
  private String compileOutput;
  private String runOutput;

  public Exercise(String name, String path, String hint, String google, String packageName, boolean done) {
    this.name = name;
    this.path = path;
    this.hint = hint;
    this.google = google;
    this.packagedName = packageName;
    this.done = done;
  }

  public String getName() {
    return name;
  }

  public String getPath() {
    return path;
  }

  public String getPackagedName() {
    return packagedName;
  }

  public boolean isCompiling() {
    return compiling;
  }

  public void setCompiling(boolean compiling) {
    this.compiling = compiling;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public String getCompileOutput() {
    return compileOutput;
  }

  public void setCompileOutput(String compileOutput) {
    this.compileOutput = compileOutput;
  }

  public String getRunOutput() {
    return runOutput;
  }

  public void setRunOutput(String runOutput) {
    this.runOutput = runOutput;
  }

  public void printHint() {
    System.out.printf("%s%n", hint.indent(2));
  }

  public void printGoogle() {
    System.out.printf("%s%n", google.indent(2));
  }
}
