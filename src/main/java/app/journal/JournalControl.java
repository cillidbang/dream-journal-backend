package app.journal;

import app.util.UngenericCRUD;
import app.Image.ImageEntity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.util.Optional;

@RequestScoped
public class JournalControl {

    @Inject
    UngenericCRUD crud;


    public Response getAllJournals() {
        return Response.ok(crud.getAll(JournalEntity.class)).build();
    }

    public Response createEntry(JournalEntity journalEntity) throws IOException {
        crud.create(journalEntity);

        /*Optional<String> string = "https://images.pexels.com/photos/34176344/pexels-photo-34176344.jpeg?cs=srgb&dl=pexels-abdullahi-santuraki-615175419-34176344.jpg&fm=jpg\"".describeConstable();
        Optional<ImageEntity> entityOfAlreadySavedImage = downloadImageToContainerAndGetEntity(string, journalEntity.id);*/

        /*if (entityOfAlreadySavedImage.isPresent()) {
            ImageEntity savedEntity = crud.create(entityOfAlreadySavedImage.get());
            return Response.ok(savedEntity).build();
        }*/
        return Response.status(500, "image saving failed").build();
    }

    public Response editEntry(JournalEntity newEntity) {
        return Response.ok(crud.edit(newEntity)).build();
    }

    public Response deleteEntry(String id) {
        return Response.ok(crud.deleteById(JournalEntity.class, Long.parseLong(id))).build();
    }


}
