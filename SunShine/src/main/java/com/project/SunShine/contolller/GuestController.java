package com.project.SunShine.contolller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.project.SunShine.exception.NoRoomFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import com.project.SunShine.model.Guest;
import com.project.SunShine.model.Rooms;
import com.project.SunShine.service.GuestService;
import com.project.SunShine.service.RoomsService;


@RestController
public class GuestController {

    @Autowired
    GuestService guestService;


    @GetMapping("/getguestlist")
    public List<Guest> getallStudents() {
        List<Guest>
                getallGuests = guestService.getGuestList();
        System.out.println("get guest {} " + getallGuests);
        return getallGuests;
    }


    @GetMapping(value = "/getguest", produces = "application/json")
    public Guest getGuest(@RequestParam Integer id, @PageableDefault(size= 20, sort= "name") Pageable pageable) {

        return guestService.getGuest(id);
    }

    @PostMapping("/createguest")
    public String createGuest(@RequestBody Guest guest) {
        return guestService.saveGuest(guest);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/deleteguest/{id}")
    public String deleteGuestInfo(@PathVariable("id") Integer id) {
        boolean flag = guestService.removeGuest(id);
        if (flag) {
            return "guest deleted successfully";
        } else {
            return "guest deletion failed";
        }
    }


    @PostMapping("/createguestlist")
    public String saveGuestList(@RequestBody  List<Guest> guestList){
        return guestService.saveGuestList(guestList);
    }

    @PatchMapping("updatefields/{id}")
    public Guest updateGuest(@PathVariable int id, @RequestBody Map<String, Object> fields){
        return guestService.updateGuest(id, fields);
    }

    @PatchMapping("/updateroom/{id}")
    public String updateRooms(@PathVariable int id, @RequestBody Map<String, Object> roomDetails){
        return guestService.updateRooms(id,roomDetails);
    }

    @GetMapping("/getguestcount")
    public String getGuestCount(){
        int count = guestService.getGuestsCount();
        return "The total number of guests are "+ count;
    }

    @GetMapping("/getavailableroomscount")
    public String getAvailableRoomCount(){
        int count = guestService.findRoomsWithAvailableBedsCount();
        return "The total number of available rooms are "+ count;
    }

    @GetMapping("/getroomtype/{type}")
    public List<Rooms> getRoomByType(@PathVariable int type){
        Optional<List<Rooms>> rooms = guestService.getRoomsByType(type);
        if(rooms.isEmpty()){
            throw new NoRoomFoundException("no rooms found");
        }
        return rooms.get();
    }

    @GetMapping("/getroomtypecount/{type}")
    public String getRoomByTypeCount(@PathVariable int type){
        return guestService.getRoomsByTypeCount(type);
    }



}
