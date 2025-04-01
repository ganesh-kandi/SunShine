package com.project.SunShine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.SunShine.model.Rooms;

import java.util.List;

@Repository
public interface RoomsDao extends JpaRepository<Rooms, Integer>{
    @Query("SELECT r FROM Rooms r WHERE r.occupiedBeds < r.totalBeds")
    List<Rooms> findRoomsWithAvailableBeds();
}
