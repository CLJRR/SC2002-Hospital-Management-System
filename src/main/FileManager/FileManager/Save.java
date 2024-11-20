package FileManager;

import java.io.IOException;
import java.util.List;

/**
 * The {@code Save} interface defines a contract for saving data to a specific
 * destination.
 * Implementing classes must provide a custom implementation of the {@code save}
 * method
 * to handle the storage of a list of objects.
 */

public interface Save {

    /**
     * Saves the provided list of objects to a destination.
     *
     * @param al a {@code List<?>} containing the objects to be saved
     * @throws IOException if an error occurs while saving the data
     */
    void save(List<?> al) throws IOException;
}
