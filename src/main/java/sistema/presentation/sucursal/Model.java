package sistema.presentation.sucursal;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sistema.logic.Sucursal;

public class Model extends Observable{
    Sucursal Sucursal;
    List<Sucursal> Sucursales;
    

    public Sucursal getSucursal() {
        return Sucursal;
    }

    public void setSucursal(Sucursal Sucursal) {
        this.Sucursal = Sucursal;
    }


    public List<Sucursal> getSucursals() {
        return Sucursales;
    }

    public void setSucursales(List<Sucursal> Sucursals) {
        this.Sucursales = Sucursals;
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