package sistema.data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import sistema.logic.Punto;
import sistema.logic.Sucursal;
import sistema.logic.Empleado;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    private List<Sucursal> sucursales;
    private List<Empleado> empleados;

    private List<Punto> ubicSucursales;

    public Data() {
        sucursales = new ArrayList<>();
        empleados = new ArrayList<>();
        ubicSucursales = new ArrayList<>();
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

    public void setUbicSucursales(List<Punto> ubicaciones) { this.ubicSucursales = ubicaciones; }

    public List<Punto> getUbicSucursales() { return ubicSucursales; }
}

