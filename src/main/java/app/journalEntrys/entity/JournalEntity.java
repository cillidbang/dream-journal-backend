package app.journalEntrys.entity;


import jakarta.persistence.*;

@Entity
@Table (name = "journalentrysummary")
public class JournalEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "subtitle")
    public String subtitle;

    @Column(name = "date")
    public String date;

    @Column(name = "content")
    public String content;


    public JournalEntity() {
    }

    public JournalEntity(String title, String subtitle, String date, String content) {
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public JournalEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JournalEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public JournalEntity setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getDate() {
        return date;
    }

    public JournalEntity setDate(String date) {
        this.date = date;
        return this;
    }
}
