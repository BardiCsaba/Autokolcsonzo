package hf;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AutokTable extends AbstractTableModel {

    static ArrayList<Auto> autok = new ArrayList<>();

    @Override
    public int getRowCount() {
        return autok.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Auto auto = autok.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return auto.getRendszam();
            case 1:
                return auto.getTipus();
            default:
                return auto.getFerohely();
        }
    }

    @Override
    public String getColumnName(int rowIndex) {
        switch (rowIndex) {
            case 0:
                return "Rendszám";
            case 1:
                return "Típus";
            default:
                return "Férőhely";
        }
    }

    @Override
    public Class<?> getColumnClass(int rowIndex) {
        switch (rowIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            default:
                return Integer.class;
        }
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Auto a = autok.get(rowIndex);
        switch (columnIndex) {
            case 0:
                a.setRendszam((String) o);
                break;
            case 1:
                a.setTipus((String) o);
                break;
            default:
                a.setFerohely((Integer) o);
                break;

        }
        autok.set(rowIndex, a);
        this.fireTableRowsUpdated(rowIndex, columnIndex);
    }
    public void removeRow(int rowIndex){
        autok.remove(rowIndex);
        this.fireTableDataChanged();
    }

    public void addAuto(String rendszam, String tipus, int ferohely) {
        autok.add(new Auto(rendszam, tipus, ferohely));
        this.fireTableDataChanged();
    }

    public Auto searchAuto(String string) {
        try {
            for (Auto auto : autok) {
                if (auto.getRendszam().matches(string))
                    return auto;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Auto> getAutok() { return autok; }
}
