package hf;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AutoPanel extends JPanel {
    private AutokTable autokTable;

    public AutoPanel () {

        try {
            autokTable = new AutokTable();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("autok.txt"));
            autokTable.autok = (ArrayList<Auto>)ois.readObject();
            ois.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JTable jt = new JTable(autokTable);
        TableRowSorter<AbstractTableModel> rowsorter = new TableRowSorter<AbstractTableModel>(autokTable);
        jt.setRowSorter(rowsorter);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Keresés:"));
        JTextField filter = (JTextField) searchPanel.add(new JTextField(30));

        JButton removeButton = (JButton) searchPanel.add(new JButton("Kijelölt autók törlése"));
        removeButton.addActionListener(ae -> {
            int input = JOptionPane.showConfirmDialog(null,"Megerősítés","Biztosan tőrli?",JOptionPane.YES_NO_OPTION);
            if(input == 0) {
                int[] rows = jt.getSelectedRows();
                for(int i=0;i<rows.length;i++){
                    autokTable.removeRow(rows[i]-i);
                }
                jt.updateUI();
            }
        });

        this.add(searchPanel,BorderLayout.NORTH);
        TableFilter filterTable = new TableFilter();
        filterTable.Filter(filter,rowsorter);

        jt.setRowHeight(30);
        TableColumnModel columnModel = jt.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(100);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jt.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );

        JScrollPane scrollTable = new JScrollPane(jt);
        this.add(scrollTable,BorderLayout.CENTER);
        scrollTable.setPreferredSize(new Dimension(600, 400));

        JPanel adderPanel = new JPanel(new FlowLayout());
        adderPanel.add(new JLabel("Rendszám:"));
        JTextField newRendszam = (JTextField) adderPanel.add(new JTextField(6));

        adderPanel.add(new JLabel("Típus:"));
        JTextField newTipus = (JTextField) adderPanel.add(new JTextField(20));

        adderPanel.add(new JLabel("Férőhely:"));
        JTextField newFerohely = (JTextField) adderPanel.add(new JTextField(2));

        JButton adderButton = (JButton) adderPanel.add(new JButton("Felvesz"));
        adderButton.addActionListener(ae -> {
            autokTable.addAuto(newRendszam.getText(), newTipus.getText(), Integer.parseInt(newFerohely.getText()));
            jt.updateUI();
        });
        this.add(adderPanel,BorderLayout.SOUTH);
    }

    public AutokTable getAutokTable() {
        return autokTable;
    }
}
