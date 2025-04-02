package com.project.SunShine.contolller;

import com.project.SunShine.model.Rooms;
import com.project.SunShine.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomsController {

    @Autowired
    RoomsService roomsService;

    @GetMapping("/getavailablerooms")
    public List<Rooms> getAvailableRooms(){
        return roomsService.getAllAvailableRooms();
    }

    @PostMapping("/CreateRoom")
    public String createRoom() {
        //TODO: process POST request
        //roomsService.saveRoom();
        return "success";
    }

}
