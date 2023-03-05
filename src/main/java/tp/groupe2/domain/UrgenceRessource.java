package tp.groupe2.domain;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import tp.groupe2.Urgence.Urgence;
import tp.groupe2.Urgence.UrgenceDao;
import tp.groupe2.user.User;
import tp.groupe2.user.UserDao;

import java.sql.SQLException;
import java.util.List;

@Path("/urgence")
public class UrgenceRessource {

    UrgenceDao urgenceDao = new UrgenceDao();
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Urgence> displayAllUsers(){
        return urgenceDao.getAllUrgence();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Urgence addUser(@FormParam("id") int id,
                        @FormParam("nom") String nom) {
        Urgence urgence = new Urgence(id, nom);
        return urgenceDao.addUrgence(urgence);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Urgence getById(@PathParam("id") int id) throws SQLException {
        Urgence urgence = urgenceDao.getUrgenceById(id);
        if (urgence == null){
            return null;
        } else {
            return urgence;
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Urgence updateById(@PathParam("id") int id,
                           @FormParam("nom") String nom) throws SQLException {
        Urgence urgenceToUpdate = urgenceDao.getUrgenceById(id);
        if(urgenceToUpdate != null){
            if (nom != null){
                urgenceToUpdate.setNom(nom);
            }
            urgenceDao.updateUrgence(urgenceToUpdate);
        }
        return urgenceToUpdate;
    }


    @DELETE
    @Path("/delete/{id}")
    public Urgence supprimerParId(@PathParam("id") int id) throws SQLException {
        Urgence userDelete = urgenceDao.getUrgenceById(id);
        if (userDelete != null){
            urgenceDao.deleteUrgence(id);
        } else {
            return null;
        }
        return userDelete;
    }

}
