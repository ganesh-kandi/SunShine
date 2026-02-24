package com.project.SunShine.service;

import com.project.SunShine.dao.GuestDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @InjectMocks
    GuestService guestService;

    @Mock
    GuestDao guestDao;

    @Test
    void myFirstTest(){
        System.out.println("my first test");
    }

    @Test
    void getGuestTest(){
        Integer guestId= 2;
        guestService.getGuest(guestId);

    }

}