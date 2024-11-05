package ApptTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApptOutcome {
    private Map<String, List<String>> patientAppointmentOutcomes = new HashMap<>();
    private Map<String, String> prescriptionStatus = new HashMap<>();

    // Function for a patient to view their past appointment outcome records
    public List<String> viewPastAppointmentOutcomes(String patientId) {
        return patientAppointmentOutcomes.getOrDefault(patientId, new ArrayList<>());
    }

    // Function for a doctor to record an appointment outcome
    public void recordAppointmentOutcome(String patientId, String outcome) {
        List<String> outcomes = patientAppointmentOutcomes.getOrDefault(patientId, new ArrayList<>());
        outcomes.add(outcome);
        patientAppointmentOutcomes.put(patientId, outcomes);
        System.out.println("Recorded appointment outcome for patient " + patientId + ": " + outcome);
    }

    // Function for a pharmacist to view a patient's appointment outcome record
    public List<String> viewAppointmentOutcome(String patientId) {
        return viewPastAppointmentOutcomes(patientId);  // Reuse the patient method for viewing
    }

    // Function for a pharmacist to update prescription status
    public void updatePrescriptionStatus(String prescriptionId, String status) {
        prescriptionStatus.put(prescriptionId, status);
        System.out.println("Updated prescription " + prescriptionId + " to status: " + status);
    }

    // Additional function to view prescription status (for demonstration purposes)
    public String getPrescriptionStatus(String prescriptionId) {
        return prescriptionStatus.getOrDefault(prescriptionId, "No record found.");
    }
}
