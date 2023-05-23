package datatypes.strings;

/**
 * Strings1.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Strings1 {

  public static void main(String[] args) {
    /* Modify below */

    // Create a 'String' variable named 'javalings' with the value 'javalings'

    // Create a 'String' variable named 'exercise' with the value 'exercise'

    String concat = javalings + exercise;
    String concatWithSpaces = javalings + " " + exercise;
    String concatFormatted = "%s %s".formatted(javalings, exercise);
    String concatFormattedOld = String.format("%s %s", javalings, exercise);

    /* Modify above */
    System.out.println("concat              = " + concat);
    System.out.println("concatWithSpaces    = " + concatWithSpaces);
    System.out.println("concatFormatted     = " + concatFormatted);
    System.out.println("concatFormattedOld  = " + concatFormattedOld);

  }
}
