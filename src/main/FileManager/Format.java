package FileManager;

import java.io.IOException;

/**
 * The {@code Format} interface defines a contract for formatting objects into
 * specific string representations. Implementing classes are required to provide
 * a custom implementation of the {@code format} method.
 */

public interface Format {

    /**
     * Formats the specified object into a string representation.
     *
     * @param object the object to be formatted
     * @return a string representation of the formatted object
     * @throws IOException if an error occurs during formatting
     */
    String format(Object object) throws IOException;
}
