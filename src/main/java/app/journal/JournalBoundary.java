package app.journal;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

@Path("/journal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JournalBoundary {

    @Inject
    JournalControl control;

    @GET
    public Response all() {
        return control.getAllJournals();
    }

    @POST
    public Response create(JournalEntity journalEntity) throws IOException {return control.createEntry(journalEntity);}

    @PUT
    public Response edit(JournalEntity journalEntity) {
        return control.editEntry(journalEntity);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        return control.deleteEntry(id);
    }
}
