package hf;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Berlo implements Serializable {
    private String id;
    private String nev;
    private String cim;
    private String telefonszam;
    static ArrayList<String> azonositok;

    public Berlo(String nev, String cim, String telefonszam) {
        this.nev = nev;
        this.cim = cim;
        this.telefonszam = telefonszam;

        azonositok = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("azonositok.txt"));
            azonositok = (ArrayList<String>)ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String id = createID(azonositok);
        azonositok.add(id);
        this.id = id;

    }
    public String getNev() { return nev; }
    public String getCim() { return cim; }
    public String getTelefonszam() { return telefonszam; }
    public String getId() { return id; }

    public void setNev(String nev) { this.nev = nev; }
    public void setCim(String cim) { this.cim = cim; }
    public void setTelefonszam(String telefonszam) { this.telefonszam = telefonszam; }

    public String toString() {
        return nev + "/" + telefonszam;
    }

    public String createID(ArrayList<String> azonositok) {

        String string = String.format("%04d", new Random().nextInt(10000));
        if(azonositok != null)
            for (String azonosito: azonositok)
                if(string.matches(azonosito))
                    createID(azonositok);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("azonositok.txt"));
                oos.writeObject(azonositok);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return string;
    }
}
