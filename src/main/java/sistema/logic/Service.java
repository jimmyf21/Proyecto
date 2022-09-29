package sistema.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import sistema.data.Data;
import sistema.data.XmlPersister;

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

    public Sucursal sucursalGet(String referencia) throws Exception{
        return data.getSucursales().stream().filter(f->f.getReferencia().equals(referencia)).findFirst().orElse(null);
    }

    public void sucursalUpdate(Sucursal sucursal) throws Exception{
        Sucursal result;
        try{
            result = sucursaleSearchForCode(sucursal.getCodigo());
            data.getSucursales().remove(result);
            data.getSucursales().add(sucursal);
        }catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }

    public List<Sucursal> sucursalesSearch(String referencia){
        List<Sucursal> result=data.getSucursales().stream().filter(f->f.getReferencia().startsWith(referencia)).collect(Collectors.toList());
        return result;
    }

    public Sucursal sucursaleSearch(String referencia){
        Sucursal result = data.getSucursales().stream().filter(c->c.getReferencia().equals(referencia)).findFirst().orElse(null);
        return result;
    }

    public Sucursal sucursaleSearchForCode(String codigo){
        Sucursal result = data.getSucursales().stream().filter(c->c.getCodigo().equals(codigo)).findFirst().orElse(null);
        return result;
    }


    public void sucursalAdd(Sucursal sucursal, Point p) throws Exception{
        Sucursal old= data.getSucursales().stream().filter(c->c.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (old==null){
            data.getSucursales().add(sucursal);
            addUbicSucursales(p, sucursal);
            JOptionPane.showMessageDialog(null, "Guardado con exito");
        }
        else{
            throw new Exception("Sucursal ya existe");
        }

    }

    public void sucursalDelete(Sucursal sucursal) throws Exception {
        Sucursal result = data.getSucursales().stream().filter(e -> e.getReferencia().equals(sucursal.getReferencia())).findFirst().orElse(null);
        Empleado e = data.getEmpleados().stream().filter(em -> em.getSucursal().getReferencia().equals(sucursal.getReferencia())).findFirst().orElse(null);
        Punto p = data.getUbicSucursales().stream().filter(em -> em.getSucursalCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
            if (e == null) {
                data.getSucursales().remove(result);
                data.getUbicSucursales().remove(p);
            }else{
                JOptionPane.showMessageDialog (null, "Sucursal con empleados", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    public List<Sucursal> sucursalAll(){
        return data.getSucursales();
    }

    // *****************  Pointers  *****************

    public void setUbicSucursales(List<Punto> list) {
        data.setUbicSucursales(list);
    }
    public List<Punto> getUbicSucursales() {
        return data.getUbicSucursales();
    }

    public void addUbicSucursales(Point p, Sucursal s) throws Exception
    {
        Punto punto = new Punto(p.getX(), p.getY(), s.getCodigo());
        if (p!=null)
            data.getUbicSucursales().add(punto);
        else
            throw new Exception("Punto no existe");
    }

    public List<Point> getPointSucursales() {
        List<Point> p = new ArrayList<>();
        Point point = new Point();
        for (Punto punto : data.getUbicSucursales()) {
            p.add(point = new Point((int) punto.getX(), (int) punto.getY()));
        }
        return p;
    }

    // *****************  Persistence  *****************
    public void store(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception ex) {
        }
    }
    public Service() {
        try{
            data=XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }

    }



}
