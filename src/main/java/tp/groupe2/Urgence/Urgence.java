package tp.groupe2.Urgence;

public class Urgence {

    private int id;
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Urgence(int id, String nom){
        this.id = id;
        this.nom = nom;
}

}
