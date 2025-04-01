package com.project.SunShine.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="g-id")
	private Integer guest_id;
	@Column
	private String g_name;
	@Column
	private String g_contact;
	@Column
	private String g_address;
	@Column
	private String g_eMail;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="roomnumber")
	private Rooms roomnumber;

	public Integer getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(Integer guest_id) {
		this.guest_id = guest_id;
	}

	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public String getG_contact() {
		return g_contact;
	}

	public void setG_contact(String g_contact) {
		this.g_contact = g_contact;
	}

	public String getG_address() {
		return g_address;
	}

	public void setG_address(String g_address) {
		this.g_address = g_address;
	}

	public String getG_eMail() {
		return g_eMail;
	}

	public void setG_eMail(String g_eMail) {
		this.g_eMail = g_eMail;
	}

	public Rooms getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(Rooms roomnumber) {
		this.roomnumber = roomnumber;
	}
}
