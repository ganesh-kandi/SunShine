package com.project.SunShine.contolller;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.TinyBitSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.SunShine.model.Guest;
import com.project.SunShine.model.Rooms;
import com.project.SunShine.service.GuestService;
import com.project.SunShine.service.RoomsService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class GuestController {

	@Autowired
	GuestService guestService;
	@Autowired
	RoomsService roomsService;
 
	  @GetMapping("/getGuestList") public List<Guest> getallStudents()
	  {
		  List<Guest>
	  getallGuests=guestService.getGuestList();
	  System.out.println("get guest {} "+ getallGuests);
	  
	  return getallGuests; 
	  }
	 

	@GetMapping(value = "/getguest", produces = "application/json")
	public Guest getGuest(@RequestParam Integer id) {
		return guestService.getGuest(id);
	}
	
	@PostMapping("/createguest")
	public String postMethodName(@RequestBody Guest guest) {
		return guestService.saveGuest(guest);

	}
	
	@PostMapping("/CreateRoom")
	public String createRoom() {
		//TODO: process POST request
		//roomsService.saveRoom();
		return "success";
	}
	

}
