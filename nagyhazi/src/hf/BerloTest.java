package hf;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class BerloTest {
    Berlo b;
    String nev, cim, telefonszam;
    ArrayList<String> azonositok;
    Random random;
    String id;

    @Before
    public void stringSetup(){
        nev = "Kovács Jancsi";
        telefonszam = "+36301234567";
        cim = "Bp 1235 Hajnalka utca 8";
        b = new Berlo(nev,cim,telefonszam);
    }
    @Test
    public void testToString() {
        assertEquals("Berlok To String",nev + "/" + telefonszam, b.toString());
    }

    @Before
    public void idSetup() {
        b = new Berlo("a","b","c");
        azonositok = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("azonositok.txt"));
            azonositok = (ArrayList<String>)ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Test
    public void createID() {
        for(int i = 0; i < 100; i++){
            id = b.createID(azonositok);
            for (String azonosito: azonositok) {
                assertNotEquals(azonosito, b.getId());
            }
            azonositok.add(id);
        }
    }
}