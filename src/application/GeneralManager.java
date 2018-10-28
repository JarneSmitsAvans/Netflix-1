package application;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 * GeneralManager.java
 * <p>
 * This class has methods that do generic things, like;
 * * Adding an ArrayList of strings to a textpane
 * * Checking if a string is not empty/null
 * * Calculating the age of a person (Not used anywhere, but it could be in the future)
 * <p>
 * Author: Dylan ten Böhmer
 */

public class GeneralManager {
    // Return true if parameter string is found empty.
    public static boolean empty(String string) {
        return string == null || string.trim().isEmpty();
    }

    // Add contents of parameter ArrayList to parameter JTextPane
    public void addToTextPane(JTextPane textPane, ArrayList<String> arrayList) {

        StyledDocument styledDocument = textPane.getStyledDocument();
        for (String string : arrayList) {
            try {
                styledDocument.insertString(0, string + "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    // Round a float to 2 decimals
    public static float round(float number, int decimalPlace) {
        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(decimalPlace, BigDecimal.ROUND_UP);
        return bigDecimal.floatValue();
    }

    // Returns the age of a person
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        // Calculate the age of a person
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
