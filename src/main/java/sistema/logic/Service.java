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

    private Service(){
        data = new Data();
    }


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

    // ***************  Sucursal  *******************

    public Sucursal sucursalGet(String numero) throws Exception{
        Sucursal result= data.getSucursales().stream().filter(f->f.getCodigo().equals(numero)).findFirst().orElse(null);
        if (result!=null) {
            return result;
        }
        else {
            throw new Exception("Sucursal no existe");
        }
    }

    public List<Sucursal> sucursalesSearch(String numero){
        List<Sucursal> result=data.getSucursales().stream().filter(f->f.getReferencia().startsWith(numero)).collect(Collectors.toList());
        return result;
    }


    public void sucursalAdd(Sucursal prestamo) throws Exception{
        Sucursal old= data.getSucursales().stream().filter
                (f->f.getCodigo().equals(prestamo.getCodigo())).findFirst().orElse(null);
        if (old==null) {
            data.getSucursales().add(prestamo);
        }
        else{
            throw new Exception("Sucursal ya existe");
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

//    public Service() {
//        try{
//            data=XmlPersister.instance().load();
//        }
//        catch(Exception e){
//            data =  new Data();
//        }
//
//    }

}
