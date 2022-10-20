package sistema.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import sistema.data.EmpleadoDao;

import sistema.data.SucursalDao;

public class Service {

    // Singleton implementation
    private static Service theInstance;
    public static Service instance(){
        if (theInstance==null){
            theInstance=new Service();
        }
        return theInstance;
    }

    // Service data



    // ***************  Empleado  *******************

    private EmpleadoDao empleadoDao;

    public Empleado empleadoGet(String cedula) throws Exception{
        return empleadoDao.read(cedula);
    }

    public List<Empleado> empleadosSearch(String cedula) throws Exception{
        return empleadoDao.findByCedula(cedula);
    }

    public List<Empleado> empleadosSearchByName(String nombre) throws Exception{
        return empleadoDao.findByName(nombre);
    }

    public List<Empleado> empleadoAll() throws Exception{
        return empleadoDao.findAll();
    }

    public void empleadoAdd(Empleado empleado) throws Exception{
        empleadoDao.create(empleado);

    }

    public void empleadoUpdate(Empleado empleado) throws Exception{
        empleadoDao.update(empleado);
    }

    public void empleadoDelete(Empleado empleado) throws Exception{
        empleadoDao.delete(empleado);
    }

    // ***************  Sucursal  *******************

    private SucursalDao sucursalDao;

    public Sucursal sucursalGet(String codigo) throws Exception{
        return sucursalDao.read(codigo);
    }

    public void sucursalAdd(Sucursal sucursal) throws Exception{
        sucursalDao.create(sucursal);
        addUbicSucursales(sucursal, sucursal.getPunto());
    }

    public void sucursalUpdate(Sucursal sucursal, Point p) throws Exception{
        sucursalDao.update(sucursal, p);
    }

    public List<Sucursal> sucursalesSearch(String filtro) throws Exception {
        return  sucursalDao.findByReferencia(filtro);
    }
    public Sucursal sucursalSearchForCode(String codigo) throws Exception {
        return sucursalDao.findByCode(codigo);
    }

    public Sucursal sucursalSearchForReference(String codigo) throws Exception {
        return sucursalDao.findByReference(codigo);
    }

    public List<Sucursal> sucursalAll() throws Exception{
        return sucursalDao.findAll();
    }

    public void sucursalDelete(Sucursal sucursal) throws Exception {
        sucursalDao.delete(sucursal);
    }

    public Sucursal sucursalSearch(String codigo) throws Exception {
        return sucursalDao.findByCode(codigo);
    }

    public Sucursal findSucursalByPoint(Point p) throws Exception {
        return sucursalDao.findByPoint(p);
    }

    // *****************  Pointers  *****************


    public Punto addUbicSucursales(Sucursal s, Punto p) throws Exception
    {
        Punto punto2 = new Punto(p.getX(), p.getY(), s.getCodigo());
        if (p!=null) {
            sucursalDao.getUbicSucursales().add(punto2);
            return punto2;
        }else
            throw new Exception("Punto no existe");
    }

    public Punto findUbicRojo (Sucursal sucursal) throws Exception{
        for (Punto p: sucursalDao.getUbicSucursales()){
            if (p.getSucursalCodigo().equals(sucursal.getCodigo())){
                return p;
            }
        }
        throw new Exception("Sucursal no existe");
    }

    public List<Point> getPointSucursales() throws Exception {
        List<Point> p = new ArrayList<>();
        for (Punto punto : sucursalDao.getUbicSucursales()) {
            p.add(new Point((int) punto.getX(), (int) punto.getY()));
        }
        return p;
    }

    public Sucursal findSucursalRangoPunto(Point point) throws Exception {
        Punto p = new Punto(point.getX(), point.getY());
        double x = p.getX(), y = p.getY();
        for (Sucursal sucursal : sucursalDao.findAll()) {
            for (Punto punto : sucursalDao.getUbicSucursales()) {
                if(punto.getSucursalCodigo().equals(sucursal.getCodigo())){
                    sucursal.setPunto(punto);
                    if (x <= sucursal.getPunto().getX() && x >= sucursal.getPunto().getX()-10 && y <= sucursal.getPunto().getY() && y >= sucursal.getPunto().getY()-10 || x >= sucursal.getPunto().getX() && x <= sucursal.getPunto().getX()+10 && y >= sucursal.getPunto().getY() && y <= sucursal.getPunto().getY()+10) {
                        return sucursal;
                    }
                }
            }
        }
        return null;
    }

    public Point getPointSucursal(Sucursal sucursal) throws Exception {
        for (Punto punto : sucursalDao.getUbicSucursales()) {
            if(punto.getSucursalCodigo().equals(sucursal.getCodigo())){
                return new Point((int) punto.getX(), (int) punto.getY());
            }
        }
        return null;
    }



    // *****************  Persistence  *****************
    public void store(){


    }
    public Service() {
        sucursalDao= new SucursalDao();
        empleadoDao= new EmpleadoDao();


    }



}
