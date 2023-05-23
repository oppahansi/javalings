package exams;

/**
 * Exam2.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Bonus challenge: Use the var keyword for all variables.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Exam2 {

  public static void main(String[] args) {
    /* Modify below */

    // Create a 'byte' variable named 'smallest' with the value '100'

    // Create a 'short' variable named 'small' with the value '30000'

    // Create an 'int' variable named 'medium' with the value '1000000000'

    // Create a 'float' variable named 'precision' with the value '42.42'

    // Create a 'double' variable named 'doublePrecision' with the value '123.321'

    // Create a 'long' variable named 'hugeNumber' with the value '123456789987654321'

    // Create a 'boolean' variable named 'trueFalse' with the value 'true'

    // Create a 'char' variable named 'character' with the value 'P'

    // Create a 'String' variable named 'text' with the value 'Hello there!'


    /* Modify above */
    System.out.println("smallest has the value : " + smallest + ", type: " + getType(smallest));
    System.out.println("small has the value : " + small + ", type: " + getType(small));
    System.out.println("medium has the value : " + medium + ", type: " + getType(medium));
    System.out.println("precision has the value : " + precision + ", type: " + getType(precision));
    System.out.println("doublePrecision has the value : " + doublePrecision + ", type: " + getType(doublePrecision));
    System.out.println("hugeNumber has the value : " + hugeNumber + ", type: " + getType(hugeNumber));
    System.out.println("trueFalse has the value : " + trueFalse + ", type: " + getType(trueFalse));
    System.out.println("character has the value : " + character + ", type: " + getType(character));
    System.out.println("text has the value : " + text + ", type: " + getType(text));
  }

  private static String getType(Object value) {
    return value.getClass().getName();
  }
}
