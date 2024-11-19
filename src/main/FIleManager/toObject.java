package FileManager;

import java.io.IOException;

/**
 * The {@code toObject} interface defines a contract for converting a string
 * representation into an object. Implementing classes must provide a custom
 * implementation of the {@code toObject} method to handle the deserialization
 * or parsing process.
 */
public interface toObject {

    /**
     * Converts a string representation into an object.
     *
     * @param string the string representation to be converted
     * @return an {@code Object} created from the string representation
     * @throws IOException if an error occurs during the conversion process
     */
    Object toObject(String string) throws IOException;
}
