package com.example.hotelbooking.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "date_start", nullable = false)
    private Timestamp dateStart;

    @Column(name = "date_end", nullable = false)
    private Timestamp dateEnd;

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public String getDateEndAsString() {
        return new SimpleDateFormat("dd-MM-yyyy").format(dateEnd.getTime());
    }

    public String getDateStartAsString() {
        return new SimpleDateFormat("dd-MM-yyyy").format(dateStart.getTime());
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}