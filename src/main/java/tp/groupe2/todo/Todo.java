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

    // constructeurs
    public Todo() {

    }
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

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getDateTodo() {
        return (java.sql.Date) dateTodo;
    }

    public void setDateTodo(Date dateTodo) {
        this.dateTodo = dateTodo;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescriptionTodo() {
        return descriptionTodo;
    }

    public void setDescriptionTodo(String descriptionTodo) {
        this.descriptionTodo = descriptionTodo;
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
}
