package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieUpdateListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager = new SerieManagerImpl(ui);

    public SerieUpdateListener(GUI ui) {
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
