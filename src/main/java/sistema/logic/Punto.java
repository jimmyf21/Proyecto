package sistema.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Punto {
    @XmlID
    private String sucursalCodigo;
    private double x;
    private double y;

    public Punto() {
    }

    public Punto( double x, double y, String sucursalCodigo){
        this.sucursalCodigo = sucursalCodigo;
        this.x = x;
        this.y = y;
    }

    public String getSucursalCodigo() {
        return sucursalCodigo;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setSucursalCodigo(String sucursalCodigo) {
        this.sucursalCodigo = sucursalCodigo;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean equals(Punto point){
        if ((point.getX() == this.getX()) && (point.getY() == this.getY())){
            return true;
        }
        return false;
    }
}
