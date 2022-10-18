package sistema.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import sistema.data.Data;
import sistema.data.XmlPersister;

import sistema.data.SucursalDao;

import javax.swing.*;

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
    private Data data;


    // ***************  Empleado  *******************
    public Empleado empleadoGet(String cedula) throws Exception{
        return data.getEmpleados().stream().filter(c->c.getCedula().equals(cedula)).findFirst().orElse(null);
    }

    public List<Empleado> empleadosSearch(String cedula){
        List<Empleado> result=data.getEmpleados().stream().filter(c->c.getNombre().startsWith(cedula)).collect(Collectors.toList());
        return result;
    }

    public List<Empleado> empleadoAll(){
        return data.getEmpleados();
    }

    public void empleadoAdd(Empleado empleado) throws Exception{
        Empleado old= data.getEmpleados().stream().filter(c->c.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (old==null){
            data.getEmpleados().add(empleado);
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        }
        else{
            throw new Exception("Empleado ya existe");
        }

    }

    public void empleadoUpdate(Empleado empleado) throws Exception{
        Empleado result;
        try{
            result = this.empleadoGet(empleado.getCedula());
            data.getEmpleados().remove(result);
            data.getEmpleados().add(empleado);
            JOptionPane.showMessageDialog(null, "Empleado editado con exito");
        }catch (Exception e) {
            throw new Exception("Empleado no existe");
        }
    }

    public void empleadoDelete(Empleado empleado) throws Exception{
        Empleado result;
        try{
            result = data.getEmpleados().stream().filter(e->e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
            if (result!=null) data.getEmpleados().remove(result);

        }catch (Exception e) {
            throw new Exception("Empleado no existe");
        }
    }

    // ***************  Sucursal  *******************

    private SucursalDao sucursalDao;

    public Sucursal sucursalGet(String codigo) throws Exception{
        return sucursalDao.read(codigo);
    }

    public void sucursalAdd(Sucursal sucursal) throws Exception{
        Sucursal old= sucursalDao.findByCode(sucursal.getCodigo());
        if (old==null){
            Point punto2 = new Point((int) sucursal.getPunto().getX(), (int) sucursal.getPunto().getY());
            Punto punto = addUbicSucursales(punto2, sucursal);
            sucursal.setPunto(punto);
            sucursalDao.create(sucursal);
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        }
        else{
            throw new Exception("Sucursal ya existe");
        }
    }

    public void sucursalUpdate(Sucursal sucursal, Point p) throws Exception{

        Sucursal result;
        try{
            sucursal.setPunto(new Punto(p.x, p.y));
            sucursalDao.update(sucursal);
            JOptionPane.showMessageDialog(null, "Sucursal editada con exito");
        }catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }

    public List<Sucursal> sucursalesSearch(String filtro) throws Exception {
        return  sucursalDao.findByReferencia(filtro);
    }

    public Sucursal sucursalSearch(String referencia) throws Exception {
        return  sucursalDao.findByReferencia(referencia).get(0);
    }
    public Sucursal sucursalSearchForCode(String codigo) throws Exception {
        return sucursalDao.findByCode(codigo);
    }



    public List<Sucursal> sucursalAll() throws Exception{
        return sucursalDao.findAll();
    }

    public void sucursalDelete(Sucursal sucursal) throws Exception {
//        Empleado e = sucursalDao.getEmpleados().stream().filter(em -> em.getSucursal().getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
//        Punto p = sucursalDao.getUbicSucursales().stream().filter(em -> em.getSucursalCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
//            if (e == null) {
//                sucursalDao.delete(sucursal);
//                sucursalDao.getUbicSucursales().remove(p);
//            }else{
//                JOptionPane.showMessageDialog (null, "Sucursal con empleados", "Error", JOptionPane.ERROR_MESSAGE);
//            }
    }

//    public Sucursal sucursalGet(String codigo) throws Exception{
//        return data.getSucursales().stream().filter(f->f.getCodigo().equals(codigo)).findFirst().orElse(null);
//    }
//
//    public void sucursalUpdate(Sucursal sucursal, Point p) throws Exception{
//        Sucursal result;
//        try{
//            result = sucursalSearchForCode(sucursal.getCodigo());
//            data.getSucursales().remove(result);
//            for (Punto punto: data.getUbicSucursales()) {
//                if (punto.getSucursalCodigo().equals(sucursal.getCodigo())){
//                    punto.setX(p.getX());
//                    punto.setY(p.getY());
//                    sucursal.setPunto(punto);
//                }
//            }
//            data.getSucursales().add(sucursal);
//            JOptionPane.showMessageDialog(null, "Sucursal editado con exito");
//        }catch (Exception e) {
//            throw new Exception("Sucursal no existe");
//        }
//    }
//
//    public List<Sucursal> sucursalesSearch(String referencia){
//        List<Sucursal> result=data.getSucursales().stream().filter(f->f.getReferencia().startsWith(referencia)).collect(Collectors.toList());
//        return result;
//    }
//
//    public Sucursal sucursalSearch(String referencia){
//        Sucursal result = data.getSucursales().stream().filter(c->c.getReferencia().equals(referencia)).findFirst().orElse(null);
//        return result;
//    }
//
//    public Sucursal sucursalSearchForCode(String codigo){
//        Sucursal result = data.getSucursales().stream().filter(c->c.getCodigo().equals(codigo)).findFirst().orElse(null);
//        return result;
//    }
//
//
//    public void sucursalAdd(Sucursal sucursal, Point p) throws Exception{
//        Sucursal old= data.getSucursales().stream().filter(c->c.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
//        if (old==null){
//            Punto punto = addUbicSucursales(p, sucursal);
//            sucursal.setPunto(punto);
//            data.getSucursales().add(sucursal);
//            JOptionPane.showMessageDialog(null, "Guardado con exito");
//        }
//        else{
//            throw new Exception("Sucursal ya existe");
//        }
//    }
//
//    public void sucursalDelete(Sucursal sucursal) throws Exception {
//        Empleado e = data.getEmpleados().stream().filter(em -> em.getSucursal().getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
//        Punto p = data.getUbicSucursales().stream().filter(em -> em.getSucursalCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
//            if (e == null) {
//                data.getSucursales().remove(sucursal);
//                data.getUbicSucursales().remove(p);
//            }else{
//                JOptionPane.showMessageDialog (null, "Sucursal con empleados", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//    }
//    public List<Sucursal> sucursalAll(){
//        return data.getSucursales();
//    }

    // *****************  Pointers  *****************


    public Punto addUbicSucursales(Point p, Sucursal s) throws Exception
    {
        Punto punto = new Punto(p.getX(), p.getY(), s.getCodigo());
        if (p!=null) {
            data.getUbicSucursales().add(punto);
            return punto;
        }else
            throw new Exception("Punto no existe");
    }

    public List<Point> getPointSucursales() {
        List<Point> p = new ArrayList<>();
        for (Punto punto : data.getUbicSucursales()) {
            p.add(new Point((int) punto.getX(), (int) punto.getY()));
        }
        return p;
    }

    public Sucursal getPoint(Point point) {
        Punto p = new Punto(point.getX(), point.getY());
        double x = p.getX(), y = p.getY();
        for (Sucursal sucursal : data.getSucursales()) {
            for (Punto punto : data.getUbicSucursales()) {
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

    public Point getPointSucursal(Sucursal sucursal) {
        for (Punto punto : data.getUbicSucursales()) {
            if(punto.getSucursalCodigo().equals(sucursal.getCodigo())){
                return new Point((int) punto.getX(), (int) punto.getY());
            }
        }
        return null;
    }

    // *****************  Persistence  *****************
    public void store(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception ex) {
        }
    }
    public Service() {
        sucursalDao= new SucursalDao();
        try{
            data=XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }

    }



}
