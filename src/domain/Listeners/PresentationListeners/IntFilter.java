package domain.Listeners.PresentationListeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * IntFilter.java
 * This ActionListener will allow you to only type numbers in a textfield.
 * Author: Marc Verwijmeren
 */

public class IntFilter extends KeyAdapter {
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
            e.consume();
        }
    }
}

