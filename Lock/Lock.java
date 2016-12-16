package week1;

//import org.junit.Test;

/**
 * Write a description of class Lock here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lock {
    /**
     * what the user enters
     */
    private String userEnteredCode;

    /**
     * what the correct code will be
     */
    private String correctCode;

    private static String masterCode = "999";

    public static void setMasterCode (String newCode) {
        masterCode = newCode;
        //correctCode = "9";
    }


    public Lock() {
        correctCode = "123";
        userEnteredCode = "";
    }

    public Lock(String pActualCode) {
        correctCode = pActualCode;
        userEnteredCode = "";
    }

    public boolean equals(Lock l) {
        if (correctCode.equals(l.correctCode))
            return true;
        else
            return false;
    }

    public boolean equals(Object l) {
        if (correctCode.equals(((Lock)l).correctCode))
            return true;
        else
            return false;
    }

    public void enterCode(String pCode) {
        userEnteredCode = pCode;
    }

    public boolean isUnlock() {
        if (correctCode.equals(userEnteredCode) ||
                (masterCode.equals(userEnteredCode)))
            return true;
        else
            return false;
    }

    public boolean reset() {
        userEnteredCode = "";
        return true;

    }


}

    
