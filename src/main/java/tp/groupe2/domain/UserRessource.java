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
    public Response addUser(@FormParam("id") int id,
                        @FormParam("nom") String nom,
                        @FormParam("prenom") String prenom) {
        User user = new User(id, nom, prenom);
        userDao.addUser(user);
        return Response.status(201).header("Message confirmation", "USER bien ajoute").entity(user).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) throws SQLException {
        User user = userDao.getUserById(id);
        if (user == null) {
            return Response.status(404).entity("Todo introuvable").build();
        }
        return Response.ok(user, MediaType.APPLICATION_JSON).header("Message confirmation", "USER GET").build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateById(@PathParam("id") int id,
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
        return Response.ok().header("Message confirmation", "USER Bien modifie").entity(userToUpdate).build();
    }

    // cela supprimera l'utilisateur et les todos qui lui sont liés
    @DELETE
    @Path("/delete/{id}")
    public Response supprimerParId(@PathParam("id") int id) throws SQLException {
        User userDelete = userDao.getUserById(id);
        if (userDelete != null){
            userDao.deleteUser(id);
            return Response.ok().header("Message confirmation", "USER Bien supprime").entity(userDelete).build();
        } else {
            return Response.status(404).entity("Erreur !!! Le user n'existe pas !").build();
        }
    }
}
