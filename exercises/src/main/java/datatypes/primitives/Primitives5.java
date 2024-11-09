package datatypes.primitives;

/**
 * Primitives5.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives5 {

    public static void main(String[] args) {
        /* Modify below */

        // Create a 'float' variable named 'x' with the value '21.8F'

        // Create a 'float' variable named 'y' with the value '7.4F'

        // Fix this
        float sum = x + y;
        float rest = x - y;
        float div = x / y;
        float prod = x * y;

        /* Modify above */
        System.out.println("""
                x + y = %d
                x - y = %d
                x / y = %d
                x * y = %d

                Some floating point operations can result in numbers
                with strange decimal places.

                You have to keep that in mind when doing calculations.
                """.formatted(sum, rest, div, prod));
    }
}
