package hf;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BerlesPanel extends JPanel {
    private BerlesekTable berlesekTable;

    public BerlesPanel (AutokTable autokTable, BerlokTable berlokTable) {

        try {
            berlesekTable = new BerlesekTable();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("berlesek.txt"));
            berlesekTable.berlesek = (ArrayList<Berles>)ois.readObject();
            ois.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JTable jt = new JTable(berlesekTable);
        TableRowSorter<AbstractTableModel> rowsorter = new TableRowSorter<>(berlesekTable);
        jt.setRowSorter(rowsorter);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Keresés:"));
        JTextField filter = (JTextField) searchPanel.add(new JTextField(30));

        JButton removeButton = (JButton) searchPanel.add(new JButton("Kijelölt bérlések törlése"));
        removeButton.addActionListener(ae -> {
            int input = JOptionPane.showConfirmDialog(null,"Megerősítés","Biztosan tőrli?",JOptionPane.YES_NO_OPTION);
            if(input == 0) {
                int[] rows = jt.getSelectedRows();
                for(int i=0;i<rows.length;i++){
                    berlesekTable.removeRow(rows[i]-i);
                }
                jt.updateUI();
            }
        });

        this.add(searchPanel,BorderLayout.NORTH);
        TableFilter filterTable = new TableFilter();
        filterTable.Filter(filter,rowsorter);

        jt.setRowHeight(30);
        TableColumnModel columnModel = jt.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );

        JScrollPane scrollTable = new JScrollPane(jt);
        this.add(scrollTable,BorderLayout.CENTER);
        scrollTable.setPreferredSize(new Dimension(800, 400));

        JPanel adderPanel = new JPanel(new FlowLayout());
        adderPanel.add(new JLabel("Autó rendszám:"));
        JTextField newAuto = (JTextField) adderPanel.add(new JTextField(6));

        adderPanel.add(new JLabel("Bérlő azonosító:"));
        JTextField newBerlo = (JTextField)adderPanel.add(new JTextField(4));

        JButton adderButton = (JButton) adderPanel.add(new JButton("Felvesz"));
        adderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Auto auto = autokTable.searchAuto(newAuto.getText());
                Berlo berlo = berlokTable.searchBerlo(newBerlo.getText());
                if(auto != null && berlo != null)
                    berlesekTable.addBerles(auto,berlo);
                jt.updateUI();
            }
        });
        this.add(adderPanel,BorderLayout.SOUTH);
    }

    public BerlesekTable getBerlesekTable() { return berlesekTable; }
}
