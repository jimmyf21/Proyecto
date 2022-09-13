package sistema.presentation.empleado.empleados;


import java.util.Observable;
import java.util.Observer;
import java.util.List;

import sistema.logic.Empleado;


public class Model extends Observable {

    Empleado empleado;
    List<Empleado> empleados;


    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
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