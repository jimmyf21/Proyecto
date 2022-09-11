package sistema.presentation.empleado.empleadoAgregar;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import sistema.logic.Empleado;
import sistema.logic.Sucursal;


public class Model extends Observable {
    Empleado Empleado;
    List<Empleado> Empleados;
    List<Sucursal> Sucursales;
   

    public Empleado getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(Empleado Empleado) {
        this.Empleado = Empleado;
    }

    public List<Empleado> getEmpleados() {
        return Empleados;
    }

    public void setEmpleados(List<Empleado> Empleados) {
        this.Empleados = Empleados;
    }

    public List<Sucursal> getSucursals() {
        return Sucursales;
    }

    public void setSucursales(List<Sucursal> Sucursales) {
        this.Sucursales = Sucursales;
    }


    public Sucursal getSucursalByCode(String SucursalCode){
        for(int i = 0; i < Sucursales.size(); i++){
            if(SucursalCode == null ? Sucursales.get(i).getCodigo() == null : SucursalCode.equals(Sucursales.get(i).getCodigo())){
                return Sucursales.get(i);
            }
        }
        return null;
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