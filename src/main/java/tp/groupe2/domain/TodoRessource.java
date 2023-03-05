package tp.groupe2.domain;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import tp.groupe2.todo.Todo;
import tp.groupe2.todo.TodoDao;
import tp.groupe2.user.User;
import tp.groupe2.user.UserDao;

import java.sql.SQLException;
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

}