package hf;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BerlesekTableTest {
    Berles b1, b2;
    Auto a;
    Berlo b;
    int index;
    BerlesekTable berlesekTable;
    ArrayList<Berles> berlesek;

    @Before
    public void removeSetup() {
        berlesekTable = new BerlesPanel(new AutoPanel().getAutokTable(), new BerloPanel().getBerlokTable()).getBerlesekTable();
        berlesek = berlesekTable.getBerlesek();
    }

    @Test
    public void removeRow() {
        index = berlesek.size() - 1; //utolsó elem
        b1 = berlesek.get(index);
        berlesekTable.removeRow(index);
        b2 = berlesek.get(berlesek.size() - 1);
        assertNotEquals("Remove Row", b1, b2);
    }

    @Before
    public void addSetup() {
        berlesekTable = new BerlesPanel(new AutoPanel().getAutokTable(), new BerloPanel().getBerlokTable()).getBerlesekTable();
        berlesek = berlesekTable.getBerlesek();
        a = new Auto("BHG-432", "BMW X4", 5);
        b = new Berlo("Tóth János", "Bp 1234 Random utca 1", "+36301234567");
    }

    @Test
    public void addBerles() {
        index = berlesek.size() - 1; //utolsó elem
        berlesekTable.addBerles(a, b);
        assertEquals("Add Berles Auto", berlesek.get(index + 1).getAuto(), a);
        assertEquals("Add Berles Berlo", berlesek.get(index + 1).getBerlo(), b);
    }
}