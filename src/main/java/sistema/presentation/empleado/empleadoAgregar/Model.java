package sistema.presentation.empleado.empleadoAgregar;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import sistema.logic.Empleado;
import sistema.logic.Punto;

public class Model extends Observable {
    Empleado Empleado;

    int modo;

    Point ubicacion;

    ImagenModel mapa;

    public Model() {
    }
    public Empleado getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(Empleado Empleado) {
        this.Empleado = Empleado;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }


    public Point getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Point ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ImagenModel getMapa() {
        return mapa;
    }


    public void setMapa(ImagenModel mapa) {
        this.mapa = mapa;
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