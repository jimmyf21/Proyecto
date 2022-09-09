package sistema.presentation.empleado;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import sistema.logic.Empleado;
import sistema.logic.Sucursal;

import javax.swing.*;


public class View extends javax.swing.JFrame implements java.util.Observer {

    Controller controller;
    Model model;
    private JPanel panel1;
    private JTextField sucursalEmpleadoTxt;
    private JTextField salarioEmpleadoTxt;
    private JTextField telefonoEmpleadoTxt;
    private JTextField nombreEmpleadoTxt;
    private JTextField cedulaEmpleadoTxt;
    private JButton guardarEmpleadoBtn;
    private JButton cancelarEmpleadoBtn;

    public View() {

        cancelarEmpleadoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.hide();
            }
        });


        guardarEmpleadoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String campoCedula = cedulaEmpleadoTxt.getText();
                String campoNombre = nombreEmpleadoTxt.getText();
                String campoTelefono = telefonoEmpleadoTxt.getText();
                double campoSalario = Double.valueOf(salarioEmpleadoTxt.getText());
                String campoSucursal = sucursalEmpleadoTxt.getText();


                campoCedula = campoCedula.replaceAll(" ", "");
                campoNombre = campoNombre.replaceAll(" ", "");
                campoTelefono = campoTelefono.replaceAll(" ", "");
                campoSucursal = campoSucursal.replaceAll(" ", "");

                if (campoCedula.length() != 0 && campoNombre.length() != 0 && campoTelefono.length() != 0 && campoSucursal.length() != 0) {
                    int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?");
                    if (JOptionPane.OK_OPTION == value) {
                        controller.EmpleadoAdd(new Empleado(campoCedula, campoNombre, campoTelefono, campoSalario, campoSucursal));
                        JOptionPane.showMessageDialog(null, "Guardado con exito");
                        cedulaEmpleadoTxt.setText("");
                        nombreEmpleadoTxt.setText("");
                        telefonoEmpleadoTxt.setText("");
                        salarioEmpleadoTxt.setText("");
                        sucursalEmpleadoTxt.setText("");

                        controller.hide();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "¡Los campos no pueden estar vacios!", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        guardarEmpleadoBtn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String campoCedula = cedulaEmpleadoTxt.getText();
                    String campoNombre = nombreEmpleadoTxt.getText();
                    String campoTelefono = telefonoEmpleadoTxt.getText();
                    String campoSalario = salarioEmpleadoTxt.getText();
                    String campoSucursal = sucursalEmpleadoTxt.getText();
                    campoCedula = campoCedula.replaceAll(" ", "");
                    campoNombre = campoNombre.replaceAll(" ", "");
                    campoTelefono = campoTelefono.replaceAll(" ", "");
                    campoSalario = campoSalario.replaceAll(" ", "");
                    campoSucursal = campoSucursal.replaceAll(" ", "");
                    if (campoCedula.length() != 0 && campoNombre.length() != 0 && campoTelefono.length() != 0 && campoSalario.length() != 0 && campoSucursal.length() != 0) {
                        int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?");
                        if (JOptionPane.OK_OPTION == value) {
                            JOptionPane.showMessageDialog(null, "Guardado con exito");
                            controller.hide();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "¡Los campos no pueden estar vacios!", "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        cancelarEmpleadoBtn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controller.hide();
                }
            }
        });


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
