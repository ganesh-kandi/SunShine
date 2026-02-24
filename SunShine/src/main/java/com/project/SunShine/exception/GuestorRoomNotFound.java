package com.project.SunShine.exception;

import java.util.HashMap;
import java.util.Hashtable;

public class GuestorRoomNotFound extends RuntimeException{
    public GuestorRoomNotFound(String message){
        super(message);
    }
}
