package com.project.SunShine.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table
public class Rooms {

	@Id
	@Column(name="roomnumber")
	private Integer roomNumber;
	@Column
	private Integer floor;
	@Column
	private String type;
	@Column
	private boolean AC;
	@Column
	private Integer price;
	@Column(nullable = false)
	private int totalBeds;  // Total number of beds in the room (3 or 4)

	@Column(nullable = false)
	private int occupiedBeds = 0;  // Track currently occupied beds

	// Check if the room has available beds
	public boolean hasAvailableBeds() {
		return occupiedBeds < totalBeds;
	}
	public Rooms() {
        // Default constructor
    }

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAC() {
		return AC;
	}

	public void setAC(boolean AC) {
		this.AC = AC;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public int getTotalBeds() {
		return totalBeds;
	}

	public void setTotalBeds(int totalBeds) {
		this.totalBeds = totalBeds;
	}

	public int getOccupiedBeds() {
		return occupiedBeds;
	}

	public void setOccupiedBeds(int occupiedBeds) {
		this.occupiedBeds = occupiedBeds;
	}

	@Override
	public String toString() {
		return "Rooms{" +
				"roomNumber=" + roomNumber +
				", floor=" + floor +
				", type='" + type + '\'' +
				", AC=" + AC +
				", price=" + price +
				", totalBeds=" + totalBeds +
				", occupiedBeds=" + occupiedBeds +
				'}';
	}
}
