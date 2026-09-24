package aljafemr.exception;

public class AppointmentConflictException extends Exception{
    // Default constructor
    public AppointmentConflictException(){
        super();
    }

    // Constructor - custom error message
    public AppointmentConflictException(String message){
        super(message);
    }
}
