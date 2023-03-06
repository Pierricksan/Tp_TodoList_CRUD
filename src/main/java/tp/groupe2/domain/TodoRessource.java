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
    public Todo getById(@PathParam("id") int id) throws SQLException {
        return todoDao.getTodoById(id);
    }

    // Obtenir liste de todo en fonction de l'id utilisateur
    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getByUserId(@PathParam("id") int id_user) throws SQLException {
        return todoDao.getTodoByUserId(id_user);
    }

    // Obtenir liste de todo en fonction de l'id utilisateur
    @GET
    @Path("urgence/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getTodoByUrgenceId(@PathParam("id") int id_urgence) throws SQLException {
        return todoDao.getTodoByUrgenceId(id_urgence);
    }

    // Obtenir liste de todo en fonction de l'id utilisateur
    @GET
    @Path("urgence/{id_urge}/user/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getTodoByUrgenceAndUserId(@PathParam("id_urge") int id_urgence,
                                                @PathParam("id_user") int id_user) throws SQLException {
        return todoDao.getTodoByUrgenceAndUserId(id_urgence, id_user);
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
        return Response.status(201).entity(todo).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Todo updateById(@PathParam("id") int id,
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
        return todoToUpdate;
    }

    @DELETE
    @Path("/delete/{id}")
    public Todo supprimerParId(@PathParam("id") int id) throws SQLException {
        Todo todoDelete = todoDao.getTodoById(id);
        if (todoDelete != null){
            todoDao.deleteTodo(id);
        } else {
            return null;
        }
        return todoDelete;
    }
}