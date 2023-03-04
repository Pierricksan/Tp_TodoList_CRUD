package tp.groupe2.domain;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import tp.groupe2.user.User;
import tp.groupe2.user.UserDao;

import java.util.List;
@Path("/user")
public class UserRessource {
    UserDao userDao = new UserDao();
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> displayAllUsers(){
        return userDao.getAllUsers();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(@FormParam("id") int id,
                        @FormParam("nom") String nom,
                        @FormParam("prenom") String prenom) {
        User user = new User(id, nom, prenom);
        return userDao.addUser(user);
    }

}
