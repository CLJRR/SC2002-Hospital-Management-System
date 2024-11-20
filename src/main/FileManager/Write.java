package FileManager;

import java.io.IOException;
import java.util.List;

/**
 * The {@code Write} interface defines a contract for writing a list of string
 * data
 * to a specified destination. Implementing classes must provide a custom
 * implementation
 * of the {@code write} method to handle the writing process.
 */

public interface Write {

    /**
     * Writes a list of string data to a specified destination.
     *
     * @param data a {@code List<String>} containing the data to be written
     * @throws IOException if an error occurs during the writing process
     */
    void write(List<String> data) throws IOException;
}
