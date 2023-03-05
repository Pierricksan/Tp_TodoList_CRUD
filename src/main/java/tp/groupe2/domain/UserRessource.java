package tp.groupe2.domain;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tp.groupe2.user.User;
import tp.groupe2.user.UserDao;

import java.sql.SQLException;
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") int id) throws SQLException {
        User user = userDao.getUserById(id);
        if (user == null){
            return null;
        } else {
            return user;
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User updateById(@PathParam("id") int id,
                           @FormParam("nom") String nom,
                           @FormParam("prenom") String prenom) throws SQLException {
        User userToUpdate = userDao.getUserById(id);
        if(userToUpdate != null){
            if (nom != null){
                userToUpdate.setNom(nom);
            }
            if (prenom != null){
                userToUpdate.setPrenom(prenom);
            }
            userDao.updateUser(userToUpdate);
        }
        return userToUpdate;
    }

    // cela supprimera l'utilisateur et les todos qui lui sont liés
    @DELETE
    @Path("/delete/{id}")
    public User supprimerParId(@PathParam("id") int id) throws SQLException {
        User userDelete = userDao.getUserById(id);
        if (userDelete != null){
            userDao.deleteUser(id);
        } else {
            return null;
        }
        return userDelete;
    }
}
