package sistema.presentation.sucursal.sucursalAgregar;


import java.awt.event.*;
import java.util.Observable;
import java.awt.*;
import sistema.application.Application;
import sistema.logic.Service;
import sistema.logic.Sucursal;
import sistema.presentation.sucursal.sucursalAgregar.ImagenModel;

import javax.swing.*;
import javax.swing.border.Border;

public class View extends javax.swing.JFrame implements java.util.Observer {
    private JPanel panel1;
    private JTextField codigoSucursalTxt;
    private JTextField referenciaSucursalTxt;
    private JTextField direccionSucursalTxt;
    private JTextField zonajeSucursalTxt;
    private JButton guardarSucursalBtn;
    private JButton cancelarSucursalBtn;
    private JLabel mapaLabel;

    Controller controller;
    Model model;

    Point ubicacion;
    public View() {

        cancelarSucursalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetLabelsTxt();
                controller.hide();
                clearBordersFields();
            }
        });

        zonajeSucursalTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                if (!key.matches("[0-9]")) {
                    e.consume();
                }

            }
        });

        referenciaSucursalTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                if (key.matches("[0-9]")) {
                    e.consume();
                }

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

                if(validateFields()){
                    int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
                    double zonajeParseado = Double.valueOf(campoZonaje);
                    if (JOptionPane.OK_OPTION == value) {
                        try {
                            Sucursal s = new Sucursal(campoCodigo, campoReferencia, campoDireccion, zonajeParseado);
                            if(model.getModo() == Application.MODO_EDITAR) {
                                ubicacion = controller.getUbicacionActual(s);
                            }
                               Boolean b = controller.SucursalAdd(s, ubicacion);
                               if(b){
                                   resetLabelsTxt();
                                   clearBordersFields();
                                   controller.hide();
                               }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "¡Los campos no pueden estar vacios!", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
                ubicacion = null;
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

                    if (validateFields()) {
                        int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
                        double zonajeParseado = Double.valueOf(campoZonaje);
                        if (JOptionPane.OK_OPTION == value) {
                            try {
                                Boolean b = controller.SucursalAdd(new Sucursal(campoCodigo, campoReferencia, campoDireccion, zonajeParseado), ubicacion);
                                if (b) {
                                    resetLabelsTxt();
                                    clearBordersFields();
                                }
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Los campos no pueden estar vacios!", "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    ubicacion = null;
                }
            }
        });
        cancelarSucursalBtn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    resetLabelsTxt();
                    controller.hide();
                    clearBordersFields();
                }
            }
        });
        codigoSucursalTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(codigoSucursalTxt.getText().isEmpty()){
                    codigoSucursalTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });

        referenciaSucursalTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(referenciaSucursalTxt.getText().isEmpty()){
                    referenciaSucursalTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });

        direccionSucursalTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(direccionSucursalTxt.getText().isEmpty()){
                    direccionSucursalTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });

        zonajeSucursalTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(zonajeSucursalTxt.getText().isEmpty()){
                    zonajeSucursalTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });
        mapaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel sucurs = new JLabel();
                ubicacion = e.getPoint();
                model.setUbicacionActual(ubicacion);
                ImagenModel imagen = new ImagenModel(ubicacion);
                sucurs = imagen.mostrarUbicaciones();
                mapaLabel.setIcon(sucurs.getIcon());
            }
        });

    }

    Border b = codigoSucursalTxt.getBorder();

    private boolean validateFields(){
        boolean valid = true;
        Border b = codigoSucursalTxt.getBorder();
        if (codigoSucursalTxt.getText().isEmpty()) {
            valid = false;
            codigoSucursalTxt.setBorder(Application.BORDER_ERROR);
        } else {
            codigoSucursalTxt.setToolTipText(null);
            codigoSucursalTxt.setBorder(b);
        }
        b = referenciaSucursalTxt.getBorder();
        if (referenciaSucursalTxt.getText().length() == 0) {
            valid = false;
            referenciaSucursalTxt.setBorder(Application.BORDER_ERROR);
        } else {
            referenciaSucursalTxt.setBorder(b);
            referenciaSucursalTxt.setToolTipText(null);
        }
        b = direccionSucursalTxt.getBorder();
        if (direccionSucursalTxt.getText().length() == 0) {
            valid = false;
            direccionSucursalTxt.setBorder(Application.BORDER_ERROR);
        } else {
            direccionSucursalTxt.setBorder(b);
            direccionSucursalTxt.setToolTipText(null);
        }
        b = zonajeSucursalTxt.getBorder();
        if (zonajeSucursalTxt.getText().length() == 0) {
            valid = false;
            zonajeSucursalTxt.setBorder(Application.BORDER_ERROR);
        } else {
            zonajeSucursalTxt.setBorder(b);
            zonajeSucursalTxt.setToolTipText(null);
        }
        if(model.getModo() == Application.MODO_AGREGAR){
            if(ubicacion == null) {
                valid = false;
                mapaLabel.setBorder(Application.BORDER_ERROR);
            }
        }
        return valid;
    }

    private void resetLabelsTxt(){
        codigoSucursalTxt.setText("");
        referenciaSucursalTxt.setText("");
        direccionSucursalTxt.setText("");
        zonajeSucursalTxt.setText("");
    }

    private void clearBordersFields(){
        codigoSucursalTxt.setBorder(b);
        referenciaSucursalTxt.setBorder(b);
        direccionSucursalTxt.setBorder(b);
        zonajeSucursalTxt.setBorder(b);
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
        codigoSucursalTxt.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        referenciaSucursalTxt.setText(sucursalO.getReferencia());
        direccionSucursalTxt.setText(sucursalO.getDireccion());
        zonajeSucursalTxt.setText(String.valueOf(sucursalO.getZonaje()));

        if(model.getModo() == Application.MODO_AGREGAR){
            ImagenModel mapa = new ImagenModel(model.getUbicacionActual());
            mapaLabel.setIcon(new ImageIcon(mapa.getMapa()));
            this.panel1.revalidate();
        }else {
            ImagenModel mapa = new ImagenModel(model.getUbicacionActual());
            JLabel s = mapa.mostrarUbicaciones();
            mapaLabel.setIcon(s.getIcon());
            model.setUbicacionActual(null);
            this.panel1.revalidate();
        }
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
