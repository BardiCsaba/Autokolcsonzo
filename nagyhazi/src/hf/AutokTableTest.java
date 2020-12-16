package hf;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AutokTableTest {
    Auto a1, a2;
    String rendszam, tipus;
    int ferohely, index;
    AutokTable autokTable;
    ArrayList<Auto> autok;

    @Before
    public void removeSetup(){
        autokTable = new AutoPanel().getAutokTable();
        autok = autokTable.getAutok();
    }
    @Test
    public void removeRow() {
        index = autok.size()-1; //utolsó elem
        a1 = autok.get(index);
        autokTable.removeRow(index);
        a2 = autok.get(autok.size()-1);
        assertNotEquals("Remove Row",a1,a2);
    }

    @Before
    public void addSetup(){
        autokTable = new AutoPanel().getAutokTable();
        autok = autokTable.getAutok();
        rendszam = "HVG-654";
        tipus = "BMW X5";
        ferohely = 5;
    }
    @Test
    public void addAuto() {
        index = autok.size()-1; //utolsó elem
        autokTable.addAuto(rendszam,tipus,ferohely);
        assertEquals("Add Auto Rendszam",autok.get(index+1).getRendszam(),rendszam);
        assertEquals("Add Auto Tipus",autok.get(index+1).getTipus(),tipus);
        assertEquals("Add Auto Ferohely",autok.get(index+1).getFerohely(),ferohely);

    }

    @Before
    public void searchSetup(){
        autokTable = new AutoPanel().getAutokTable();
        autok = autokTable.getAutok();
    }
    @Test
    public void searchAuto() {
        a1 = autok.get(0);
        a2 = autokTable.searchAuto(a1.getRendszam());
        assertSame("Search Auto",a1,a2);
    }
}