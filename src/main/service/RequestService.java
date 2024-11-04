package service;

import entity.Request;
import enums.Flag;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RequestService {

    private static final String FILE_NAME = "./data/requests.txt";

    public List<Request> loadAll() {
        List<Request> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String input;
            while ((input = reader.readLine()) != null) {
                Request request = toObject(input);
                if (request != null) {
                    requests.add(request);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return requests;
    }

    public Request getByRequestId(String id) {
        List<Request> requests = loadAll();
        for (Request request : requests) {
            if (request.getRequestId().equals(id)) {
                return request;
            }
        }
        System.out.println("Request ID: " + id + " not found.");
        return null;  // Return null if request not found

    }

// Save a Request to the file if requestId is not duplicate
    public void save(Request request) {
        List<Request> requests = loadAll();

        // Check for duplicates by requestId
        if (isDuplicateId(request.getRequestId(), requests)) {
            System.out.println("Request ID: " + request.getRequestId() + " already exists. Cannot add duplicate.");
            return;
        }

        // If no duplicate, save the request
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(format(request));
            writer.newLine();
            System.out.println("Request record saved: " + request.getRequestId());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Delete a Request by requestId
    public void deleteByRequestId(String requestId) {
        List<Request> requests = loadAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Request request : requests) {
                if (!request.getRequestId().equals(requestId)) {
                    writer.write(format(request));
                    writer.newLine();
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        if (found) {
            System.out.println("Request ID: " + requestId + " has been deleted.");
        } else {
            System.out.println("Request ID: " + requestId + " not found.");
        }
    }

    // Delete all Request records
    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            System.out.println("All request records have been deleted.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private boolean isDuplicateId(String requestId, List<Request> requests) {
        for (Request request : requests) {
            if (request.getRequestId().equals(requestId)) {
                return true;
            }
        }
        return false;
    }

    private String format(Request request) {
        return String.join(",",
                request.getRequestId(),
                request.getPharmId(),
                request.getMedicationName(),
                String.valueOf(request.getIncreaseStockBy()),
                request.getNotes(),
                request.getFlag().name());
    }

    //==============================================================
    private Request toObject(String input) {
        String[] data = input.split(",");
        if (data.length == 6) {
            try {
                String requestId = data[0];
                String pharmId = data[1];
                String medicationName = data[2];
                int increaseStockBy = Integer.parseInt(data[3]);
                String notes = data[4];
                Flag flag = Flag.valueOf(data[5].toUpperCase());
                return new Request(requestId, pharmId, medicationName, increaseStockBy, notes, flag);
            } catch (Exception e) {
                System.err.println("Error parsing request data: " + input + " - " + e.getMessage());
            }
        } else {
            System.err.println("Invalid data format: " + input);
        }
        return null;
    }
}
