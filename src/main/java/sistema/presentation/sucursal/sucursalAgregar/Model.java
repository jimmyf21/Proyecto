package sistema.presentation.sucursal.sucursalAgregar;


import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sistema.logic.Sucursal;

public class Model extends Observable{
    Sucursal sucursal;
    int modo;

    Point ubicacionActual;

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }


    public Point getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(Point ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit() {
        this.setChanged();
        this.notifyObservers();
    }

}