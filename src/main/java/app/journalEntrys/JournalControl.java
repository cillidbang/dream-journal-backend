package app.journalEntrys;

import app.UngenericCRUD;
import app.journalEntrys.entity.JournalEntity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@RequestScoped
public class JournalControl {

    @Inject
    UngenericCRUD crud;

    public Response getAllJournals() {
        return Response.ok(crud.getAll(JournalEntity.class)).build();
    }
    public Response createEntry(JournalEntity journalEntity) {
        return Response.ok(crud.create(journalEntity)).build();
    }

    public Response editEntry(JournalEntity newEntity) {
        return Response.ok(crud.edit(newEntity)).build();
    }

    public Response deleteEntry(@PathParam("id") String id) {
        JournalEntity deleted = crud.deleteById(JournalEntity.class, Long.parseLong(id));
        return Response.ok(deleted).build();
    }

}
