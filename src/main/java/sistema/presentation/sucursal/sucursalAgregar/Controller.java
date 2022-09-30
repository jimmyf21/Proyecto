package sistema.presentation.sucursal.sucursalAgregar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sistema.application.Application;
import sistema.logic.Empleado;
import sistema.logic.Sucursal;
import sistema.logic.Service;

import sistema.presentation.principal.ControllerPrincipal;

import javax.swing.*;

public class Controller {
    Model model;
    View view;

    JDialog dialog;

    public Controller(View viewSucursal, Model modelSucursal) {
        this.model = modelSucursal;
        this.view = viewSucursal;

        modelSucursal.setSucursal(new Sucursal());

        view.setModel(modelSucursal);
        view.setController(this);
    }

    public void preAgregar() {
        model.setModo(Application.MODO_AGREGAR);
        model.setSucursal(new Sucursal());
        model.commit();
    }



    public void show(){
        dialog = new JDialog((Frame) null, "Sucursal", true);
        dialog.setContentPane(view.getPanel1());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
    }

    public void SucursalEdit(Sucursal e){
        model.setModo(Application.MODO_EDITAR);
        model.setSucursal(e);
        model.commit();
        this.show();
    }

    public Boolean SucursalAdd(Sucursal sucursal, Point p){
        boolean result = false;
        try {
            switch (model.getModo()) {
                case Application.MODO_AGREGAR:
                    Service.instance().sucursalAdd(sucursal, p);
                    Service.instance().store();
                    Sucursal s = Service.instance().sucursalGet(sucursal.getCodigo());
                    if(s == null){
                        hide();
                    }else{
                        result = true;
                    }
                    break;
                case Application.MODO_EDITAR:
                    Service.instance().sucursalUpdate(sucursal);
                    model.setSucursal(sucursal);
                    break;
            }
            Application.SUCURSALES.searchSucursal("");
            model.commit();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public void getUbicacionActual(Sucursal s){
        model.setUbicacionActual(new Point(Service.instance().getPointSucursal(s)));
    }

}
