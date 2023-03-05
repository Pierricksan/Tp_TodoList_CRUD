package tp.groupe2.todo;

import java.util.Date;

public class Todo {

    // attributs
    public int id;
    public String titre;
    public String descriptionTodo;
    public Date dateTodo;
    public int id_utilisateur;
    public int id_urgence;

    public Todo(String titre, String descriptionTodo, Date dateTodo, int id_urgence, int id_utilisateur) {
        this.titre = titre;
        this.descriptionTodo = descriptionTodo;
        this.dateTodo = dateTodo;
        this.id_urgence = id_urgence;
        this.id_utilisateur = id_utilisateur;
    }
    public Todo(int id, String titre, String descriptionTodo, Date dateTodo, int id_urgence, int id_utilisateur) {
        this.id = id;
        this.titre = titre;
        this.descriptionTodo = descriptionTodo;
        this.dateTodo = dateTodo;
        this.id_urgence = id_urgence;
        this.id_utilisateur = id_utilisateur;

    }

    public java.sql.Date getDate() {
        return (java.sql.Date) dateTodo;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return descriptionTodo;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getId_urgence() {
        return id_urgence;
    }

}
