package game.util;

import game.model.Constants;

public class Validate {
    private static DateUtils date = new DateUtils();
    private static Constants constant = new Constants();

    public static boolean checkPassport(String criminal_fname, String criminal_lname, String civilian_fname, String civilian_lname, 
                                        int civilian_age, String occupation, String[] badOccupation, String expdate, String formattedCurrentDate){

        // TO DO: (FORBIDDEN VALUES CHECKER) - RETURN TRUE IF SUS

        // CRIMINAL CHECKER - DONE
        if (criminal_fname.equalsIgnoreCase(civilian_fname) && criminal_lname.equalsIgnoreCase(civilian_lname)) {
            return true;
        }

        // AGE VALIDATOR - DONE
        if (civilian_age > 100){ // maybe over a hundered years old is suspicious enough?
            return true;
        }

        // OCCUPATION VALIDATOR - DONE
        if (contains1D(badOccupation, occupation)){
            return true;
        }
    
        // EXPDATE VALIDATOR
        if (date.isDateLarger(formattedCurrentDate, expdate)){
            return true;
        }

        // RETURN FALSE IF NOT SUSPICIOUS
        return false;
    }

    public static String returnSuspicousDetail(String criminal_fname, String criminal_lname, String civilian_fname, String civilian_lname, 
                                        int civilian_age, String occupation, String[] badOccupation, String expdate, String formattedCurrentDate){

        // (FORBIDDEN VALUES CHECKER) - RETURN TRUE IF SUS
        
        // CRIMINAL CHECKER - DONE
         if (criminal_fname.equalsIgnoreCase(civilian_fname) && criminal_lname.equalsIgnoreCase(civilian_lname)) {
            return constant.redColor + criminal_fname  + " " + criminal_lname + constant.resetColor + ", the civilian is a criminal";
        }

        // AGE VALIDATOR - DONE
        if (civilian_age > 100){ // maybe over a hundered years old is suspicious enough?
            return constant.redColor + civilian_age + constant.resetColor + ", the civilian has a suspicious age ";
        }

        // OCCUPATION VALIDATOR - DONE
        if (contains1D(badOccupation, occupation)){
            return constant.redColor  + occupation + constant.resetColor + ", the civilian has bad occupation";
        }

        // EXPDATE VALIDATOR
        if (date.isDateLarger(formattedCurrentDate, expdate)){
            return constant.redColor + expdate + constant.resetColor + ", the civilian has expired passport";
        }

        return "Something went wrong";
    }
    
    public static boolean contains1D(String[] arr, String target){
        for (String string : arr) {
            if (string.equalsIgnoreCase(target)){
                return true;
            }
        }
        return false;
    }
}
