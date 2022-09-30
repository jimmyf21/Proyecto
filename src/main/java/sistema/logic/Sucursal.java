package sistema.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sucursal {
    @XmlID
    private String codigo;
    private String referencia;
    private String direccion;
    private double zonaje;

    @XmlIDREF
    private Punto punto;

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public Sucursal(String codigo, String referencia, String direccion, double zonaje) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.direccion = direccion;
        this.zonaje = zonaje;
    }

    public Sucursal() {
        this.codigo = "";
        this.referencia = "";
        this.direccion = "";
        this.zonaje = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getZonaje() {
        return zonaje;
    }

    public void setZonaje(float zonaje) {
        this.zonaje = zonaje;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "codigo='" + codigo + '\'' +
                ", referencia='" + referencia + '\'' +
                ", direccion='" + direccion + '\'' +
                ", zonaje=" + zonaje +
                '}';
    }


}
