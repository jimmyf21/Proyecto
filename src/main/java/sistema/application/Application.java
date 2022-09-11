package sistema.application;


import sistema.presentation.principal.ControllerPrincipal;


import javax.swing.*;

public class Application {
    public static void main(String[] args){

        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        sistema.presentation.empleado.empleadoTabbledPane.Model modelEmpleados = new sistema.presentation.empleado.empleadoTabbledPane.Model() ;
        sistema.presentation.empleado.empleadoTabbledPane.View viewEmpleados = new sistema.presentation.empleado.empleadoTabbledPane.View();
        sistema.presentation.empleado.empleadoTabbledPane.Controller controllerEmpleados =
                new sistema.presentation.empleado.empleadoTabbledPane.Controller(viewEmpleados, modelEmpleados);
        EMPLEADOS = controllerEmpleados;

        sistema.presentation.sucursal.sucursalTabbedPane.Model modelSucursal = new sistema.presentation.sucursal.sucursalTabbedPane.Model() ;
        sistema.presentation.sucursal.sucursalTabbedPane.View viewSucursal = new sistema.presentation.sucursal.sucursalTabbedPane.View();
        sistema.presentation.sucursal.sucursalTabbedPane.Controller controllerSucursal =
                new sistema.presentation.sucursal.sucursalTabbedPane.Controller(viewSucursal, modelSucursal);
        SUCURSALES = controllerSucursal;

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

        viewPrincipal.getPanel().add("Empleados",viewEmpleados.getPanel());
        viewPrincipal.getPanel().add("Sucursales",viewSucursal.getPanel());
        viewPrincipal.getPanel().add("Acerca de",viewAcerca.getPanel());

        sistema.presentation.empleado.empleadoAgregar.View viewEmpleadoAgregar = new sistema.presentation.empleado.empleadoAgregar.View();
        sistema.presentation.empleado.empleadoAgregar.Model modelEmpleadoAgregar = new sistema.presentation.empleado.empleadoAgregar.Model();

        sistema.presentation.empleado.empleadoAgregar.Controller controllerEmpleadoAgregar =
                new sistema.presentation.empleado.empleadoAgregar.Controller(viewEmpleadoAgregar, modelEmpleadoAgregar);
        EMPLEADOS_AGREGAR = controllerEmpleadoAgregar;

        sistema.presentation.sucursal.sucursalAgregar.View viewSucursalAgregar = new sistema.presentation.sucursal.sucursalAgregar.View();
        sistema.presentation.sucursal.sucursalAgregar.Model modelSucursalAgregar = new sistema.presentation.sucursal.sucursalAgregar.Model();
        sistema.presentation.sucursal.sucursalAgregar.Controller controllerSucursalAgregar =
                new sistema.presentation.sucursal.sucursalAgregar.Controller(viewSucursalAgregar, modelSucursalAgregar);
        SUCURSALES_AGREGAR = controllerSucursalAgregar;


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
    public static sistema.presentation.empleado.empleadoTabbledPane.Controller EMPLEADOS;
    public static sistema.presentation.sucursal.sucursalTabbedPane.Controller SUCURSALES;

    public static sistema.presentation.acercaDe.Controller ACERCADE;

    public static sistema.presentation.empleado.empleadoAgregar.Controller EMPLEADOS_AGREGAR;
    public static sistema.presentation.sucursal.sucursalAgregar.Controller SUCURSALES_AGREGAR;

    public static JFrame window;
}
