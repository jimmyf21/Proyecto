package sistema.presentation.principal;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import sistema.logic.Empleado;

public class EmpleadoTableModel extends AbstractTableModel implements TableModel {
    String[] cols ={"Cedula","Nombre","Telefono", "Salario", "Sucursal", "% Zonaje", "Sal. Total"};
    List<Empleado> rows;

    public  EmpleadoTableModel(List<Empleado> rows){
        this.rows=rows;
    }

    public int getColumnCount() {
        return 7;
    }

    public String getColumnName(int col){
        return cols[col];
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int col) {
        Empleado empleado = rows.get(row);
        switch (col){
            case 0: return empleado.getCedula();
            case 1:return empleado.getNombre();
            case 2: return empleado.getTelefono();
            case 3:return empleado.getSalario();
            case 4: return empleado.getSucursal();
            case 5:return empleado.getSucursal().getZonaje();
            case 6:return empleado.calSalTotal();
            default: return "";
        }
    }
}
