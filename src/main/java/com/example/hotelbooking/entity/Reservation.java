package com.example.hotelbooking.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;
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
	
	@ManyToOne()
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "date_start", nullable = false)
	private Timestamp dateStart;
	
	@Column(name = "date_end", nullable = false)
	private Timestamp dateEnd;
	
	@Transient
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	@Transient
	private SimpleDateFormat simpleDateFormatEn = new SimpleDateFormat("yyyy-MM-dd");
	
	public Timestamp getDateEnd() {
		return dateEnd;
	}
	
	public String getDateEndAsString() {
		return simpleDateFormat.format(dateEnd.getTime());
	}
	
	public String getDateStartAsString() {
		return simpleDateFormat.format(dateStart.getTime());
	}
	
	
	public void setDateEnd(String dateEnd) throws ParseException {
		this.dateEnd = new Timestamp(simpleDateFormatEn.parse(dateEnd).getTime());
	}
	
	public Timestamp getDateStart() {
		return dateStart;
	}
	
	
	public void setDateStart(String dateStart) throws ParseException {
		this.dateStart = new Timestamp(simpleDateFormatEn.parse(dateStart).getTime());
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