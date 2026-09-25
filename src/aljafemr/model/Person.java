package aljafemr.model;

import aljafemr.exception.InvalidDataException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Person is an abstract base class for patients and doctors, holds shared identity fields, and is never created directly.
 */

public abstract class Person {
    // Class Fields
    private final String id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;

    // Variables
    private static final int nameLimit = 50;

    /**
     * Constructor creates a Person after validating each field.
     * @param id Required
     * @param firstName  Required, has a limit of 50 characters
     * @param lastName Required, has a limit of 50 characters
     * @param dateOfBirth Not null, must be strictly before today (today is rejected)
     *
     * @throws InvalidDataException if any fields above fail their rules. No object is created
     */

    // Parameterized Constructor
    public Person(String id, String firstName, String lastName, LocalDate dateOfBirth) throws InvalidDataException{
        validateId(id);
        validateName(firstName, "First name");
        validateName(lastName, "Last name");
        validateDateOfBirth(dateOfBirth);

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o){
        // self-comparison check
        if (this == o) return true;

        // null and type check
        if (o == null || getClass() != o.getClass()) return false;

        // cast and compare properties
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    // Public methods (getters, setters, business logic)
    public String getId(){ return this.id; }
    public String getFirstName(){ return this.firstName; }
    public String getLastName(){ return this.lastName; }
    public String getFullName(){ return this.firstName + " " + this.lastName; }
    public LocalDate getDateOfBirth(){ return this.dateOfBirth; }

    public abstract String displayDetails();

    // Private helper methods (validation, formatting, internal utilities)
    private void validateId(String value) throws InvalidDataException{
        if (value == null || value.isBlank()){
            throw new InvalidDataException("ID is required.");
        }
    }
    private void validateName(String value, String fieldName) throws InvalidDataException{
        if (value == null || value.isBlank()){
            throw new InvalidDataException(fieldName + " is required.");
        }
        if (value.length() > nameLimit){
            throw new InvalidDataException(fieldName + " cannot exceed " + nameLimit + " characters.");
        }
    }
    private void validateDateOfBirth(LocalDate value) throws InvalidDataException{
        if (value == null){
            throw new InvalidDataException("Date of birth is required.");
        }
        final LocalDate today = LocalDate.now();
        if (today.equals(value) || today.isBefore(value)){
            throw new InvalidDataException("Date of birth must be strictly before today.");
        }
    }
}
