package aljafemr;

import aljafemr.exception.InvalidDataException;
import aljafemr.model.Doctor;
import aljafemr.model.Patient;
import aljafemr.model.Person;

import java.time.LocalDate;

public class Chunk2Tests {
    public static void main(String[] args){
        System.out.println("Person test: creating type Person objects and calling displayDetails()");
        try {
            Person personOne = new Patient("P099", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            Person personTwo = new Doctor("D099", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "17-98723");
            System.out.println(personOne.displayDetails());
            System.out.println("   ");
            System.out.println(personTwo.displayDetails());
        } catch (InvalidDataException e){
            System.out.println("BUG: valid person rejected " + e.getMessage());
        }

        System.out.println("   ");
        System.out.println("Patient tests, one for each invalid input.");
        System.out.println("invalid id");
        try {
            Patient patientOne = new Patient(null, "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null ID: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("   ", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank ID: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("D037", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; wrong prefix: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P0001", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; wrong length: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P0a1", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; letter as digit: " + e.getMessage());
        }

        System.out.println("invalid first name");
        try {
            Patient patientOne = new Patient("P013", null, "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null first name: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "   ", "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank first name: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "a".repeat(51), "Toaster", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; exceeds limit: " + e.getMessage());
        }

        System.out.println("invalid last name");
        try {
            Patient patientOne = new Patient("P013", "Fin", null, LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null last name: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "   ", LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank last name: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "a".repeat(51), LocalDate.of(2002,9,29), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; exceeds limit: " + e.getMessage());
        }

        System.out.println("invalid date of birth");
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", null, "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null date of birth: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.now(), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; dob is today: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.now().plusDays(1), "1234567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; dob is in future: " + e.getMessage());
        }

        System.out.println("invalid phone number");
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.of(2002,9,29), "123456789", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; wrong length: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.of(2002,9,29), "12E4567890", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; letter as digit: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.of(2002,9,29), "   ", "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank phone number: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.of(2002,9,29), null, "John Doe");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null phone number: " + e.getMessage());
        }

        System.out.println("invalid emergency contact");
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "   ");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank emergency contact: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", "a".repeat(51));
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; exceeds limit: " + e.getMessage());
        }
        try {
            Patient patientOne = new Patient("P013", "Fin", "Toaster", LocalDate.of(2002,9,29), "1234567890", null);
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null emergency contact: " + e.getMessage());
        }

        System.out.println("   ");
        System.out.println("Doctor tests, one for each invalid input.");
        System.out.println("invalid id");
        try {
            Doctor doctorOne = new Doctor(null, "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null ID: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("   ", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank ID: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("P023", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; wrong prefix: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("D0001", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; wrong length: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("D00a", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; letter as digit: " + e.getMessage());
        }

        System.out.println("invalid specialty");
        try {
            Doctor doctorOne = new Doctor("D001", "Fin", "Toaster", LocalDate.of(2002,9,29), "  ", "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank specialty: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("D001", "Fin", "Toaster", LocalDate.of(2002,9,29), null, "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null specialty: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("D001", "Fin", "Toaster", LocalDate.of(2002,9,29), "a".repeat(51), "17-98723");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; exceeds limit: " + e.getMessage());
        }

        System.out.println("invalid license number");
        try {
            Doctor doctorOne = new Doctor("D001", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", null);
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; null license number: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("D001", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "   ");
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; blank license number: " + e.getMessage());
        }
        try {
            Doctor doctorOne = new Doctor("D001", "Fin", "Toaster", LocalDate.of(2002,9,29), "Primary Care", "a".repeat(51));
            System.out.println("BUG: accepted");
        } catch (InvalidDataException e){
            System.out.println("Rejected as expected; exceeds limit: " + e.getMessage());
        }

        System.out.println("   ");
        System.out.println("Patient setter test: invalid setPhoneNumber value.");
        try {
            Patient patientTwo = new Patient("P002","Sam", "Purse", LocalDate.of(2002,9,29), "8021239900", "Billy Bob");
            System.out.println(patientTwo.getPhoneNumber());
            try {
                patientTwo.setPhoneNumber("802123889A");
                System.out.println("BUG: accepted");
            } catch (InvalidDataException e){
                System.out.println("Rejected as expected; invalid setter value, letters as digit: " + e.getMessage());
            }
            System.out.println(patientTwo.getPhoneNumber());
        } catch (InvalidDataException e) {
            System.out.println("BUG: unexpected " + e.getMessage());
        }

        System.out.println("   ");
        System.out.println("Equality test: comparing two patient objects with the same id.");
        try {
            Patient patientTwo = new Patient("P002","Sam", "Purse", LocalDate.of(2002,9,29), "8021239900", "Billy Bob");
            Patient patientThree = new Patient("P002","Sam", "Purse", LocalDate.of(2002,9,29), "8021239900", "Billy Bob");
            Patient patientFour = new Patient("P022","Sam", "Purse", LocalDate.of(2002,9,29), "8021239900", "Billy Bob");

            System.out.println("comparing two patients with same id: " + patientTwo.equals(patientThree));
            System.out.println("hashcode of two similar patient objects: " + patientTwo.hashCode() + " " + patientThree.hashCode());
            System.out.println("comparing two different patient objects: " + patientTwo.equals(patientFour));
            System.out.println("comparing patient object with null: " + patientFour.equals(null));
        } catch (InvalidDataException e){
            System.out.println("BUG: unexpected " + e.getMessage());
        }
    }
}
