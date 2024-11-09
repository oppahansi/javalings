package datatypes.primitives;

/**
 * Primitives7.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives7 {

    public static void main(String[] args) {
        /* Modify below */

        // Create a 'boolean' variable named 'x' with the value 'true'

        // Create a 'boolean' variable named 'y' with the value 'false'

        boolean and = x && y;
        boolean or = x || y;
        boolean notX = !x;
        boolean notY = !y;

        /* Modify above */
        System.out.println("""
                x has the value: %b
                y has the value: %b
                x && y = %b
                x || y = %b
                !x = %b
                !y = %b
                """.formatted(x, y, and, or, notX, notY));
    }
}
