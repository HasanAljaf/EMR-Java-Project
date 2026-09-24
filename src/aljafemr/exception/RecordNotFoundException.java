package aljafemr.exception;

public class RecordNotFoundException extends Exception{
    // Default constructor
    public RecordNotFoundException(){
        super();
    }

    // Constructor - custom error message
    public RecordNotFoundException(String message){
        super(message);
    }
}
