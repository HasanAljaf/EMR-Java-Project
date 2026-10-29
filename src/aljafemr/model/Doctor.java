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

    // Parameterized constructor
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
    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth, String specialty, String licenseNumber) throws InvalidDataException{
        super(id, firstName, lastName, dateOfBirth);
        validateDoctorId(id);
        validateStringInput(specialty, "Specialty", NAME_LIMIT);
        validateStringInput(licenseNumber, "License Number", NAME_LIMIT);

        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
    }

    // Getters
    public String getSpecialty() { return specialty; }
    public String getLicenseNumber() { return licenseNumber; }

    // Setters
    public void setSpecialty(String newSpecialty) throws InvalidDataException{
        validateStringInput(newSpecialty, "Specialty", NAME_LIMIT);
        this.specialty = newSpecialty;
    }

    // Helper methods
    private void validateDoctorId(String value) throws InvalidDataException{
        if (value.length() != 4 || value.charAt(0) != 'D'){
            throw new InvalidDataException("Invalid ID format: Must be 4 characters long beginning with 'D'.");
        }
        if (!isAllDigitsFrom(value, 1)){
            throw new InvalidDataException("Invalid ID format: Last 3 characters must be digits.");
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
                "License Number: " + this.licenseNumber;
    }
}
