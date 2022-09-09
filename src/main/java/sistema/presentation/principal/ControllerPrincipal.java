package sistema.presentation.principal;

import sistema.application.Application;
import sistema.logic.Service;

public class ControllerPrincipal {
    private Model model;
    private View view;

    public ControllerPrincipal(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setModel(model);
        view.setController(this);
    }

    public void show(){
        Application.window.setContentPane(view.getPanel1());
    }

    public void hide(){

        this.view.setVisible(false);
    }

    public void exit(){
        Service.instance().store();
    }

    public void empleadosAgregar(){
        this.hide();
        Application.EMPLEADOS.show();
    }

    public void sucursalesAgregar(){
        this.hide();
        Application.SUCURSALES.show();
    }

    public View getView() {
        return view;
    }
}