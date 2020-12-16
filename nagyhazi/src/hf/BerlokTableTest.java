package hf;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BerlokTableTest {
    Berlo b1, b2;
    String nev, cim, telefonszam;
    int index;
    BerlokTable berlokTable;
    ArrayList<Berlo> berlok;

    @Before
    public void removeSetup(){
        berlokTable = new BerloPanel().getBerlokTable();
        berlok = berlokTable.getBerlok();
    }
    @Test
    public void removeRow() {
        index = berlok.size()-1; //utolsó elem
        b1 = berlok.get(index);
        berlokTable.removeRow(index);
        b2 = berlok.get(berlok.size()-1);
        assertNotEquals("Remove Row",b1,b2);
    }

    @Before
    public void addSetup(){
        berlokTable = new BerloPanel().getBerlokTable();
        berlok = berlokTable.getBerlok();
        nev = "Tóth Ádám";
        cim = "Bp 1234 Random utca 1";
        telefonszam = "+36301234567";
    }
    @Test
    public void addBerlo() {
        index = berlok.size()-1; //utolsó elem
        berlokTable.addBerlo(nev,cim,telefonszam);
        assertEquals("Add Berlo Nev",berlok.get(index+1).getNev(),nev);
        assertEquals("Add Berlo Cim",berlok.get(index+1).getCim(),cim);
        assertEquals("Add Berlo Tel",berlok.get(index+1).getTelefonszam(),telefonszam);
    }

    @Before
    public void searchSetup(){
        berlokTable = new BerloPanel().getBerlokTable();
        berlok = berlokTable.getBerlok();
    }
    @Test
    public void searchBerlo() {
        b1 = berlok.get(0);
        b2 = berlokTable.searchBerlo(b1.getId());
        assertSame("Search Berlo",b1,b2);
    }
}