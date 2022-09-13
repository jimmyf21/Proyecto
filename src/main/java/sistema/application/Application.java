package sistema.application;


import sistema.presentation.principal.ControllerPrincipal;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Application {
    public static void main(String[] args){

        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        sistema.presentation.empleado.empleadoAgregar.Model modelEmpleado= new sistema.presentation.empleado.empleadoAgregar.Model();
        sistema.presentation.empleado.empleadoAgregar.View viewEmpleado = new sistema.presentation.empleado.empleadoAgregar.View();
        sistema.presentation.empleado.empleadoAgregar.Controller controllerEmpleado =
                new sistema.presentation.empleado.empleadoAgregar.Controller(viewEmpleado,modelEmpleado);
        EMPLEADO_AGREGAR = controllerEmpleado;

        sistema.presentation.empleado.empleados.Model empleadosModel= new sistema.presentation.empleado.empleados.Model();
        sistema.presentation.empleado.empleados.View empleadosView = new sistema.presentation.empleado.empleados.View();
        sistema.presentation.empleado.empleados.Controller empleadosController =
                new sistema.presentation.empleado.empleados.Controller(empleadosView,empleadosModel);
        EMPLEADOS = empleadosController;

        sistema.presentation.sucursal.sucursalAgregar.Model modelSucursal= new sistema.presentation.sucursal.sucursalAgregar.Model();
        sistema.presentation.sucursal.sucursalAgregar.View viewSucuresal = new sistema.presentation.sucursal.sucursalAgregar.View();
        sistema.presentation.sucursal.sucursalAgregar.Controller controllerSucursal =
                new sistema.presentation.sucursal.sucursalAgregar.Controller(viewSucuresal,modelSucursal);
        SUCURSAL_AGREGAR = controllerSucursal;
        sistema.presentation.sucursal.sucusales.Model sucursalesModel= new sistema.presentation.sucursal.sucusales.Model();
        sistema.presentation.sucursal.sucusales.View sucursalesView = new sistema.presentation.sucursal.sucusales.View();
        sistema.presentation.sucursal.sucusales.Controller sucursalesController =
                new sistema.presentation.sucursal.sucusales.Controller(sucursalesView,sucursalesModel);
        SUCURSALES = sucursalesController;

        sistema.presentation.acercaDe.Model modelAcerca = new sistema.presentation.acercaDe.Model() ;
        sistema.presentation.acercaDe.View viewAcerca = new sistema.presentation.acercaDe.View();
            sistema.presentation.acercaDe.Controller controllerAcerca =
                new sistema.presentation.acercaDe.Controller(viewAcerca, modelAcerca);
        ACERCADE = controllerAcerca;


        sistema.presentation.principal.Model modelPrincipal=new sistema.presentation.principal.Model() ;
        sistema.presentation.principal.View viewPrincipal = new sistema.presentation.principal.View();
        ControllerPrincipal controllerPrincipal =
                new ControllerPrincipal(viewPrincipal,modelPrincipal);
        PRINCIPAL = controllerPrincipal;

        viewPrincipal.getPanel().add("Empleados",empleadosView.getPanel());
        viewPrincipal.getPanel().add("Sucursales",sucursalesView.getPanel());
        viewPrincipal.getPanel().add("Acerca de",viewAcerca.getPanel());


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

    public static ControllerPrincipal PRINCIPAL;
    public static sistema.presentation.empleado.empleados.Controller EMPLEADOS;
    public static sistema.presentation.empleado.empleadoAgregar.Controller EMPLEADO_AGREGAR;
    public static sistema.presentation.sucursal.sucusales.Controller SUCURSALES;
    public static sistema.presentation.sucursal.sucursalAgregar.Controller SUCURSAL_AGREGAR;

    public static sistema.presentation.acercaDe.Controller ACERCADE;

    public static JFrame window;
    public static  final int  MODO_AGREGAR=0;
    public static final int MODO_EDITAR=1;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

}
