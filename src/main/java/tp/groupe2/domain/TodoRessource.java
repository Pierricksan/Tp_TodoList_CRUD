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
    TodoDao todoDao = new TodoDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> displayAllUsers(){
        return todoDao.getAllTodo();
    }

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
}