package hf;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BerloPanel extends JPanel {
    private BerlokTable berlokTable;

    public BerloPanel () {


        try {
            berlokTable = new BerlokTable();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("berlok.txt"));
            berlokTable.berlok = (ArrayList<Berlo>)ois.readObject();
            ois.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JTable jt = new JTable(berlokTable);
        TableRowSorter<AbstractTableModel> rowsorter = new TableRowSorter<AbstractTableModel>(berlokTable);
        jt.setRowSorter(rowsorter);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Keresés:"));
        JTextField filter = (JTextField) searchPanel.add(new JTextField(30));

        JButton removeButton = (JButton) searchPanel.add(new JButton("Kijelölt bérlők törlése"));
        removeButton.addActionListener(ae -> {
            int input = JOptionPane.showConfirmDialog(null,"Megerősítés","Biztosan tőrli?",JOptionPane.YES_NO_OPTION);
            if(input == 0) {
                int[] rows = jt.getSelectedRows();
                for(int i=0;i<rows.length;i++){
                    berlokTable.removeRow(rows[i]-i);
                }
                jt.updateUI();
            }
        });

        this.add(searchPanel,BorderLayout.NORTH);
        TableFilter filterTable = new TableFilter();
        filterTable.Filter(filter,rowsorter);
        
        jt.setRowHeight(30);
        TableColumnModel columnModel = jt.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(100);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jt.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );

        JScrollPane scrollTable = new JScrollPane(jt);
        this.add(scrollTable,BorderLayout.CENTER);
        scrollTable.setPreferredSize(new Dimension(800, 400));

        JPanel adderPanel = new JPanel(new FlowLayout());
        adderPanel.add(new JLabel("Név:"));
        JTextField newNev = (JTextField) adderPanel.add(new JTextField(15));

        adderPanel.add(new JLabel("Lakcím:"));
        JTextField newCim = (JTextField) adderPanel.add(new JTextField(20));

        adderPanel.add(new JLabel("Telefonszám:"));
        JTextField newTelefonszam = (JTextField) adderPanel.add(new JTextField(10));

        JButton adderButton = (JButton) adderPanel.add(new JButton("Felvesz"));
        adderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                berlokTable.addBerlo(newNev.getText(), newCim.getText(), newTelefonszam.getText());
                jt.updateUI();
            }
        });
        this.add(adderPanel,BorderLayout.SOUTH);
    }

    public BerlokTable getBerlokTable() {
        return berlokTable;
    }
}