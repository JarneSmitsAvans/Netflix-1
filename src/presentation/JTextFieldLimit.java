package presentation;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * JTextFieldLimit.java
 * This class has a method that places a set character limit on a JTextField
 * <p>
 * Author: Dylan ten Böhmer
 */

public class JTextFieldLimit extends PlainDocument {
    private int limit;

    // Constructor
    public JTextFieldLimit(int limit) {
        super();
        // Limit is set here.
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return; // If empty, return null
        if ((getLength() + str.length()) <= limit) { // Check if the the input string is under the set limit, if it is, enter it.
            super.insertString(offset, str, attr);
        }
    }
}