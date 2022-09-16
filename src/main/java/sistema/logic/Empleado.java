package sistema.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;


@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado {
    @XmlID
    private String cedula;
    private String nombre;
    private String telefono;
    private double salario;
    @XmlIDREF
    private Sucursal sucursal;


    public Empleado(String cedula, String nombre, String telefono, double salario, Sucursal sucursal) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salario = salario;
        this.sucursal = sucursal;

    }

    public Empleado() {
        this.cedula = "";
        this.nombre = "";
        this.telefono = "";
        this.salario = 0;
        this.sucursal = new Sucursal();

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int calSalTotal() {
        return (int) (this.salario + (salario*this.sucursal.getZonaje() / 100));
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", salario=" + salario + '\'' +
                ", sucursal='" + sucursal.getReferencia() + '\'' +
                ", sucursal='" + sucursal.getZonaje() + '\'' +
                ", salTotal='" + calSalTotal() + '\'' +
                '}';
    }
}
