package hf;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BerlokTable extends AbstractTableModel {

    static ArrayList<Berlo> berlok = new ArrayList<>();

    @Override
    public int getRowCount() { return berlok.size(); }

    @Override
    public int getColumnCount() { return 4; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Berlo berlo = berlok.get(rowIndex);
        switch (columnIndex) {
            case 0: return berlo.getId();
            case 1: return berlo.getNev();
            case 2: return berlo.getCim();
            default: return berlo.getTelefonszam();
        }
    }

    @Override
    public String getColumnName(int i)
    {
        switch(i)
        {
            case 0: return "Azonosító";
            case 1: return "Név";
            case 2: return "Lakcím";
            default: return "Telefonszám";
        }
    }

    @Override
    public Class<?> getColumnClass(int rowIndex)
    {
        switch(rowIndex)
        {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            default: return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex)
    {
        Berlo b = berlok.get(rowIndex);
        switch(columnIndex)
        {
            case 0: break;
            case 1: b.setNev((String)o); break;
            case 2: b.setCim((String)o); break;
            default: b.setTelefonszam((String)o); break;
        }
        berlok.set(rowIndex, b);
        this.fireTableRowsUpdated(rowIndex, columnIndex);
    }
    public void removeRow(int rowIndex){
        berlok.remove(rowIndex);
        this.fireTableDataChanged();
    }

    public void addBerlo(String nev, String cim, String telefonszam)
    {
        berlok.add(new Berlo(nev,cim,telefonszam));
        this.fireTableDataChanged();
    }

    public Berlo searchBerlo(String id) {
        try {
            for (Berlo berlo : berlok) {
                if (id.equals(berlo.getId()))
                    return berlo;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Berlo> getBerlok() { return berlok; }
}
