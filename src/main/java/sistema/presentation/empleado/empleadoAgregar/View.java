package sistema.presentation.empleado.empleadoAgregar;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;
import sistema.logic.Sucursal;

import javax.swing.*;
import javax.swing.border.Border;


public class View extends javax.swing.JFrame implements java.util.Observer {


    private JPanel panel1;
    private JTextField sucursalEmpleadoTxt;
    private JTextField salarioEmpleadoTxt;
    private JTextField telefonoEmpleadoTxt;
    private JTextField nombreEmpleadoTxt;
    private JTextField cedulaEmpleadoTxt;
    private JButton guardarEmpleadoBtn;
    private JButton cancelarEmpleadoBtn;
    private JLabel jLabelMapa;
    private JButton button1;

    Controller controller;
    Model model;

    public View() {

        cancelarEmpleadoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetLabelsTxt();
                controller.hide();
                clearBordersFields();
            }
        });


        salarioEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                if (!key.matches("[0-9]")) {
                    e.consume();
                }

            }
        });

        nombreEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                if (key.matches("[0-9]")) {
                    e.consume();
                }

            }
        });


        telefonoEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                if (!key.matches("[0-9]")) {
                    e.consume();
                }

            }
        });


        guardarEmpleadoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

                if(validateFields()){
                    int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
                    double salarioParsiado = Double.valueOf(campoSalario);
                    if (JOptionPane.OK_OPTION == value) {
                        try {
                            Sucursal s = Service.instance().sucursalSearchForReference(campoSucursal);
                            if(s != null) {
                                Boolean b = controller.EmpleadoAdd(new Empleado(campoCedula, campoNombre, campoTelefono, salarioParsiado, s));
                                if(b){
                                    resetLabelsTxt();
                                    clearBordersFields();
                                    controller.hide();
                                }
                            }else{
                                JOptionPane.showMessageDialog (null, "Sucursal no existe", "Error", JOptionPane.ERROR_MESSAGE);
                            }
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

                    if(validateFields()){
                        int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
                        double salarioParsiado = Double.valueOf(campoSalario);
                        if (JOptionPane.OK_OPTION == value) {
                            try {
                                Sucursal s = Service.instance().sucursalSearch(campoSucursal);
                                if(s != null) {

                                    Boolean b = controller.EmpleadoAdd(new Empleado(campoCedula, campoNombre, campoTelefono, salarioParsiado, s));
                                    if(b){
                                        resetLabelsTxt();
                                        clearBordersFields();
                                    }
                                    resetLabelsTxt();
                                    clearBordersFields();
                                }else{
                                    JOptionPane.showMessageDialog (null, "Sucursal no existe", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
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
                    clearBordersFields();
                }
            }
        });
        cedulaEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(cedulaEmpleadoTxt.getText().isEmpty()){
                    cedulaEmpleadoTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });
        nombreEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(nombreEmpleadoTxt.getText().isEmpty()){
                    nombreEmpleadoTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });

        telefonoEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(telefonoEmpleadoTxt.getText().isEmpty()){
                    telefonoEmpleadoTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });

        salarioEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(salarioEmpleadoTxt.getText().isEmpty()){
                    salarioEmpleadoTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });

        sucursalEmpleadoTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(sucursalEmpleadoTxt.getText().isEmpty()){
                    sucursalEmpleadoTxt.setBorder(Application.BORDER_NOBORDER);
                }
            }
        });
        jLabelMapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
                Sucursal sucursal = controller.getSucursalFromPoint(point);
                if(sucursal != null) {
//                     if(e.getPoint().getX() != point.getX() && e.getPoint().getY() != point.getY() || e.getPoint().getX() == point.getX() && e.getPoint().getY() == point.getY())
                    try {
                        model.setUbicacion(controller.getPoint(sucursal));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    model.getMapa().setUbicRojo(model.getUbicacion());
                    JLabel imagen = model.getMapa().mostrarUbicaciones();
                    jLabelMapa.setIcon(imagen.getIcon());
                    sucursalEmpleadoTxt.setText(sucursal.getReferencia());
                    sucursalEmpleadoTxt.setDisabledTextColor(Color.RED);

                }else {
                    JLabel imagen = model.getMapa().mostrarUbicaciones();
                    jLabelMapa.setIcon(imagen.getIcon());
                    sucursalEmpleadoTxt.setText("");

                }
            }
        });
    }

    Border b = cedulaEmpleadoTxt.getBorder();

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

    private boolean validateFields() {
        boolean valid = true;
        Border b = cedulaEmpleadoTxt.getBorder();
        if (cedulaEmpleadoTxt.getText().isEmpty()) {
            valid = false;
            cedulaEmpleadoTxt.setBorder(Application.BORDER_ERROR);
        } else {
            cedulaEmpleadoTxt.setToolTipText(null);
            cedulaEmpleadoTxt.setBorder(b);
        }
        b = nombreEmpleadoTxt.getBorder();
        if (nombreEmpleadoTxt.getText().length() == 0) {
            valid = false;
            nombreEmpleadoTxt.setBorder(Application.BORDER_ERROR);
        } else {
            nombreEmpleadoTxt.setBorder(b);
            nombreEmpleadoTxt.setToolTipText(null);
        }
        b = telefonoEmpleadoTxt.getBorder();
        if (telefonoEmpleadoTxt.getText().length() == 0) {
            valid = false;
            telefonoEmpleadoTxt.setBorder(Application.BORDER_ERROR);
        } else {
            telefonoEmpleadoTxt.setBorder(b);
            telefonoEmpleadoTxt.setToolTipText(null);
        }
        b = salarioEmpleadoTxt.getBorder();
        if (salarioEmpleadoTxt.getText().length() == 0) {
            valid = false;
            salarioEmpleadoTxt.setBorder(Application.BORDER_ERROR);
        } else {
            salarioEmpleadoTxt.setBorder(b);
        }
        b = sucursalEmpleadoTxt.getBorder();
        if (sucursalEmpleadoTxt.getText().length() == 0) {
            valid = false;
            sucursalEmpleadoTxt.setBorder(Application.BORDER_ERROR);
        } else {
            sucursalEmpleadoTxt.setBorder(b);
        }
        return valid;
    }

    private void resetLabelsTxt(){
        cedulaEmpleadoTxt.setText("");
        nombreEmpleadoTxt.setText("");
        telefonoEmpleadoTxt.setText("");
        salarioEmpleadoTxt.setText("");
        sucursalEmpleadoTxt.setText("");
    }

    private void clearBordersFields() {
        cedulaEmpleadoTxt.setBorder(b);
        nombreEmpleadoTxt.setBorder(b);
        telefonoEmpleadoTxt.setBorder(b);
        salarioEmpleadoTxt.setBorder(b);
        sucursalEmpleadoTxt.setBorder(b);
    }

    @Override
    public void update(Observable o, Object arg) {
        Empleado empleado = model.getEmpleado();
        cedulaEmpleadoTxt.setText(empleado.getCedula());
        cedulaEmpleadoTxt.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        nombreEmpleadoTxt.setText(empleado.getNombre());
        telefonoEmpleadoTxt.setText(empleado.getTelefono());
        salarioEmpleadoTxt.setText(String.valueOf(empleado.getSalario()));
        sucursalEmpleadoTxt.setText(empleado.getSucursal().getReferencia());
        try {
            model.setMapa(new ImagenModel(Service.instance().getPointSucursales(), Service.instance().getPointSucursal(empleado.getSucursal())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JLabel imagen = model.getMapa().mostrarUbicaciones();
        jLabelMapa.setIcon(imagen.getIcon());
        this.panel1.revalidate();
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
