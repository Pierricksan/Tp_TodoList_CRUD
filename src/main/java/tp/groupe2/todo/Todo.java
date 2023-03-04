package tp.groupe2.todo;

import tp.groupe2.Urgence.Urgence;
import tp.groupe2.user.User;
import java.util.Date;

public class Todo {

    // attributs
    public int id;
    public String titre, description;
    public Date date;
    public int id_utilisateur;
    public int id_urgence;


    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_urgence() {
        return id_urgence;
    }

    public void setId_urgence(int id_urgence) {
        this.id_urgence = id_urgence;
    }

    // constructeur
    public Todo(int id, String titre, String description, Date date, int id_urgence, int id_utilisateur) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.id_urgence = id_urgence;
        this.id_utilisateur = id_utilisateur;

    }
}
