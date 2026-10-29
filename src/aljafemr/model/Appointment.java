package aljafemr.model;

// Class imports
import aljafemr.exception.InvalidDataException;

// Util imports
import java.time.LocalDateTime;
import java.lang.Object;
import java.time.temporal.ChronoUnit;

import static aljafemr.model.Person.isAllDigitsFrom;
import static aljafemr.model.Person.validateStringInput;

/**
 * Appointment uses state from AppointmentStatus, and contains appointment details.
 */

public class Appointment {
    // Fields
    private final String appointmentId;
    private final Patient patient;
    private final Doctor doctor;
    private LocalDateTime dateTime;
    private final String reason;
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    // Variables
    private final int REASON_LIMIT = 100;

    /**
     * Constructor creates a SCHEDULED appointment object after validating the input fields.
     * @param appointmentId Required, prefix 'A', 4 characters long, last 3 must be digits, immutable.
     * @param patient Required.
     * @param doctor Required.
     * @param dateTime Required, truncated to whole minutes (seconds and nanoseconds set to zero), past and future dates both accepted
     * @param reason Required, limited to 100 characters.
     * status is not a constructor parameter, always starts as SCHEDULED, no public setter.
     *
     * @throws InvalidDataException if any fields above fail their rules. No object is created.
     */

    // Parameterized constructor
    public Appointment(String appointmentId, Patient patient, Doctor doctor, LocalDateTime dateTime, String reason) throws InvalidDataException {
        validateAppointmentId(appointmentId);
        objectNullCheck(patient, "Patient");
        objectNullCheck(doctor, "Doctor");
        LocalDateTime truncatedDateTime = truncateToMinutes(dateTime);
        validateDateTime(truncatedDateTime);
        validateStringInput(reason, "Reason", REASON_LIMIT);

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = truncatedDateTime;
        this.reason = reason;
    }

    // Getters
    public String getAppointmentId(){ return this.appointmentId; }
    public Patient getPatient(){ return this.patient; }
    public Doctor getDoctor(){ return this.doctor; }
    public LocalDateTime getDateTime(){ return this.dateTime; }
    public String getReason(){ return this.reason; }
    public AppointmentStatus getStatus(){ return this.status; }

    // Private helper methods
    private void validateAppointmentId(String value) throws InvalidDataException {
        if(value.length() != 4 || value.charAt(0) != 'A'){
            throw new InvalidDataException("Invalid ID format: Must be 4 characters long beginning with 'A'.");
        }
        if (!isAllDigitsFrom(value, 1)) {
            throw new InvalidDataException("Invalid ID format: Last 3 characters must be digits.");
        }
    }
    private void objectNullCheck(Object value, String fieldName) throws InvalidDataException{
        if (value == null){
            throw new InvalidDataException(fieldName + " is required.");
        }
    }
    private LocalDateTime truncateToMinutes(LocalDateTime value){
        return value.truncatedTo(ChronoUnit.MINUTES);
    }
    private void validateDateTime(LocalDateTime value) throws InvalidDataException {
        if (value == null){
            throw new InvalidDataException("Date/Time is required.");
        }
        if (!value.equals(value.truncatedTo(ChronoUnit.MINUTES))){
            throw new InvalidDataException("Invalid date/time format. Must be year-mon-day hr-min.");
        }
    }
    private void pastDateTimeCheck(LocalDateTime value) throws InvalidDataException {
        LocalDateTime today = LocalDateTime.now();
        if (value.isBefore(today)){
            throw new InvalidDataException("Date/time cannot be before today.");
        }
    }
    private void scheduledStatusCheck(AppointmentStatus value, String statusAction) throws InvalidDataException {
        if (value != AppointmentStatus.SCHEDULED){
            throw new InvalidDataException("cannot " + statusAction + " a completed or cancelled appointment");
        }
    }

    // Public methods
    public void reschedule(LocalDateTime value) throws InvalidDataException {
        LocalDateTime truncatedDateTime = truncateToMinutes(value);
        validateDateTime(truncatedDateTime);
        scheduledStatusCheck(getStatus(), "reschedule");
        pastDateTimeCheck(truncatedDateTime);
        this.dateTime = truncatedDateTime;
    }
    public void cancel() throws InvalidDataException {
        scheduledStatusCheck(getStatus(), "cancel");
        this.status = AppointmentStatus.CANCELLED;
    }
    public void complete() throws InvalidDataException {
        scheduledStatusCheck(getStatus(), "complete");
        if (LocalDateTime.now().isBefore(this.dateTime)){
            throw new InvalidDataException("Cannot complete a future appointment.");
        }
        this.status = AppointmentStatus.COMPLETED;
    }
    public boolean isOccupyingSlot() throws InvalidDataException {
        return this.status == AppointmentStatus.SCHEDULED;
    }
}
