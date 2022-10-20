package sistema.presentation.empleado.empleadoAgregar;

import java.awt.*;
import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;
import sistema.logic.Sucursal;

import javax.swing.*;


public class Controller {
    Model model;
    View view;
    JDialog dialog;

    public Controller(View viewEmpleado, Model modelEmpleado) {
        this.view = viewEmpleado;
        this.model = modelEmpleado;

        model.setEmpleado(new Empleado());
        view.setController(this);
        view.setModel(model);
    }

    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setEmpleado(new Empleado());
        model.commit();
        this.show();
    }


    public void show(){
        dialog = new JDialog((Frame) null, "Empleado", true);
        dialog.setContentPane(view.getPanel1());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
    }


    public void show1(){
        Application.window.setContentPane(view.getPanel1());
        Application.window.revalidate();
    }
    public void hide1(){
        Application.PRINCIPAL.show();
    }

    public void editar(Empleado e){
        model.setModo(Application.MODO_EDITAR);
        model.setEmpleado(e);
        model.commit();
        this.show();
    }

    public Point getPoint(Sucursal sucursal) throws Exception{
        return Service.instance().getPointSucursal(sucursal);
    }

    public Boolean EmpleadoAdd(Empleado e){
        Boolean result = false;
        Empleado empleadoBuscar = null;
        try {
            switch (model.getModo()) {
                case Application.MODO_AGREGAR:
                    Service.instance().empleadoAdd(e);
                    Service.instance().store();
                    model.setEmpleado(e);
                    empleadoBuscar = Service.instance().empleadoGet(e.getCedula());
                    break;
                case Application.MODO_EDITAR:
                    Service.instance().empleadoUpdate(e);
                    model.setEmpleado(e);
                    empleadoBuscar = Service.instance().empleadoGet(e.getCedula());
                    break;
            }
            if(empleadoBuscar == null){
                hide();
            }else{
                result = true;
            }
            Application.EMPLEADOS.searchEmpleado("");
        }catch (Exception ex){
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public Sucursal getSucursalFromPoint(Point p){
        Sucursal s = null;
        try {
            s = Service.instance().findSucursalByPoint(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return s;
    }


}