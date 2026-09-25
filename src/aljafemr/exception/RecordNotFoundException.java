/*
 * Exception throw when a requested patient, doctor, or appointment ID does not exist.
*/

package aljafemr.exception;

public class RecordNotFoundException extends Exception {
    // Constructor - custom error message
    public RecordNotFoundException(String message){
        super(message);
    }
}
