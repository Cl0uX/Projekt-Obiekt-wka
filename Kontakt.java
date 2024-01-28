public class Kontakt {

    public String numer;
    public String adres;

    public Kontakt(String numer, String adres) {
        this.numer = numer;
        this.adres = adres;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Kontakt{" +
                "telefon='" + numer + '\'' +
                ", adres='" + adres + '\'' +
                '}';
    }
}
