package variables;

/**
 * Variables2.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Variables2 {

    public static void main(String[] args) {
        /* Modify below */

        // Create a 'float' variable named 'precision' with the value '42.42F'

        // Create a 'double' variable named 'doublePrecision' with the value '123.321D'

        // Create a 'long' variable named 'hugeNumber' with the value
        // '123456789987654321L'

        /* Modify above */
        System.out.println("""
                floatingPointNumber has the value : %f
                doubleFloatingPointNumber has the value : %f
                hugeNumber has the value : %e
                """.formatted(precision, doublePrecision, hugeNumber));
    }
}
