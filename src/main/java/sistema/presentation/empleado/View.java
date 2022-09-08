package sistema.presentation.empleado;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.presentation.empleado.Controller;
import sistema.presentation.empleado.Model;

import sistema.logic.Empleado;

import javax.swing.*;


public class View extends javax.swing.JFrame implements java.util.Observer {

    Controller controller;
    Model model;
    private JPanel panel1;

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
        Empleado empleado = model.getEmpleado();

    }

    public JPanel getPanel1() {
        return panel1;
    }
}
