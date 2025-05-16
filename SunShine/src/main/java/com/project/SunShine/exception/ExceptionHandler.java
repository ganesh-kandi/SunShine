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
}
