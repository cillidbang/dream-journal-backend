package app.Image;

import app.util.UngenericCRUD;
import jakarta.inject.Inject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ImageControl {

    @Inject
    UngenericCRUD crud;

    /*public ImageEntity getImage(Long journalId) {
        List<ImageEntity> images = crud.findImageByJournalId(journalId);
    }*/


    public Optional<ImageEntity> downloadImageToContainerAndGetEntity(Optional<String> urlOfImage, Long jorunalId) throws IOException {

        if (urlOfImage.isEmpty()) {
            return Optional.empty();
        }

        URL url = new URL(urlOfImage.get());
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

        ImageEntity imageEntity = new ImageEntity(fileName,filePath, jorunalId);

        return Optional.of(imageEntity);
    }
}
