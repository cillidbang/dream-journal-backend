package app.journalEntrys;

import app.GenericCRUD;
import app.journalEntrys.entity.JournalEntity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@RequestScoped
public class JournalControl {

    @Inject
    GenericCRUD crud;

    public Response getAllJournals() {
        return Response.ok(crud.getAll()).build();
    }

    public Response createEntry(JournalEntity journalEntity) {
        crud.create(journalEntity);
        return Response.ok(journalEntity).build();
    }

    public Response deleteEntry(@PathParam("id") String id) {
        JournalEntity entity = crud.deleteById(Integer.parseInt(id));
        return Response.ok(entity).build();
    }

}
