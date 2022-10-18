package sistema.data;

import sistema.logic.Empleado;
import sistema.logic.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {
    Database db;

    public EmpleadoDao() {
        db = Database.instance();
    }

    public void create(Empleado e) throws Exception {
        String sql = "insert into " +
                "Empleado " +
                "(cedula, nombre, telefono, salario, sucursal) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        stm.setString(2, e.getNombre());
        stm.setString(3, e.getTelefono());
        stm.setDouble(4, e.getSalario());
        stm.setString(5, e.getSucursal().getCodigo());

        db.executeUpdate(stm);
    }

    public Empleado read(String cedula) throws Exception {
        Empleado e = new Empleado();
        String sql = "select " +
                "* " +
                "from Empleado e " +
                "where e.cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            e = from(rs, "e");
            e.setSucursal(SucursalDao.from(rs, "s"));
            return e;
        }else{
            throw new Exception("EMPLEADO NO EXISTE");
        }

    }

    public void update(Empleado e) throws Exception {
        String sql = "update Empleado set nombre=?, telefono=?, salario=?, sucursal=? where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNombre());
        stm.setString(2, e.getTelefono());
        stm.setDouble(3, e.getSalario());
        stm.setString(4, e.getCedula());
        stm.setString(5, e.getSucursal().getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }

    public void delete(Empleado e) throws Exception {
        String sql = "delete from Empleado where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        db.executeUpdate(stm);
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }

    public List<Empleado> findAll() throws Exception {
        List<Empleado> resultado = new ArrayList<>();

        try {
            String sql = "select * from Empleado e";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            Empleado e;
            while (rs.next()) {
                e = from(rs, "e");
                e.setSucursal(SucursalDao.from(rs, "s"));
                resultado.add(e);
            }
        } catch (SQLException ex) {
            throw new Exception("Error al buscar todos los empleados", ex);
        }
        return resultado;
    }

    public List<Empleado> findByCedula(String cedula) throws Exception {
        List<Empleado> resultado = new ArrayList<Empleado>();
        String sql = "select * " +
                "from " +
                "empleado e " +
                "where e.cedula like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + cedula + "%");
        ResultSet rs = db.executeQuery(stm);
        Empleado e = new Empleado();
        while (rs.next()) {
            e = from(rs, "e");
            e.setSucursal(SucursalDao.from(rs, "s"));
            resultado.add(e);
        }
        return resultado;
    }

    public Empleado getEmpleadoEnBD(String cedula) throws Exception {
        String sql = "select * " +
                "from " +
                "empleado e " +
                "where e.cedula = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs = db.executeQuery(stm);
        Empleado e = new Empleado();
        if (rs.next()) {
            e = from(rs, "e");
            e.setSucursal(SucursalDao.from(rs, "s"));
            return e;
        } else {
            return null;
        }
    }

    public Empleado from(ResultSet rs, String alias) throws Exception {
        Empleado e = new Empleado();
        e.setCedula(rs.getString(alias + ".cedula"));
        e.setNombre(rs.getString(alias + ".nombre"));
        e.setTelefono(rs.getString(alias + ".telefono"));
        e.setSalario((float) rs.getDouble(alias + ".salario"));
       /* e.setSucursal(new SucursalDao().read(rs.getString(alias + ".sucursal")));*/
        return e;
    }
}
