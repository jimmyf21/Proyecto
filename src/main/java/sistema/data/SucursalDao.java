package sistema.data;

import sistema.logic.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.logic.Punto;

import java.awt.Point;

public class SucursalDao {
    Database db;

    public SucursalDao() {
        db = Database.instance();
    }

    public void create(Sucursal e) throws Exception {
        String sql = "insert into " +
                "Sucursal " +
                "(codigo, referencia, direccion, zonaje, ubicacionX, ubicacionY) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        stm.setString(2, e.getReferencia());
        stm.setString(3, e.getDireccion());
        stm.setDouble(4, e.getZonaje());
        stm.setInt(5, (int) e.getPunto().getX());
        stm.setInt(6, (int) e.getPunto().getY());

        db.executeUpdate(stm);
    }

    public Sucursal read(String codigo) throws Exception {
        String sql = "select " +
                "* " +
                "from  Sucursal s " +
                "where s.codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "s");
        } else {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }

    public void update(Sucursal e, Point p) throws Exception {
        String sql = "update " +
                "Sucursal " +
                "set referencia=?, direccion=?, zonaje=?, ubicacionX=?, ubicacionY=?  " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getReferencia());
        stm.setString(2, e.getDireccion());
        stm.setDouble(3, e.getZonaje());
        stm.setInt(4, (int) e.getPunto().getX());
        stm.setInt(5, (int) e.getPunto().getY());
        stm.setString(6, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("SUCURSAL NO EXISTE");
        }

    }

    public void delete(Sucursal e) throws Exception {
        String sql = "delete " +
                "from Sucursal " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }

    public List<Sucursal> findAll() throws Exception {
        List<Sucursal> resultado = new ArrayList<>();

        try {
            String sql = "select * from Sucursal s";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            Sucursal s;
            while (rs.next()) {
                s = from(rs, "s");
                resultado.add(s);
            }
        } catch (SQLException ex) {

        }
        return resultado;
    }

    public List<Sucursal> findByReferencia(String referencia) throws Exception {
        List<Sucursal> resultado = new ArrayList<Sucursal>();
        String sql = "select * " +
                "from " +
                "Sucursal s " +
                "where s.referencia like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + referencia + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            resultado.add(from(rs, "s"));
        }
        return resultado;
    }

    public List<Punto> getUbicSucursales() throws Exception {
        List<Punto> resultado = new ArrayList<Punto>();
        String sql = "select s.codigo, s.ubicacionX, s.ubicacionY " +
                "from " +
                "Sucursal s ";
        PreparedStatement stm = db.prepareStatement(sql);
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            resultado.add(new Punto(rs.getInt("ubicacionX"), rs.getInt("ubicacionY"), rs.getString("codigo")));
        }
        return resultado;
    }

    public Sucursal findByCode(String codigo) throws Exception {
        String sql = "select * " +
                "from " +
                "Sucursal s " +
                "where s.codigo = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "s");
        } else {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }



    public static Sucursal from(ResultSet rs, String alias) throws Exception {
        Sucursal e = new Sucursal();
        e.setCodigo(rs.getString(alias + ".codigo"));
        e.setReferencia(rs.getString(alias + ".referencia"));
        e.setDireccion(rs.getString(alias + ".direccion"));
        e.setZonaje((float) rs.getDouble(alias + ".zonaje"));
        e.setPuntoXY(rs.getInt(alias + ".ubicacionX"), rs.getInt(alias + ".ubicacionY"));
        return e;
    }

}
