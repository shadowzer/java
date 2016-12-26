package server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "time", nullable = false, length = 50)
    private String time;

    @Column(name = "message", nullable = true, length = 300)
    private String message;

    public Record() {
    }

    public Record(String username, String time, String message) {
        this.username = username;
        this.time = time;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}