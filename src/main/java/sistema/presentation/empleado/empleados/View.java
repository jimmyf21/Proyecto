package sistema.presentation.empleado.empleados;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Observable;

public class  View extends javax.swing.JFrame implements java.util.Observer   {

    private JButton buscarEmpleado;
    private JButton agregarEmpleado;

    private JTable empleadosTable;
    private JPanel panel;

    private JTextField nombreEmpleado;

    private JButton borrarEmpleado;
    private JButton reporteEmpleado;
    private JLabel nombreLbl;


    public View() {

        nombreEmpleado.addKeyListener(new KeyAdapter() {
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
        buscarEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String texto = nombreEmpleado.getText();
                texto=texto.replaceAll(" ", "");
                if (texto.length() == 0) {
                    JOptionPane.showMessageDialog(null, "¡El campo no puede estar vacio!", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        nombreEmpleado.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                controller.searchEmpleado(nombreEmpleado.getText());
            }
        });
        agregarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });
        empleadosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = empleadosTable.getSelectedRow();
                    controller.editar(row);
                }
            }
        });
        borrarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = empleadosTable.getSelectedRow();
                if(row != -1){
                    controller.borrarEmpleado(row);
                }
            }
        });
        reporteEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("empleados.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) { }
            }
        });
    }
    Controller controller;
    Model model;

    public void setController(Controller controllerPrincipal){
        this.controller = controllerPrincipal;
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
        int[] cols = {EmpleadoTableModel.CEDULA, EmpleadoTableModel.NOMBRE, EmpleadoTableModel.TELEFONO, EmpleadoTableModel.SALARIO, EmpleadoTableModel.SUCURSAL, EmpleadoTableModel.ZONAJE, EmpleadoTableModel.SALARIO_TOTAL};
        empleadosTable.setModel(new EmpleadoTableModel(cols, model.getEmpleados()));
        empleadosTable.setRowHeight(30);
        this.panel.revalidate();

    }

    public JTable getEmpleados() {
        return empleadosTable;
    }


    public JPanel getPanel() {
        return panel;
    }
}

