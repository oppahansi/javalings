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
        char concat = (char) (x + y);
        var multipleChars = "" + x + y; // Do not change this line

        /* Modify above */
        System.out.println("""
                x has the value: %s
                    x is of type: %s

                y has the value: %s
                    y is of type: %s

                x + y = %s
                    concat is of type: %s

                "" + x + y = %s
                    multipleChars is of type: %s

                Did you expect these results?
                """.formatted(x, getType(x), y, getType(y), concat, getType(concat), multipleChars,
                getType(multipleChars)));
    }

    private static String getType(Object value) {
        return value.getClass().getName();
    }
}
