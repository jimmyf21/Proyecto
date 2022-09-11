package sistema.presentation.principal;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JTabbedPane tabbedPane;

    public View() {

    }

    public JTabbedPane getPanel() {
        return tabbedPane;
    }

    ControllerPrincipal controller;
    Model model;

    public void setController(ControllerPrincipal controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {

    }

}

