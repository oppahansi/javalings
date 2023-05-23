package datatypes.primitives;

/**
 * Primitives8.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives8 {

  public static void main(String[] args) {
    /* Modify below */

    // Create a 'char' variable named 'x' with the value 'X'

    // Create a 'char' variable named 'y' with the value 'Y'

    // Fix this
    char concat = x + y;
    var multipleChars = "" + x + y; // Do not change this line

    /* Modify above */
    System.out.println("x has the value: " + x + "\n    x is of type: " + getType(x));
    System.out.println("\ny has the value: " + y + "\n    y is of type: " + getType(y));
    System.out.println("\nx + y = " + concat + "\n    concat is of type: " + getType(concat));
    System.out.println(
        "\n\"\" + x + y = " + multipleChars + "\n    multipleChars is of type: " + getType(multipleChars));
    System.out.println("\nDid you expect these results?");
  }

  private static String getType(Object value) {
    return value.getClass().getName();
  }
}
