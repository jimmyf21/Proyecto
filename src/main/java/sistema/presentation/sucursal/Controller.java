package sistema.presentation.sucursal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sistema.application.Application;
import sistema.logic.Sucursal;
import sistema.logic.Service;

import sistema.presentation.principal.ControllerPrincipal;

public class Controller {
    Model model;
    View view;

    ControllerPrincipal controllerPrincipal;

    public Controller(Model modelSucursal, View viewSucursal, ControllerPrincipal controllerPrincipal) {
        this.model = modelSucursal;
        this.view = viewSucursal;

        modelSucursal.setSucursal(new Sucursal());
        modelSucursal.setSucursales(new ArrayList<>());
        modelSucursal.setSucursales(Service.instance().sucursalAll());


        this.controllerPrincipal = controllerPrincipal;

        view.setModel(modelSucursal);
        view.setController(this);
    }



    public void show(){
        Application.window.setContentPane(view.getPanel1());
        Application.window.setVisible(true);
    }

    public void hide(){
        this.view.setVisible(false);
        Application.PRINCIPAL.show();
    }

    public void SucursalGet(String codigo){
        try {
            Sucursal Sucursal = Service.instance().sucursalGet(codigo);
            model.setSucursal(Sucursal);
            model.setSucursales(Arrays.asList(Sucursal));
            model.commit();
        } catch (Exception ex) {
            model.setSucursal(new Sucursal());
            model.setSucursales(new ArrayList<>());
            model.commit();
        }
    }


    public void  SucursalSearch(String codigo){
        List<Sucursal> Sucursals= Service.instance().sucursalSearch(codigo);
        model.setSucursal(new Sucursal(codigo,"", "", 0));
        model.setSucursales(Sucursals);
        model.commit();
    }

    public void SucursalEdit(int row){
        Sucursal Sucursal = model.getSucursales().get(row);
        model.setSucursal(Sucursal);
        model.commit();
    }

    public void SucursalAdd(Sucursal sucursal){
        try {
            Service.instance().sucursalAdd(sucursal);
            model.setSucursal(new Sucursal("","", "", 0));
            model.setSucursales(Arrays.asList(sucursal));
            model.commit();
        } catch (Exception ex) {

        }

    }

    public ControllerPrincipal getControllerPrincipal() {
        return controllerPrincipal;
    }
}
