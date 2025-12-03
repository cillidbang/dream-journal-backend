package app.journalEntrys;

import app.journalEntrys.entity.JournalEntity;
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
    public Response allJournals() {
        return control.getAllJournals();
    }

    @POST
    public Response createJournalEntry(JournalEntity journalEntity) throws IOException {
        return control.createEntry(journalEntity);
    }

    @PUT
    public Response changeExistingEntry(JournalEntity journalEntity) {
        return control.editEntry(journalEntity);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteJournal(@PathParam("id") String id) {
        return control.deleteEntry(id);
    }
}
