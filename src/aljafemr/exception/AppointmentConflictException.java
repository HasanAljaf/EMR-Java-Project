/*
 * Exception throw when the selected doctor already has a scheduled appointment at the requested date/time.
*/

package aljafemr.exception;

public class AppointmentConflictException extends Exception {
    // Constructor - custom error message
    public AppointmentConflictException(String message){
        super(message);
    }
}
