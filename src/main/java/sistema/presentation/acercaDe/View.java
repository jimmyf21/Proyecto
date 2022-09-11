package sistema.presentation.acercaDe;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {


    private JPanel panel;
    private JTextPane SISESistemaDeSucursalesTextPane;

    public View() {

    }


    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {

    }

    public JPanel getPanel() {
        return panel;
    }
}