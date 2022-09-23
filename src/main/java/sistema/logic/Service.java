package sistema.logic;

import java.util.List;
import java.util.stream.Collectors;
import sistema.data.Data;
import sistema.data.XmlPersister;

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

   /* private Service(){
        data = new Data();
    }*/


    // ***************  Empleado  *******************
    public Empleado empleadoGet(String cedula) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(c->c.getCedula().equals(cedula)).findFirst().orElse(null);
        if (result!=null){
            return result;
        }
        else {
            throw new Exception("Empleado no existe");
        }
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
        Sucursal result= data.getSucursales().stream().filter(f->f.getReferencia().equals(referencia)).findFirst().orElse(null);
        if (result!=null) {
            return result;
        }
        else {
            throw new Exception("Sucursal no existe");
        }
    }

    public void sucursalUpdate(Sucursal sucursal) throws Exception{
        Sucursal result;
        try{
            result = this.sucursalGet(sucursal.getReferencia());
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


    public void sucursalAdd(Sucursal sucursal) throws Exception{
        Sucursal old= data.getSucursales().stream().filter(c->c.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (old==null){
            data.getSucursales().add(sucursal);
        }
        else{
            throw new Exception("Sucursal ya existe");
        }

    }

    public void sucursalDelete(Sucursal sucursal) throws Exception{
        Sucursal result;
        try{
            result = data.getSucursales().stream().filter(e->e.getReferencia().equals(sucursal.getReferencia())).findFirst().orElse(null);
            if (result!=null) data.getSucursales().remove(result);

        }catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }

    public List<Sucursal> sucursalAll(){
        return data.getSucursales();
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
