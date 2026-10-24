package aljafemr.model;

import aljafemr.exception.InvalidDataException;
import java.time.LocalDate;

/**
 * Doctor is a subclass of Person, and contains doctor details.
 */
public class Doctor extends Person{
    // Fields
    private String specialty;
    private final String licenseNumber;

    /**
     * Constructor creates a Doctor and inherits fields and methods from Person.
     * @param id Required, must begin with "D", 4 characters long, last 3 characters must be numbers
     * @param firstName  Required, has a limit of 50 characters
     * @param lastName Required, has a limit of 50 characters
     * @param dateOfBirth required, must be strictly before today (today is rejected)
     *
     * @param specialty required, has limit of 50 characters.
     * @param licenseNumber required, has limit of 50 characters, cannot be changed.
     *
     * @throws InvalidDataException if any fields above fail their rules. No object is created
     */

    // Parameterized constructor
    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth, String specialty, String licenseNumber) throws InvalidDataException{
        super(id, firstName, lastName, dateOfBirth);
        validateDoctorId(id);
        validateSpecialty(specialty);
        validateLicenseNumber(licenseNumber);

        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
    }

    // Getters
    public String getSpecialty() { return specialty; }
    public String getLicenseNumber() { return licenseNumber; }

    // Setters
    public void setSpecialty(String newSpecialty) throws InvalidDataException{
        validateSpecialty(newSpecialty);
        this.specialty = newSpecialty;
    }

    // Helper methods
    private void validateDoctorId(String value) throws InvalidDataException{
        if (value.length() != 4 || value.charAt(0) != 'D'){
            throw new InvalidDataException("Invalid ID format. Must be 4 characters long beginning with 'D'.");
        }
        if (!checkDigits(value, 1)){
            throw new InvalidDataException("Invalid format. Last 3 characters must be digits");
        }
    }
    private void validateSpecialty(String value) throws InvalidDataException{
        if (value == null || value.isBlank()){
            throw new InvalidDataException("Specialty is required.");
        }
        if (value.length() > NAME_LIMIT){
            throw new InvalidDataException("Specialty cannot exceed " + NAME_LIMIT + " characters");
        }
    }
    private void validateLicenseNumber(String value) throws InvalidDataException{
        if (value == null || value.isBlank()){
            throw new InvalidDataException("License number is required.");
        }
        if (value.length() > NAME_LIMIT){
            throw new InvalidDataException("License number cannot exceed " + NAME_LIMIT + " characters");
        }
    }

    // Methods
    @Override
    public String displayDetails(){
        return "Doctor Details\n" +
                "ID: " + getId() + "\n" +
                "Name: " + getFullName() + "\n" +
                "DOB: " + getDateOfBirth() + "\n" +
                "Specialty: " + this.specialty + "\n" +
                "License Number: " + this.licenseNumber + "\n";
    }
}
