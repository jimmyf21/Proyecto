package sistema.presentation.empleado.empleadoAgregar;

import java.awt.*;
import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;

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
        dialog = new JDialog(Application.window,"Empleado", true);
        dialog.setSize(600,350);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setContentPane(view.getPanel1());
        Point location = Application.window.getLocation();
        dialog.setLocation( location.x+500,location.y+180);
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


    public void EmpleadoAdd(Empleado e){
        try {
            switch (model.getModo()) {
                case Application.MODO_AGREGAR:
                    Service.instance().empleadoAdd(e);
                    model.setEmpleado(new Empleado());
                    break;
                case Application.MODO_EDITAR:
                    Service.instance().empleadoUpdate(e);
                    model.setEmpleado(e);
                    break;
            }
            Application.EMPLEADOS.searchEmpleado("");
            model.commit();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }


}
