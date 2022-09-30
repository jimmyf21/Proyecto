package sistema.presentation.empleado.empleadoAgregar;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ImagenModel extends JLabel {

    private BufferedImage mapa;

    private List<Point> ubicSucursales;
    private Point ubicSucursal;

    public ImagenModel(Point ubicaciones) {
        this.ubicSucursal= ubicaciones;
        mostrarImagen();
    }

    public ImagenModel(List<Point> ubicaciones) {
        this.ubicSucursales= ubicaciones;
        mostrarImagen();
    }

    public void mostrarImagen()  {
        try {
            mapa = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("imagenes/mapa.png")));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar la imagen");
        }
    }

    public JLabel mostrarUbicaciones() {
        try {
            BufferedImage icono = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("imagenes/Sucursal.png")));

            for (int i = 0; i < ubicSucursales.size(); i++) {
                Graphics graphics = mapa.getGraphics();
                graphics.drawImage(mapa, 0, 0, null);
                graphics.drawImage(icono, ubicSucursales.get(i).x-16, ubicSucursales.get(i).y-30, null);
            }
            ImageIO.write(mapa, "PNG", new File("mapaCargar.png"));
            if(ubicSucursal !=null) {
                mostrarPuntoRojo();
            }
            this.setIcon(new ImageIcon(mapa));
            ubicSucursal = null;
        } catch (Exception e) {
        }
        return this;
    }

    public JLabel mostrarPuntoRojo() {
        try {
            BufferedImage icono = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("imagenes/SucursalSel.png")));

            Graphics graphics = mapa.getGraphics();
            graphics.drawImage(mapa, 0, 0, null);
            graphics.drawImage(icono, ubicSucursal.x-16, ubicSucursal.y-30, null);
            ImageIO.write(mapa, "PNG", new File("mapaCargar.png"));
            this.setIcon(new ImageIcon(mapa));

        } catch (Exception e) {
        }
        return this;
    }


    public BufferedImage getMapa() {
        return mapa;
    }

    public void setMapa(BufferedImage mapa) {
        this.mapa = mapa;
    }

    public Point getUbicSucursal() {
        return ubicSucursal;
    }

    public void setUbicSucursal(Point ubicSucursal) {
        this.ubicSucursal = ubicSucursal;
    }
}