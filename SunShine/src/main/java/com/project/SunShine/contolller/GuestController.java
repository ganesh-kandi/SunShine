package com.project.SunShine.contolller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.SunShine.model.Guest;
import com.project.SunShine.model.Rooms;
import com.project.SunShine.service.GuestService;
import com.project.SunShine.service.RoomsService;


@RestController
public class GuestController {

	@Autowired
	GuestService guestService;

 
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

	@GetMapping("/deleteguest/{id}")
	public String deleteGuestInfo(@PathVariable("id") Integer id){
		  boolean flag = guestService.removeGuest(id);
		  if(flag){
			  return "guest deleted successfully";
		  }else{
			  return "guest deletion failed";
		  }
	}
	




}
