package app.journalEntrys;


import app.GenericCRUD;
import app.journalEntrys.entity.JournalEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.annotations.Param;

@Path("/journal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JournalBoundary {


    @Inject
    GenericCRUD genericCRUD;

    @GET
    @Path("/getAllJournals")
    public Response getAllJournals() {
        return Response.ok(genericCRUD.getAll()).build();
    }

    @POST
    @Path("/createJournalEntry")
    public Response createJournal(JournalEntity journalEntity) {
        genericCRUD.create(journalEntity);
        return Response.ok(journalEntity).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response createJournal(@PathParam("id") String id) {
        genericCRUD.deleteById(Integer.parseInt(id));
        return Response.ok("JoujournalEntityrnals").build();
    }
}
