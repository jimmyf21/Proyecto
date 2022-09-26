package sistema.presentation.empleado.empleadoAgregar;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;

import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;
import sistema.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


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
    private JLabel jLabelMapa;
    private JButton button1;

    public View() {

       cancelarEmpleadoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetLabelsTxt();
                controller.hide();
                clearBordersFields();
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
                            /*Empleado a = Service.instance().empleadoGet(cedulaEmpleadoTxt.getText());
                            if(a == null){*/
                                Sucursal s = Service.instance().sucursaleSearch(campoSucursal);
                                if(s != null) {
                                    controller.EmpleadoAdd(new Empleado(campoCedula, campoNombre, campoTelefono, salarioParsiado, s));
                                    JOptionPane.showMessageDialog(null, "Guardado con exito");
                                    resetLabelsTxt();
                                    clearBordersFields();
                                    controller.hide();
                                }else{
                                    JOptionPane.showMessageDialog (null, "Sucursal no existe", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                           /* }else{
                                JOptionPane.showMessageDialog (null, "Empleado ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                            }*/
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

                    if (validateFields()) {
                        int value = JOptionPane.showConfirmDialog(null, "¿Desea guardar?");
                        double salarioParsiado = Double.valueOf(campoSalario);
                        if (JOptionPane.OK_OPTION == value) {
                            try {
                                Empleado a = Service.instance().empleadoGet(cedulaEmpleadoTxt.getText());
                                if (a == null) {
                                    Sucursal s = Service.instance().sucursaleSearch(campoSucursal);
                                    if (s != null) {
                                        controller.EmpleadoAdd(new Empleado(campoCedula, campoNombre, campoTelefono, salarioParsiado, s));
                                        JOptionPane.showMessageDialog(null, "Guardado con exito");
                                        resetLabelsTxt();
                                        clearBordersFields();
                                        controller.hide();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Sucursal inexistente", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Empleado existente", "Error", JOptionPane.ERROR_MESSAGE);
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
                try {
                    addIconWithClick();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
            sucursalEmpleadoTxt.setBorder(null);
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

    private void addIconWithClick() throws IOException {
        /*../../main/resources/imagenes/Sucursal.png"*/
       /* Image mapa;
        mapa = ImageIO.read(new File("../../main/resources/imagenes/Sucursal.png"));
        mapa = mapa.getScaledInstance(jLabelMapa.getWidth(), jLabelMapa.getHeight(), Image.SCALE_SMOOTH);

        BufferedImage result= new BufferedImage(mapa.getWidth(null), mapa.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g= result.getGraphics();
        g.drawImage(mapa,jLabelMapa.getWidth()/2, jLabelMapa.getHeight()/2, null);
        g.dispose();

        jLabelMapa.setIcon(new ImageIcon(result));*/

    }

    @Override
    public void update(Observable o, Object arg) {
        Empleado empleado = model.getEmpleado();
        cedulaEmpleadoTxt.setText(empleado.getCedula());
        nombreEmpleadoTxt.setText(empleado.getNombre());
        telefonoEmpleadoTxt.setText(empleado.getTelefono());
        salarioEmpleadoTxt.setText(String.valueOf(empleado.getSalario()));
        sucursalEmpleadoTxt.setText(empleado.getSucursal().getReferencia());
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
