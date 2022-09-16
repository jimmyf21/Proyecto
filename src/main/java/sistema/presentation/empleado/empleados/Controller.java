package sistema.presentation.empleado.empleados;

import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller(View view, Model model) {

        this.model = model;
        this.view = view;

        model.setEmpleados(new ArrayList<>());
        model.setEmpleados(Service.instance().empleadoAll());

        view.setModel(model);
        view.setController(this);
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

    public void hide(){

        this.view.setVisible(false);
    }




    public void searchEmpleado(String filtro){
        List<Empleado> rows = Service.instance().empleadosSearch(filtro);
        model.setEmpleados(Service.instance().empleadosSearch(filtro));
        model.commit();
    }


    public void preAgregar(){
        Application.EMPLEADO_AGREGAR.preAgregar();
    }


    public void editar(int row){
        String cedula = model.getEmpleados().get(row).getCedula();
        Empleado empleado=null;
        try {
            empleado= Service.instance().empleadoGet(cedula);
            Application.EMPLEADO_AGREGAR.editar(empleado);
        } catch (Exception ex) {}
    }

    public void borrarEmpleado(int row){
        Empleado e = model.getEmpleados().get(row);
        try {
            Service.instance().empleadoDelete(e);
            this.searchEmpleado("");
        } catch (Exception ex) {}
    }


}