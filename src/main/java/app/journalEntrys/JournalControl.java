package app.journalEntrys;

import app.UngenericCRUD;
import app.journalEntrys.entity.Image;
import app.journalEntrys.entity.JournalEntity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

        Optional<String> string = "https://images.pexels.com/photos/34176344/pexels-photo-34176344.jpeg?cs=srgb&dl=pexels-abdullahi-santuraki-615175419-34176344.jpg&fm=jpg\"".describeConstable();
        Optional<Image> entityOfAlreadySavedImage = downloadImageToContainerAndGetEntity(string, journalEntity.id);

        if (entityOfAlreadySavedImage.isPresent()) {
            Image savedEntity = crud.create(entityOfAlreadySavedImage.get());
            return Response.ok(savedEntity).build();
        }
        return Response.status(500, "image saving failed").build();
    }

    public Response editEntry(JournalEntity newEntity) {
        return Response.ok(crud.edit(newEntity)).build();
    }

    public Response deleteEntry(@PathParam("id") String id) {
        JournalEntity deleted = crud.deleteById(JournalEntity.class, Long.parseLong(id));
        return Response.ok(deleted).build();
    }

    public Optional<Image> downloadImageToContainerAndGetEntity(Optional<String> urlOfImage, Long jorunalId) throws IOException {
        String urlString;
        if (urlOfImage.isEmpty()) {
            return Optional.empty();
        }
        else {
            urlString = urlOfImage.get();
        }

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", "my-agent");
        httpURLConnection.connect();

        ReadableByteChannel readableByteChannel = Channels.newChannel(httpURLConnection.getInputStream());

        String fileType = "jpg";
        String fileName = "image_%s.%s".formatted(
                LocalDateTime.now().toLocalTime().getNano(),
                fileType);

        String filePath = "/var/lib/postgresql/imagefiles/%s".formatted(fileName);

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)){
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }

        Image image = new Image(fileName,filePath, jorunalId);

        return Optional.of(image);
    }

}
