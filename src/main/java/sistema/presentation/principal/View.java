package sistema.presentation.principal;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JLabel refenciaLbl;
    private JTextField referenciaTxtField;
    private JButton buscarReferenciaButton;
    private JTextField nombreTxt;
    private JButton buscarButton;
    private JButton agregarButton1;
    private JButton borrarButton1;
    private JButton reporteButton1;
    private JButton agregarButton;
    private JButton borrarButton;
    private JButton reporteButton;

    public JPanel getJPanel(){
        return panel1;
    }

    public View() {
//        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono-empleados.png")).getImage());
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
    }
}
