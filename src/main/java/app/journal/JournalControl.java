package app.journal;

import app.util.UngenericCRUD;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@RequestScoped
public class JournalControl {

    @Inject
    UngenericCRUD crud;

    public Response getAllJournals() {
        return Response.ok(crud.getAll(JournalEntity.class)).build();
    }

    public Response createEntry(JournalEntity journalEntity) {
        Optional<JournalEntity> entity = crud.create(journalEntity);

        if (entity.isEmpty()) {
            return Response.status(500, "failed to persist journal").build();
        }
        return Response.ok().build();
    }

    public Response editEntry(JournalEntity newEntity) {
        return Response.ok(crud.edit(newEntity)).build();
    }

    public Response deleteEntry(String id) {
        return Response.ok(crud.deleteById(JournalEntity.class, Long.parseLong(id))).build();
    }


}
