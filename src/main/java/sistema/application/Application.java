package sistema.application;

import sistema.presentation.principal.View;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e)
        {e.printStackTrace();}

        View view = new View();

        JFrame windows = new JFrame("SISE: Sistema de Sucursales y Empleados");
        windows.setSize(600, 400);
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setContentPane(view.getJPanel());
        windows.setVisible(true);
    }
}
