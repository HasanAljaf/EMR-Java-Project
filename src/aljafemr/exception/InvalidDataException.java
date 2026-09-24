package aljafemr.exception;

public class InvalidDataException extends Exception{
    // Default constructor
    public InvalidDataException(){
        super();
    }

    // Constructor - custom error message
    public InvalidDataException(String message){
        super(message);
    }
}
