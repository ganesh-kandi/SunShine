package com.project.SunShine.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.SunShine.model.Rooms;

import java.util.List;

@Repository
public interface RoomsDao extends JpaRepository<Rooms, Integer>{
    @Query("SELECT r FROM Rooms r WHERE r.occupiedBeds < r.totalBeds")
    List<Rooms> findRoomsWithAvailableBeds();

    @Modifying
    @Transactional
    @Query("UPDATE Rooms r SET r.occupiedBeds = r.occupiedBeds - 1 WHERE r.roomNumber = :roomnumber")
    int updateRoomCount(@Param("roomnumber")int roomNumber);
}
