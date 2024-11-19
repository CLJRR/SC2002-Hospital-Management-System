/**
 * The {@code RequestService} class implements methods for handling the persistence
 * and retrieval of {@code Request} objects. It provides functionalities for loading,
 * saving, formatting, and converting request records between objects and text representation.
 * This class implements the {@code Load}, {@code Format}, {@code Save}, {@code Write}, 
 * and {@code toObject} interfaces.
 */
package RequestSystem;

import FileManager.*;
import enums.Flag;
import java.io.*;
import java.util.*;

public class RequestService implements Load, Format, Save, Write, toObject {

    private static final String fileName = "./data/requests.txt";

    /**
     * Loads request records from the specified file and converts them into a list of {@code Request} objects.
     *
     * @return a list of {@code Request} objects loaded from the file.
     * @throws IOException if there is an error reading the file or converting data.
     */
    @Override
    public List<?> load() throws IOException {
        List<Request> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Request request = (Request) toObject(line);
                data.add(request);
            }
        }
        return data;
    }

    /**
     * Saves a list of {@code Request} objects to the specified file in text format.
     *
     * @param list the list of {@code Request} objects to save.
     * @throws IOException if there is an error writing to the file or if the list contains invalid objects.
     */
    @Override
    public void save(List<?> list) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof Request) {
                String formattedString = format(obj);
                data.add(formattedString);
            } else {
                throw new IOException("List contains incorrect objects.");
            }
        }
        write(data);
    }

    /**
     * Writes a list of formatted text lines to the specified file.
     *
     * @param data a list of strings representing the formatted request data.
     * @throws IOException if there is an error writing to the file.
     */
    @Override
    public void write(List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    /**
     * Converts a {@code Request} object into a formatted string representation for storage.
     *
     * @param object the {@code Request} object to format.
     * @return a string representation of the {@code Request} object.
     * @throws IOException if the input object is not of type {@code Request}.
     */
    @Override
    public String format(Object object) throws IOException {
        if (object instanceof Request request) {
            return String.join(",",
                    request.getRequestId(),
                    request.getPharmId(),
                    request.getMedicationName(),
                    String.valueOf(request.getIncreaseStockBy()),
                    request.getNotes(),
                    request.getApprovedBy() == null ? "" : request.getApprovedBy(),
                    request.getFlag().toString());
        } else {
            throw new IOException("Invalid object type");
        }
    }

    /**
     * Converts a formatted string from the file into a {@code Request} object.
     *
     * @param string the formatted string representing a {@code Request}.
     * @return a {@code Request} object created from the string.
     * @throws IOException if the string format is invalid or cannot be parsed.
     */
    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        if (parts.length != 7) {
            throw new IOException("Invalid format.");
        }

        String requestId = parts[0];
        String pharmId = parts[1];
        String medicationName = parts[2];
        int increaseStockBy = Integer.parseInt(parts[3]);
        String notes = parts[4];
        String approvedBy = parts[5].isEmpty() ? null : parts[5];
        Flag flag = Flag.valueOf(parts[6].toUpperCase());

        return new Request(requestId, pharmId, medicationName, increaseStockBy, notes, approvedBy, flag);
    }
}
