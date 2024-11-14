package RequestSystem;
import FileManager.*;
import enums.*;
import java.io.*;
import java.util.*;

public class RequestService implements Load, Format, Save, Write, toObject {

    private static final String fileName = "./data/requests.txt";

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

    @Override
    public void write(List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    @Override
    public String format(Object object) throws IOException {
        if (object instanceof Request request) {
            return String.join(",",
                    request.getRequestId(),
                    request.getPharmId(),
                    request.getMedicationName(),
                    String.valueOf(request.getIncreaseStockBy()),
                    request.getNotes(),
                    request.getFlag().toString()
            );
        } else {
            throw new IOException("Invalid object type");
        }
    }

    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");
        if (parts.length != 6) {
            throw new IOException("Invalid format.");
        }

        String requestId = parts[0];
        String pharmId = parts[1];
        String medicationName = parts[2];
        int increaseStockBy = Integer.parseInt(parts[3]);
        String notes = parts[4];
        Flag flag = Flag.valueOf(parts[5].toUpperCase());

        return new Request(requestId, pharmId, medicationName, increaseStockBy, notes, flag);
    }
}
