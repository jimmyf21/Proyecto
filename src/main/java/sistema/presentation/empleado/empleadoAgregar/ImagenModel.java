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

    private Point ubicSucursal;

    public ImagenModel(Point ubicaciones) {
        this.ubicSucursal= ubicaciones;
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
        JLabel sucursalmap = new JLabel();
        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("imagenes/Sucursal.png")));


            Graphics graphics = mapa.getGraphics();
            graphics.drawImage(mapa, 0, 0, null);
            graphics.drawImage(bufferedImage, ubicSucursal.x - 16, ubicSucursal.y - 30, null);

            ImageIO.write(mapa, "PNG", new File("mapaCargar.png"));
            sucursalmap.setIcon(new ImageIcon(mapa));

        } catch (Exception e) {

        }
        return sucursalmap;
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