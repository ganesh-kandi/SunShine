package com.project.SunShine.dao;

import com.project.SunShine.model.Rooms;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.SunShine.model.Guest;

@Repository
public interface GuestDao extends JpaRepository<Guest, Integer>{

    @Modifying
    @Transactional
    @Query("DELETE FROM Guest g WHERE g.guest_id = :guestid")
    void deleteGuest(@Param("guestid") int guestId);

    @Query("SELECT COUNT(*) FROM Guest")
    int getGuestsCount();
}
