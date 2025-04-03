package com.project.SunShine.service;

import com.project.SunShine.dao.GuestDao;
import com.project.SunShine.dao.RoomsDao;
import com.project.SunShine.model.Guest;
import com.project.SunShine.model.Rooms;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class GuestService {

    @Autowired
    GuestDao guestDao;
    @Autowired
    RoomsDao roomsDao;

    public static final String ROOM_NUMBER = "roomnumber";

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
        this.updateRoom(guest, assignRoom);
        return "guest "  + guest.getGuest_id() +" assigned the roomNumber : "+assignRoom.getRoomNumber();
    }

    private void updateRoom(Guest guest, Rooms room){
        guest.setRoomnumber(room);
        guestDao.save(guest);

        room.setOccupiedBeds(room.getOccupiedBeds()+1);
        roomsDao.save(room);
    }

    public String saveGuestList(List<Guest> guestList) {
        if(guestList.isEmpty()){
            return "guest list is empty.";
        }else {
            for(Guest guest: guestList){
                System.out.println(this.saveGuest(guest));
            }
            return "success";
        }

    }

    public boolean removeGuest(Integer id) {
        Guest r_guest = guestDao.findById(id).orElseThrow((() -> new RuntimeException("guest not found in DB")));
        guestDao.deleteGuest(r_guest.getGuest_id());
        int roomNumber= r_guest.getRoomnumber().getRoomNumber();
        int count = decrementRoomNumber(roomNumber);
        if(count>0){
            System.out.println("room details are also updated successfully");
            return true;
        }else{
            System.out.println("room details are not updated.");
            return false;
        }
    }
    @Transactional
    public int decrementRoomNumber(int roomNumber){
        return roomsDao.updateRoomCount(roomNumber);
    }

    public Guest  updateGuest(int id, Map<String, Object> fields) {
        Optional<Guest> u_guest = guestDao.findById(id);
        if(u_guest.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Guest.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, u_guest.get(), value);
            });
            return guestDao.save(u_guest.get());
        }
        return null;
    }

    public String updateRooms(int id, Map<String, Object> roomDetails) {
        Optional<Guest> guest = guestDao.findById(id);

        if(guest.isEmpty() || roomDetails.get(ROOM_NUMBER)== null){
            return "guest or room not found.. ";
        }else{
            int oldRoomNumber = guest.get().getRoomnumber().getRoomNumber();
            Map<String,Object> objectMap = (Map<String, Object>) roomDetails.get(ROOM_NUMBER);

            int newRoomNumber = (Integer)objectMap.get("roomNumber");
            this.decrementRoomNumber(oldRoomNumber);
            Optional<Rooms> newRoom = roomsDao.findById(newRoomNumber);
            //room number should be updated
            //and also occupied beds are also update
            /*roomDetails.forEach((key, value)->{
                    Field field = ReflectionUtils.findField(Guest.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, guest.get(),value);
            });*/
            if(newRoom.isPresent()) {
                guest.get().setRoomnumber(newRoom.get());
                //guestDao.save(guest.get());
                this.updateRoom(guest.get(), newRoom.get());
            }
            return "rooms details are updated for guest : "+ guest.get().getGuest_id() + " and new roomNumber is : "+newRoom.get().getRoomNumber();
        }

    }

    @Transactional
    public int incrementRoomNumber(int roomNumber){
        return roomsDao.updateRoomCount(roomNumber);
    }

}
