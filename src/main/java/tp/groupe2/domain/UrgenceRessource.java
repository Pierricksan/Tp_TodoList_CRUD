package tp.groupe2.domain;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tp.groupe2.Urgence.Urgence;
import tp.groupe2.Urgence.UrgenceDao;

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
    public Response addUser(@FormParam("id") int id,
                        @FormParam("nom") String nom) {
        Urgence urgence = new Urgence(id, nom);
        urgenceDao.addUrgence(urgence);
        return Response.status(201).header("Message confirmation", "USER bien ajoute").entity(urgence).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) throws SQLException {
        Urgence urgence = urgenceDao.getUrgenceById(id);
        if (urgence == null){
            return Response.status(404).entity("Urgence introuvable").build();
        } else {
            return Response.ok(urgence, MediaType.APPLICATION_JSON).header("Message confirmation", "Urgence GET").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateById(@PathParam("id") int id,
                           @FormParam("nom") String nom) throws SQLException {
        Urgence urgenceToUpdate = urgenceDao.getUrgenceById(id);
        if(urgenceToUpdate != null){
            if (nom != null){
                urgenceToUpdate.setNom(nom);
            }
            urgenceDao.updateUrgence(urgenceToUpdate);
        }
        return Response.ok().header("Message confirmation", "Urgence Bien modifie").entity(urgenceToUpdate).build();
    }


    @DELETE
    @Path("/delete/{id}")
    public Response supprimerParId(@PathParam("id") int id) throws SQLException {
        Urgence urgenceDelete = urgenceDao.getUrgenceById(id);
        if (urgenceDelete != null){
            urgenceDao.deleteUrgence(id);
            return Response.ok().header("Message confirmation", "URGENCE Bien supprime").entity(urgenceDelete).build();
        } else {
            return Response.status(404).entity("Erreur !!! L'urgence n'existe pas !").build();
        }
    }

}
