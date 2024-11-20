package FileManager;

import java.io.IOException;
import java.util.List;

/**
 * The {@code Load} interface defines a contract for loading data from a source.
 * Implementing classes must provide a custom implementation of the {@code load}
 * method
 * to retrieve and return data as a list of objects.
 */

public interface Load {

    /**
     * Loads data from a source and returns it as a list of objects.
     *
     * @return a {@code List<?>} containing the loaded data
     * @throws IOException if an error occurs while accessing or reading the data
     *                     source
     */
    List<?> load() throws IOException;
}
