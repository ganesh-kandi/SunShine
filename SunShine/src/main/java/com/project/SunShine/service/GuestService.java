package com.project.SunShine.service;

import java.util.List;
import java.util.Random;


import com.project.SunShine.dao.RoomsDao;
import com.project.SunShine.model.Rooms;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SunShine.dao.GuestDao;
import com.project.SunShine.model.Guest;

@Service
public class GuestService {

    @Autowired
    GuestDao guestDao;
    @Autowired
    RoomsDao roomsDao;

    public Guest getGuest(Integer guest) {
        System.out.println("we are in service layer..");
        Guest getGuest = guestDao.findById(guest).get();
        if (getGuest != null) {
            System.out.println("in if" + getGuest);
            return getGuest;
        } else {
            System.out.println("in else. person not found");
            return null;
        }

    }

    public List<Guest> getGuestList() {

        return guestDao.findAll();
    }

    public String saveGuest(Guest guest) {
        Rooms assignRoom;
        if (guest.getRoomnumber().getRoomNumber() != null) {
            assignRoom = roomsDao.findById(guest.getRoomnumber().getRoomNumber())
                    .orElseThrow(()->new RuntimeException("Room not found"));

        }else{
            List<Rooms> availableRooms = roomsDao.findRoomsWithAvailableBeds();
            if(availableRooms.isEmpty()){
                return "Rooms are not available";
            }
            Random random = new Random();
            assignRoom = availableRooms.get(random.nextInt(availableRooms.size()));
        }
        if(!assignRoom.hasAvailableBeds()){
            return "no beds are available in "+ assignRoom.getRoomNumber();
        }
        guest.setRoomnumber(assignRoom);
        guestDao.save(guest);

        assignRoom.setOccupiedBeds(assignRoom.getOccupiedBeds()+1);
        roomsDao.save(assignRoom);
        return "guest "  + guest.getGuest_id() +" assigned the roomNumber : "+assignRoom.getRoomNumber();
    }

    public void saveGuestList(List<Guest> guest) {
        guestDao.saveAll(guest);
    }

    public boolean removeGuest(Integer id) {
        Guest r_guest = guestDao.findById(id).orElseThrow((() -> new RuntimeException("guest not found in DB")));
        guestDao.deleteGuest(r_guest.getGuest_id());
        int roomNumber= r_guest.getRoomnumber().getRoomNumber();
        int count = incrementRoomNumber(roomNumber);
        if(count>0){
            System.out.println("room details are also updated successfully");
            return true;
        }else{
            System.out.println("room details are not updated.");
            return false;
        }
    }
    @Transactional
    public int incrementRoomNumber(int roomNumber){
        return roomsDao.updateRoomCount(roomNumber);
    }
}
