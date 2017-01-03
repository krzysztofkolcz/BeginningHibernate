package com.kkolcz;

public class SpfStatus {

    boolean spfUstawione;
    boolean domenaUzytkownika;
    boolean skrzynkaZalozona;

    String stronaSprzedazy;
    String spfStatusZlecenia;


    public SpfStatus(boolean spfUstawione, boolean domenaDostepna, boolean skrzynkaZalozona, String stronaSprzedazy) {
        this.spfUstawione = spfUstawione;
        this.domenaUzytkownika = domenaDostepna;
        this.skrzynkaZalozona = skrzynkaZalozona;
        this.stronaSprzedazy = stronaSprzedazy;
    }

    public boolean isSpfUstawione() {
        return spfUstawione;
    }

    public void setSpfUstawione(boolean spfUstawione) {
        this.spfUstawione = spfUstawione;
    }

    public boolean isDomenaUzytkownika() {
        return domenaUzytkownika;
    }

    public void setDomenaUzytkownika(boolean domenaUzytkownika) {
        this.domenaUzytkownika = domenaUzytkownika;
    }

    public boolean isSkrzynkaZalozona() {
        return skrzynkaZalozona;
    }

    public void setSkrzynkaZalozona(boolean skrzynkaZalozona) {
        this.skrzynkaZalozona = skrzynkaZalozona;
    }


    public String getStronaSprzedazy() {
        return stronaSprzedazy;
    }

    public void setStronaSprzedazy(String stronaSprzedazy) {
        this.stronaSprzedazy = stronaSprzedazy;
    }

    public String getSpfStatusZlecenia() {
        return spfStatusZlecenia;
    }

    public void setSpfStatusZlecenia(String spfStatusZlecenia) {
        this.spfStatusZlecenia = spfStatusZlecenia;
    }


    @Override
    public String toString() {
      return "SpfStatus [" +
              ", spfUstawione =" + booleanToString(spfUstawione) + 
              ", domenaUzytkownika =" + booleanToString(domenaUzytkownika) + 
              ", skrzynkaZalozona =" + booleanToString(skrzynkaZalozona)+ 
              ", stronaSprzedazy =" + stronaSprzedazy + 
              ", spfStatusZlecenia=" + spfStatusZlecenia + 
              "]";
    }

    public static String booleanToString(boolean b) {
        return b ? "true" : "false";
    }
}

