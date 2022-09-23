package sistema.presentation.sucursal.sucursalAgregar;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import sistema.logic.Sucursal;
import sistema.presentation.sucursal.sucursalAgregar.Controller;
import sistema.presentation.sucursal.sucursalAgregar.Model;


import javax.swing.*;

public class View extends javax.swing.JFrame implements java.util.Observer {
    private JPanel panel1;
    private JTextField codigoSucursalTxt;
    private JTextField referenciaSucursalTxt;
    private JTextField direccionSucursalTxt;
    private JTextField zonajeSucursalTxt;
    private JButton guardarSucursalBtn;
    private JButton cancelarSucursalBtn;

    Controller controller;
    Model model;
    public View() {

        cancelarSucursalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                codigoSucursalTxt.setText("");
                referenciaSucursalTxt.setText("");
                direccionSucursalTxt.setText("");
                zonajeSucursalTxt.setText("");
                controller.hide();
            }
        });
        guardarSucursalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String campoCodigo = codigoSucursalTxt.getText();
                String campoReferencia = referenciaSucursalTxt.getText();
                String campoDireccion = direccionSucursalTxt.getText();
                String campoZonaje = zonajeSucursalTxt.getText();
                campoCodigo = campoCodigo.replaceAll(" ", "");
                campoReferencia = campoReferencia.replaceAll(" ", "");
                campoDireccion = campoDireccion.replaceAll(" ", "");
                campoZonaje = campoZonaje.replaceAll(" ", "");
                if (campoCodigo.length() != 0 && campoReferencia.length() != 0 && campoDireccion.length() != 0) {
                    int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
                    if (JOptionPane.OK_OPTION == value) {
                        double zonaje = Double.valueOf(campoZonaje);
                        controller.SucursalAdd(new Sucursal(campoCodigo, campoReferencia, campoDireccion, zonaje));
                        JOptionPane.showMessageDialog(null, "Guardado con exito");
                        codigoSucursalTxt.setText("");
                        referenciaSucursalTxt.setText("");
                        direccionSucursalTxt.setText("");
                        zonajeSucursalTxt.setText("");
                        controller.hide();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "¡Los campos no pueden estar vacios!", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        referenciaSucursalTxt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                boolean mayusculas = key >= 65 && key <= 90;
                boolean minusculas = key >= 97 && key <= 122;
                boolean espacio = key == 32;

                if (!(minusculas || mayusculas || espacio))
                {
                    e.consume();
                }
            }
        });
        guardarSucursalBtn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String campoCodigo = codigoSucursalTxt.getText();
                    String campoReferencia = referenciaSucursalTxt.getText();
                    String campoDireccion = direccionSucursalTxt.getText();
                    String campoZonaje = zonajeSucursalTxt.getText();
                    campoCodigo = campoCodigo.replaceAll(" ", "");
                    campoReferencia = campoReferencia.replaceAll(" ", "");
                    campoDireccion = campoDireccion.replaceAll(" ", "");
                    campoZonaje = campoZonaje.replaceAll(" ", "");
                    if (campoCodigo.length() != 0 && campoReferencia.length() != 0 && campoDireccion.length() != 0 && campoZonaje.length() != 0) {
                        int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
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
        cancelarSucursalBtn.addKeyListener(new KeyAdapter() {
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

        Sucursal sucursalO = model.getSucursal();
        codigoSucursalTxt.setText(sucursalO.getCodigo());
        referenciaSucursalTxt.setText(sucursalO.getReferencia());
        direccionSucursalTxt.setText(sucursalO.getDireccion());
        zonajeSucursalTxt.setText(String.valueOf(sucursalO.getZonaje()));


        //controller.getControllerPrincipal().getView().getSucursales().setModel(new SucursalTableModel(model.getSucursales()));



    }

    public JPanel getPanel1() {
        return panel1;
    }
}
