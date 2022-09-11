package sistema.presentation.sucursal.sucursalTabbedPane;

import sistema.application.Application;
import sistema.logic.Service;

public class Controller {
    private Model model;
    private View view;

    public Controller( View view, Model model) {
        model.setSucursales(Service.instance().sucursalesSearch(""));
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


    public void sucursalesAgregar(){
        this.hide();
        Application.SUCURSALES_AGREGAR.show();
    }

}