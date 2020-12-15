package to;

public class UzytkownikTO {

    private Long id = 0l;
    private String imie = "", nazwisko = "";
    private boolean edited = false;

    public UzytkownikTO() {
    }

    public UzytkownikTO(Long id, String nr, String tytul, boolean edited) {
        this.id = id;
        this.imie = nr;
        this.nazwisko = tytul;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }



}
