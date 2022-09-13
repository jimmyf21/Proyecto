package sistema.presentation.empleado.empleadoAgregar;

import java.util.Observable;
import java.util.Observer;
import sistema.logic.Empleado;

public class Model extends Observable {
    Empleado Empleado;

    int modo;

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