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
// Obtenir Todo par id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo getById(@PathParam("id") int id) throws SQLException {
        Todo todo = todoDao.getTodoById(id);
        if (todo == null){
            return null;
        } else {
            return todo;
        }
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
        return Response.status(201).entity("Todo created successfully.").build();
    }
}