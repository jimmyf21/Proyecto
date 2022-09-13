package sistema.presentation.sucursal.sucusales;

import java.util.Observable;
import java.util.Observer;
import java.util.List;

import sistema.logic.Sucursal;

public class Model extends Observable {
    Sucursal sucursal;
    List<Sucursal> sucursales;

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
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