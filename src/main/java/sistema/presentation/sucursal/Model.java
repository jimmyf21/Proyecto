package sistema.presentation.sucursal;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sistema.logic.Sucursal;

public class Model extends Observable{
    Sucursal sucursal;
    List<Sucursal> sucursales;
    

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }


    public List<Sucursal> getSucursals() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursals) {
        sucursales = sucursals;
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