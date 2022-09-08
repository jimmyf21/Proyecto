package sistema.presentation.sucursal;


import java.util.Observable;
import sistema.logic.Sucursal;


import javax.swing.*;

public class View extends javax.swing.JFrame implements java.util.Observer {
    private JPanel panel1;

    Controller controller;
    Model model;
    public View() {

    }

    public void setController(Controller controller){
        this.controller=controller;
    }

    public Controller getController() {
        return controller;
    }

    public void setModel(Model model){
        this.model=model;
        model.addObserver(this);
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void update(Observable o, Object arg) {
        Sucursal sucursal = model.getSucursal();


    }

    public JPanel getPanel1() {
        return panel1;
    }
}
