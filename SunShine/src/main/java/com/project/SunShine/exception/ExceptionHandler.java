package com.project.SunShine.exception;
import com.project.SunShine.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value= NoRoomFoundException.class)
    public ResponseEntity<ApiError> noRoomsFound(){
        ApiError apiError= new ApiError("40000","internal Server error", "no rooms found with given type","no rooms found with given type");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value= NoGuestFoundException.class)
    public ResponseEntity<ApiError> noGuestFound(){
        ApiError apiError= new ApiError("40001","Internal Server error", "no guests found with given data","no guests found with given data");
        return  new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value=GuestorRoomNotFound.class)
    public ResponseEntity<ApiError> noroomorGuestFound(String description){
        ApiError apiError= new ApiError("40002", "Internal Server error", description,description);
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
