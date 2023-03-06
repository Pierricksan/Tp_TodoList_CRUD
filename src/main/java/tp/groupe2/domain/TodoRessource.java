package tp.groupe2.domain;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tp.groupe2.todo.Todo;
import tp.groupe2.todo.TodoDao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Path("/todos")
public class TodoRessource {
    // initialisation du TodoDao
    TodoDao todoDao = new TodoDao();

    // Obtenir TOUS les todos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> displayAllUsers(){
        return todoDao.getAllTodo();
    }

    // Obtenir TOUS les todos triés par user
    @GET
    @Path("/orderbyuser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> displayAllUsers2(){
        return todoDao.getAllTodoOrderByUser();
    }

    // Obtenir TOUS les todos triés par urgence
    @GET
    @Path("/orderbyurgence")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> displayAllUsers3(){
        return todoDao.getAllTodoOrderByUrgence();
    }

    // Obtenir Todo par id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) throws SQLException {
        Todo todo = todoDao.getTodoById(id);
        if (todo == null) {
            return Response.status(404).entity("Todo introuvable").build();
        }
        return Response.ok(todo, MediaType.APPLICATION_JSON).header("Message confirmation", "TODO GET").build();
    }

    // Obtenir liste de todo en fonction de l'id utilisateur
    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUserId(@PathParam("id") int id_user) throws SQLException {
        List<Todo> list =  todoDao.getTodoByUserId(id_user);
        if (list.isEmpty()) {
            return Response.status(204).header("Message confirmation", "Liste vide").build();
        }
            return Response.ok(list, MediaType.APPLICATION_JSON).header("Message confirmation", "LIST GET").build();
    }

    // Obtenir liste de todo en fonction de l'id utilisateur
    @GET
    @Path("urgence/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodoByUrgenceId(@PathParam("id") int id_urgence) {
        List<Todo> list =  todoDao.getTodoByUrgenceId(id_urgence);
        if (list.isEmpty()) {
            return Response.status(204).header("Message confirmation", "Aucune urgence correspondante").build();
        }
        return Response.ok(list, MediaType.APPLICATION_JSON).header("Message confirmation", "LIST GET").build();
    }

    // Obtenir liste de todo en fonction de l'id utilisateur
    @GET
    @Path("urgence/{id_urge}/user/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodoByUrgenceAndUserId(@PathParam("id_urge") int id_urgence,
                                                @PathParam("id_user") int id_user) throws SQLException {
        List<Todo> list =  todoDao.getTodoByUrgenceAndUserId(id_urgence, id_user);
        if (list.isEmpty()) {
            return Response.status(204).header("Message confirmation", "Aucun todo avec l'id urgence et l'id user correspondants").build();
        }
        return Response.ok(list, MediaType.APPLICATION_JSON).header("Message confirmation", "LIST TODO GET").build();
    }

    // Ajouter une todo
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response ajouterTodo(@FormParam("titre") String titre,
                                @FormParam("descriptionTodo") String descriptionTodo,
                                @FormParam("dateTodo") Date dateTodo,
                                @FormParam("id_urgence") int id_urgence,
                                @FormParam("id_utilisateur") int id_utilisateur) {
        Todo todo = new Todo(titre, descriptionTodo, dateTodo, id_urgence, id_utilisateur);
        TodoDao.createTodo(todo);
        return Response.status(201).header("Message confirmation", "TODO Bien ajoute").entity(todo).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateById(@PathParam("id") int id,
                           @FormParam("titre") String titre,
                           @FormParam("descriptionTodo") String descriptionTodo,
                           @FormParam("dateTodo") Date dateTodo,
                           @FormParam("id_urgence") int id_urgence,
                           @FormParam("id_utilisateur") int id_utilisateur) throws SQLException {
        Todo todoToUpdate = todoDao.getTodoById(id);
        if(todoToUpdate != null){
            if (titre != null){
                todoToUpdate.setTitre(titre);
            }
            if (descriptionTodo != null){
                todoToUpdate.setDescriptionTodo(descriptionTodo);
            }
            if (dateTodo != null){
                todoToUpdate.setDateTodo(dateTodo);
            }
            if (id_urgence != 0){
                todoToUpdate.setId_urgence(id_urgence);
            }
            if (id_utilisateur != 0){
                todoToUpdate.setId_utilisateur(id_utilisateur);
            }
            todoDao.updateTodo(todoToUpdate);
        }
        return Response.ok().header("Message confirmation", "TODO Bien modifie").entity(todoToUpdate).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response supprimerParId(@PathParam("id") int id) throws SQLException {
        Todo todoDelete = todoDao.getTodoById(id);
        if (todoDelete != null){
            todoDao.deleteTodo(id);
            return Response.ok().header("Message confirmation", "TODO Bien supprime").entity(todoDelete).build();
        } else {
                return Response.status(404).entity("Erreur !!! Le todo n'existe pas !").build();}
        }
}