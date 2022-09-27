package sistema.presentation.sucursal.sucusales;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class ImagenModel extends JLabel {

    private BufferedImage mapa;
    private List<Point> ubicSucursales;

    public ImagenModel(List<Point> ubicaciones) {
        this.ubicSucursales = ubicaciones;
        mostrarImagen();
    }

    public void mostrarImagen() {
        try {
            mapa = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("mapa.png")));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar la imagen");
        }
    }

    public JLabel mostrarUbicaciones() {
        try {
            BufferedImage overlay = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("Sucursal.png")));

            for (int i = 0; i < ubicSucursales.size(); i++) {
                Graphics graphics = mapa.getGraphics();
                graphics.drawImage(mapa, 0, 0, null);
                graphics.drawImage(overlay, ubicSucursales.get(i).x - 16, ubicSucursales.get(i).y - 30, null);
            }
            ImageIO.write(mapa, "PNG", new File("temporal.png"));
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

    public List<Point> getUbicSucursales() {
        return ubicSucursales;
    }

    public void setUbicSucursales(List<Point> ubicSucursales) {
        this.ubicSucursales = ubicSucursales;
    }
}