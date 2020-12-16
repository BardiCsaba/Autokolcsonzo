package hf;

import java.io.Serializable;

public class Berles implements Serializable {
    private Auto auto;
    private Berlo berlo;
    private String datum;
    private boolean aktiv;

    public Berles(Auto auto, Berlo berlo, String datum) {
        this.auto = auto;
        this.berlo = berlo;
        this.datum = datum;
        this.aktiv = true;
    }
    public Auto getAuto() { return auto; }
    public Berlo getBerlo() { return berlo; }
    public String getDatum() { return datum; }
    public Boolean getAktiv() { return aktiv; }

    public void setAuto(Auto auto) {  this.auto = auto; }
    public void setBerlo(Berlo berlo) { this.berlo = berlo; }
    public void setDatum(String datum) { this.datum = datum; }
    public void setAktiv(boolean aktiv) { this.aktiv = aktiv; }
}
