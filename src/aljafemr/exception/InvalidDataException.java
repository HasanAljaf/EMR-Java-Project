/*
 * Exception thrown when required fields, date values, status changes, or stored records are invalid.
*/

package aljafemr.exception;

public class InvalidDataException extends Exception {
    // Constructor - custom error message
    public InvalidDataException(String message){
        super(message);
    }
}
