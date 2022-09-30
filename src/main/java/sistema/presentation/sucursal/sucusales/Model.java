package sistema.presentation.sucursal.sucusales;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

import sistema.logic.Sucursal;

public class Model extends Observable {
    List<Sucursal> sucursales;

    List<Point> ubicSucursales;


    public Model() {
        this.sucursales = new ArrayList<>();
        this.ubicSucursales = new ArrayList<>();
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<Point> getUbicSucursales() {
        return ubicSucursales;
    }

    public void setUbicSucursales(List<Point> ubicSucursales) {
        this.ubicSucursales = ubicSucursales;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit() {
        this.setChanged();
        this.notifyObservers(null);
    }
}