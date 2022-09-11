package sistema.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

import sistema.logic.Sucursal;
import sistema.logic.Empleado;

@XmlRootElement//(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    private List<Empleado> empleados;
    private List<Sucursal> sucursales;

    public Data() {
        empleados = new ArrayList<>();
        sucursales = new ArrayList<>();


        sucursales.add(new Sucursal("001", "Liberia", "Guanacaste", 2));
        sucursales.add(new Sucursal("222", "Sabana", "San Jose", 3));
        sucursales.add(new Sucursal("333", "Golfito", "Puntarenas", 4));

        empleados.add(new Empleado("111", "Franklin Chang", "78972356", 7500,   new Sucursal("001", "Liberia", "Guanacaste", 2)));
        empleados.add(new Empleado("222", "Sandra Cauffman", "78972356", 7500,   new Sucursal( "001", "Liberia", "Guanacaste", 2)));
        empleados.add(new Empleado("333", "Ivan Vargas", "78972356", 7500,   new Sucursal("001", "Liberia", "Guanacaste", 2)));
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}

