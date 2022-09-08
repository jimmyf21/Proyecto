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

