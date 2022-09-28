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
                                controller.SucursalAdd(new Sucursal(campoCodigo, campoReferencia, campoDireccion, zonajeParseado));
                                resetLabelsTxt();
                                clearBordersFields();
                                controller.hide();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
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

                    if (validateFields()) {
                        int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
                        double zonajeParseado = Double.valueOf(campoZonaje);
                        if (JOptionPane.OK_OPTION == value) {
                            try {
                                Sucursal s = Service.instance().sucursaleSearchForCode(codigoSucursalTxt.getText());
                                if (s == null) {
                                    controller.SucursalAdd(new Sucursal(campoCodigo, campoReferencia, campoDireccion, zonajeParseado));
                                    JOptionPane.showMessageDialog(null, "Guardado con exito");
                                    resetLabelsTxt();
                                    clearBordersFields();
                                    controller.hide();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Sucursal existente con el mismo codigo", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Los campos no pueden estar vacios!", "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }

                ubicacion = null;
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
        referenciaSucursalTxt.setText(sucursalO.getReferencia());
        direccionSucursalTxt.setText(sucursalO.getDireccion());
        zonajeSucursalTxt.setText(String.valueOf(sucursalO.getZonaje()));

        ImagenModel mapa = new ImagenModel(model.getUbicacionActual());
        mapaLabel.setIcon(new ImageIcon(mapa.getMapa()));

    }

    public JPanel getPanel1() {
        return panel1;
    }
}
