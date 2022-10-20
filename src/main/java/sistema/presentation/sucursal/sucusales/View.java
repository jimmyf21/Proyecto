package sistema.presentation.sucursal.sucusales;

import sistema.logic.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Observable;

public class  View extends javax.swing.JFrame implements java.util.Observer   {

    private JTabbedPane tabbedPane;
    private JPanel panel;
    private JLabel referenciaLbl;
    private JTable sucursalesTable;
    private JButton borrarButton;
    private JButton reporteButton;
    private JTextField referenciaTxtField;
    private JButton buscarReferenciaButton;

    private JButton buscarButton;
    private JButton agregarEmpleadoBtn;
    private JButton agregarSucursalBtn;
    private JLabel mapaLabel;
    private JPanel mapaConteiner;

    private JTable sucursales;

    Controller controller;
    Model model;


    public View() {

        buscarReferenciaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String texto = referenciaTxtField.getText();
                texto=texto.replaceAll(" ", "");
                if (texto.length() == 0) {
                    JOptionPane.showMessageDialog(null, "¡El campo no puede estar vacio!", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        referenciaTxtField.addKeyListener(new KeyAdapter() {
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

        referenciaTxtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                controller.searchSucursal(referenciaTxtField.getText());
            }
        });
        agregarSucursalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.preAgregar();
                controller.sucursalesAgregar();
            }
        });
        sucursalesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = sucursalesTable.getSelectedRow();
                if (e.getClickCount() == 2) {
                    controller.editar(row);
                }else if(e.getClickCount() == 1){
                    model.setUbicacion( controller.getPoint(row));
                    model.getMapa().setPoint(model.getUbicacion());
                    JLabel imagen = model.getMapa().mostrarUbicaciones();
                    mapaLabel.setIcon(imagen.getIcon());
                }
            }
        });
        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("sucursales.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) { }
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = sucursalesTable.getSelectedRow();
                if (row != -1) {
                    try {
                        controller.borrarSucursal(row);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "¡No se puede borrar la sucursal!", "Aviso",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }



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
        int[] cols = {SucursalTableModel.CODIGO, SucursalTableModel.REFERENCIA, SucursalTableModel.DIRECCION, SucursalTableModel.ZONAJE};
        sucursalesTable.setModel(new SucursalTableModel(cols, model.getSucursales()));
        sucursalesTable.setRowHeight(30);

        try {
            model.setMapa(new ImagenModel(Service.instance().getPointSucursales()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JLabel imagen = model.getMapa().mostrarUbicaciones();
        mapaLabel.setIcon(imagen.getIcon());
        this.panel.revalidate();
    }

    public JTable getSucursales() {
        return sucursales;
    }


    public void setSucursales(JTable sucursales) {
        this.sucursales = sucursales;
    }



    public JPanel getPanel() {
        return panel;
    }

}