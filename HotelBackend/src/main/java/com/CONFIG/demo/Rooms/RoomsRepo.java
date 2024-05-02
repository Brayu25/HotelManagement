package com.CONFIG.demo.Rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomsRepo extends JpaRepository<Rooms,Long> {
//    Optional<Rooms> findByRoomNumber(String roomNumber);

    List<Rooms> findByPrice(int price);
}
