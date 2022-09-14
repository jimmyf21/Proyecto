package sistema.presentation.sucursal.sucusales;

import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Service;
import sistema.logic.Sucursal;

import java.util.List;

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

    public void searchSucursal(String filtro){
        List<Sucursal> rows = Service.instance().sucursalesSearch(filtro);
        model.setSucursales(rows);
        model.commit();
    }

    public void editar(int row){
        String referencia = model.getSucursales().get(row).getReferencia();
        Sucursal sucursal=null;
        try {
            sucursal= Service.instance().sucursalGet(referencia);
            Application.SUCURSAL_AGREGAR.SucursalEdit(sucursal);
        } catch (Exception ex) {}
    }


    public void sucursalesAgregar(){
        Application.SUCURSAL_AGREGAR.show();
    }

}