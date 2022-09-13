package sistema.presentation.empleado.empleados;

import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;

import java.util.List;

public class Controller {
    private Model model;
    private View view;

    public Controller(View view, Model model) {
        model.setEmpleados(Service.instance().empleadosSearch(""));
        this.model = model;
        this.view = view;

        view.setModel(model);
        view.setController(this);
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

    public void hide(){

        this.view.setVisible(false);
    }

    public void exit(){
        Service.instance().store();
    }

    public void searchEmpleado(String filtro){
        List<Empleado> rows = Service.instance().empleadosSearch(filtro);
        model.setEmpleados(Service.instance().empleadosSearch(filtro));
        model.commit();
    }

    public void empleadosAgregar(){
        this.hide();
        Application.EMPLEADOS.show();
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


    public View getView() {
        return view;
    }
}