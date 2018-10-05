package application;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class GeneralManager {
    public static boolean empty(String string) {
        // return true if parameter string is found empty.
        return string == null || string.trim().isEmpty();
    }

    public void addToTextPane(JTextPane textPane, ArrayList<String> arrayList) {
        // Add contents of parameter ArrayList to parameter JTextPane
        StyledDocument styledDocument = textPane.getStyledDocument();
        for (String string : arrayList) {
            try {
                styledDocument.insertString(0, string + "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        // Calculate the age of a person
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
