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
        return Response.ok(crud.getAll()).build();
    }

    public Response createEntry(JournalEntity journalEntity) {
        crud.create(journalEntity);
        return Response.ok(journalEntity).build();
    }

    public Response overwriteById(JournalEntity newEntity) {
        crud.edit(newEntity, newEntity.id);
        return Response.ok().build();
    }

    public Response deleteEntry(@PathParam("id") String id) {
        JournalEntity entity = crud.deleteById(Integer.parseInt(id));
        return Response.ok(entity).build();
    }

}
