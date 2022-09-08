package sistema.logic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sucursal {
    @XmlID
    private String codigo;
    private String referencia;
    private String direccion;
    private float zonaje;

    public Sucursal(String codigo, String referencia, String direccion, float zonaje) {
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

    public float getZonaje() {
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
