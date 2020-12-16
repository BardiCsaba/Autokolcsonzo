package hf;

import java.io.Serializable;

public class Auto implements Serializable {

    private String rendszam;
    private String tipus;
    private int ferohely;

    public Auto(String rendszam, String tipus, int ferohely) {
        this.rendszam = rendszam;
        this.tipus = tipus;
        this.ferohely = ferohely;
    }
    public String getRendszam() { return rendszam; }
    public String getTipus() { return tipus; }
    public int getFerohely() { return ferohely; }

    public void setRendszam(String rendszam) { this.rendszam = rendszam; }
    public void setTipus(String tipus) { this.tipus = tipus; }
    public void setFerohely(Integer ferohely) { this.ferohely = ferohely; }

    public String toString() {
        return rendszam + " - " + tipus;
    }
}
