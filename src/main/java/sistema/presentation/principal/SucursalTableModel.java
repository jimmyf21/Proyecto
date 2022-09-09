package sistema.presentation.principal;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import sistema.logic.Sucursal;

public class SucursalTableModel extends AbstractTableModel implements TableModel {
    String[] cols ={"Codigo","Referencia","Direccion", "Zonaje" };
    List<Sucursal> rows;

    public  SucursalTableModel(List<Sucursal> rows){
        this.rows=rows;
    }

    public int getColumnCount() {
        return 4;
    }

    public String getColumnName(int col){
        return cols[col];
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int col) {
        Sucursal sucursal = rows.get(row);
        switch (col){
            case 0: return sucursal.getCodigo();
            case 1:return sucursal.getReferencia();
            case 2:return sucursal.getDireccion();
            case 3:return sucursal.getZonaje();
            default: return "";
        }
    }
}