package hf;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BerlesekTable extends AbstractTableModel {
    static ArrayList<Berles> berlesek = new ArrayList<>();

    @Override
    public int getRowCount() {
        return berlesek.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Berles berles = berlesek.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return berles.getAuto().toString();
            case 1:
                return berles.getBerlo().toString();
            case 2:
                return berles.getDatum();
            default: {
                return berles.getAktiv();
            }
        }
    }

    @Override
    public String getColumnName(int rowIndex) {
        switch (rowIndex) {
            case 0:
                return "Autó";
            case 1:
                return "Berlő";
            case 2:
                return "Dátum";
            default:
                return "Bérlés aktív";
        }
    }

    @Override
    public Class<?> getColumnClass(int rowIndex) {
        switch (rowIndex) {
            case 0:
                return Auto.class;
            case 1:
                return Berlo.class;
            case 2:
                return String.class;
            default:
                return Boolean.class;
        }
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Berles b = berlesek.get(rowIndex);
        switch (columnIndex) {
            case 0:
                b.setAuto((Auto) o);
                break;
            case 1:
                b.setBerlo((Berlo) o);
                break;
            case 2:
                b.setDatum((String) o);
                break;
            default:
                b.setAktiv((Boolean) o);
                break;

        }
        berlesek.set(rowIndex, b);
        this.fireTableRowsUpdated(rowIndex, columnIndex);
    }

    public void removeRow(int rowIndex){
        berlesek.remove(rowIndex);
        this.fireTableDataChanged();
    }

    public void addBerles(Auto auto, Berlo berlo) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        berlesek.add(new Berles(auto, berlo, dtf.format(now)));

        this.fireTableDataChanged();
    }

    public ArrayList<Berles> getBerlesek() { return berlesek; }
}
