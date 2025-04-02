package com.project.SunShine.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SunShine.dao.RoomsDao;
import com.project.SunShine.model.Rooms;

import java.util.List;

@Service
public class RoomsService {
	@Autowired
	RoomsDao roomsDao;
	
	public void saveRoom(Rooms room) {
		roomsDao.save(room);
	}
	
	public Rooms getRoom(Integer roomId) {
		System.out.println("we are in service layer..");
	Rooms getRoom= roomsDao.findById(roomId).get();
	return getRoom;
	
}

    public List<Rooms> getAllAvailableRooms() {
		List<Rooms> availableRooms = roomsDao.findRoomsWithAvailableBeds();
		if(availableRooms.isEmpty()){
			System.out.println("Rooms are not available");
		}else{
			return availableRooms;
		}
		return null;
    }
}
