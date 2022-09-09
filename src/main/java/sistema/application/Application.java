package sistema.application;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Application {
    public static void main(String[] args){

        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        sistema.presentation.empleado.Model modelEmpleados = new sistema.presentation.empleado.Model() ;
        sistema.presentation.empleado.View viewEmpleados = new sistema.presentation.empleado.View();
        sistema.presentation.empleado.Controller controllerEmpleados =
                new sistema.presentation.empleado.Controller(modelEmpleados,viewEmpleados);
        EMPLEADOS = controllerEmpleados;

        sistema.presentation.sucursal.Model modelSucursales=new sistema.presentation.sucursal.Model() ;
        sistema.presentation.sucursal.View viewSucursales = new sistema.presentation.sucursal.View();
        sistema.presentation.sucursal.Controller controllerSucursales =
                new sistema.presentation.sucursal.Controller(modelSucursales,viewSucursales);
        SUCURSALES = controllerSucursales;



        sistema.presentation.principal.Model modelPrincipal=new sistema.presentation.principal.Model() ;
        sistema.presentation.principal.View viewPrincipal = new sistema.presentation.principal.View();
        sistema.presentation.principal.Controller controllerPrincipal =
                new sistema.presentation.principal.Controller(modelPrincipal,viewPrincipal);
        PRINCIPAL = controllerPrincipal;


        window = new JFrame();
        //Coloca el JFrame en algun lugar de la pantalla
        /*Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((pantallaTamano.width/4)-(window.getWidth()/4), (pantallaTamano.height/8)-(window.getHeight()/8));
        window.setSize(650,550);*/

        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISE: Sistema de Sucursales y Empleados");
        window.setVisible(true);

        PRINCIPAL.show();

    }

    public static sistema.presentation.empleado.Controller EMPLEADOS;
    public static sistema.presentation.sucursal.Controller SUCURSALES;
    public static sistema.presentation.principal.Controller PRINCIPAL;

    public static JFrame window;
}
