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
 * Appointment contains appointment details.
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
    static final int REASON_LIMIT = 100;

    // Parameterized constructor
    /**
     * Constructor creates a SCHEDULED appointment object after validating the input fields.
     * @param appointmentId Required, prefix 'A', 4 characters long, last 3 must be digits, immutable.
     * @param patient Required.
     * @param doctor Required.
     * @param dateTime Required, truncated to whole minutes (seconds and nanoseconds set to zero), past and future dates both accepted
     * @param reason Required, limited to 100 characters.
     * @notes Status is not a constructor parameter, always starts as SCHEDULED, no public setter.
     *
     * @throws InvalidDataException if any fields above fail their rules. No object is created.
     */
    public Appointment(String appointmentId, Patient patient, Doctor doctor, LocalDateTime dateTime, String reason) throws InvalidDataException {
        validateAppointmentId(appointmentId);
        objectNullCheck(patient, "Patient");
        objectNullCheck(doctor, "Doctor");
        LocalDateTime truncatedDateTime = validateThenTruncate(dateTime);
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
        if (value == null || value.isBlank()){
            throw new InvalidDataException("ID is required.");
        }
        if(value.length() != 4 || value.charAt(0) != 'A'){
            throw new InvalidDataException("Invalid ID format: Must be 4 characters long beginning with 'A'.");
        }
        if (!isAllDigitsFrom(value, 1)) {
            throw new InvalidDataException("Invalid ID format: Last 3 characters must be digits.");
        }
    }
    private void objectNullCheck(Object value, String fieldName) throws InvalidDataException {
        if (value == null){
            throw new InvalidDataException(fieldName + " is required.");
        }
    }
    private LocalDateTime validateThenTruncate(LocalDateTime value) throws InvalidDataException {
        objectNullCheck(value, "Date/time");
        return value.truncatedTo(ChronoUnit.MINUTES);
    }
    private void pastDateTimeCheck(LocalDateTime value) throws InvalidDataException {
        LocalDateTime now = LocalDateTime.now();
        if (value.isBefore(now)){
            throw new InvalidDataException("Date/time cannot be before now.");
        }
    }
    private void scheduledStatusCheck(String statusAction) throws InvalidDataException {
        if (this.status != AppointmentStatus.SCHEDULED){
            throw new InvalidDataException("cannot " + statusAction + " a completed or cancelled appointment");
        }
    }

    // Public methods
    /**
     * Reschedules a SCHEDULED appointment.
     * Success: the appointment's date/time changes.
     * If an exception is thrown, the date/time is unchanged.
     * @param value is required.
     * @throws InvalidDataException when value fails its rule, or if appointment status is not SCHEDULED.
     */
    public void reschedule(LocalDateTime value) throws InvalidDataException {
        LocalDateTime truncatedDateTime = validateThenTruncate(value);
        scheduledStatusCheck("reschedule");
        pastDateTimeCheck(truncatedDateTime);
        this.dateTime = truncatedDateTime;
    }
    /**
     * Cancels a SCHEDULED appointment.
     * Success: the appointment's status becomes CANCELLED.
     * If an exception is thrown, the status is unchanged.
     * @throws InvalidDataException when appointment status is not SCHEDULED.
     */
    public void cancel() throws InvalidDataException {
        scheduledStatusCheck("cancel");
        this.status = AppointmentStatus.CANCELLED;
    }
    /**
     * Completes a SCHEDULED appointment.
     * Success: the appointment's status becomes COMPLETED.
     * If an exception is thrown, the status is unchanged.
     * @throws InvalidDataException when appointment status is not SCHEDULED.
     */
    public void complete() throws InvalidDataException {
        scheduledStatusCheck("complete");
        if (LocalDateTime.now().isBefore(this.dateTime)){
            throw new InvalidDataException("Cannot complete a future appointment.");
        }
        this.status = AppointmentStatus.COMPLETED;
    }
    /**
     * Returns true only when status is SCHEDULED.
     * COMPLETED and CANCELLED do not block the doctor's time.
     */
    public boolean isOccupyingSlot(){
        return this.status == AppointmentStatus.SCHEDULED;
    }
}
