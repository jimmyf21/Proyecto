package sistema.presentation.principal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Observable;

public class View extends javax.swing.JFrame implements java.util.Observer   {

    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JLabel refenciaLbl;
    private JTextField referenciaTxtField;
    private JButton buscarReferenciaButton;
    private JTextField nombreTxt;
    private JButton buscarButton;
    private JButton agregarEmpleadoBtn;
    private JButton borrarEmpleadoBtn;
    private JButton reporteEmpleadoBtn;
    private JButton agregarSucursalBtn;
    private JButton borrarSucursalBtn;
    private JButton reporteSucursalBtn;
    private JTable table1;
    private JPanel jPanelAcercaDe;
    private JPanel PanelAcerca;

    private JPanel JPanelAcercaDe;

    public JPanel getJPanel(){
        return panel1;
    }

    public View() {

        buscarReferenciaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String texto = referenciaTxtField.getText();
                texto=texto.replaceAll(" ", "");
                if (texto.length() != 0) {
                    JOptionPane.showMessageDialog(null, "Buscando...");
                }else{
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
        nombreTxt.addKeyListener(new KeyAdapter() {
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
        buscarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String texto = referenciaTxtField.getText();
                texto=texto.replaceAll(" ", "");
                if (texto.length() != 0) {
                    JOptionPane.showMessageDialog(null, "Buscando...");
                }else{
                    JOptionPane.showMessageDialog(null, "¡El campo no puede estar vacio!", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        agregarEmpleadoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.empleadosAgregar();
            }
        });
        agregarSucursalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.sucursalesAgregar();
            }
        });
    }
    Controller controller;
    Model model;

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

    }

    public JPanel getPanel1() {
        return panel1;
    }
}
