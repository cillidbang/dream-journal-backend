package app.journalEntrys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class Image extends BaseEntity {

    @Column(name = "file_name")
    String fileName;

    @Column(name = "file_path")
    String filePath;

    @Column(name = "journal_id")
    Long journalId;

    public Image() {}

    public Image(String fileName, String filePath, Long journalId) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.journalId = journalId;
    }
}
