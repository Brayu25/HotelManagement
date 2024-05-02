package com.CONFIG.demo.Rooms;

import com.CONFIG.demo.Shared.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoomsService {
    @Autowired
    RoomsRepo roomsRepo;

    public EntityResponse getAll() {
        EntityResponse res = new EntityResponse<>();
        try {
            List<Rooms> rooms = roomsRepo.findAll();
            res.setMessage("successfull");
            res.setStatusCode(HttpStatus.FOUND.value());
            res.setEntity(rooms);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
        return res;
    }

    public EntityResponse update(Rooms room) {
        EntityResponse response = new EntityResponse<>();
        try {
            Optional<Rooms> existingRoom = roomsRepo.findById(room.getId());
            if (existingRoom.isPresent()) {
                Rooms updatedRoom = existingRoom.get();
                updatedRoom.setRoomCondition(room.getRoomCondition());
                updatedRoom.setRoomNumber(room.getRoomNumber());
                updatedRoom.setRoomType(room.getRoomType());
                updatedRoom.setCheckinTime(room.getCheckinTime());
                updatedRoom.setAmenities(room.getAmenities());
                updatedRoom.setCheckoutTime(room.getCheckoutTime());
                updatedRoom.setPrice(room.getPrice());
                updatedRoom.setRating(room.getRating());
                Rooms roomsToUpdate = roomsRepo.save(updatedRoom);
                response.setMessage("Room updated successfully");
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(roomsToUpdate);
            } else {
                response.setMessage("Room not found ");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setEntity(null);
            }

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
        return response;
    }

    public EntityResponse delete(Long id) {
        EntityResponse res = new EntityResponse<>();
        try {
            Optional<Rooms> existingRoom = roomsRepo.findById(id);
            if (existingRoom.isPresent()) {
                roomsRepo.deleteById(id);
                res.setMessage("Room deleted successfully");
                res.setStatusCode(HttpStatus.OK.value());
            } else {
                res.setMessage("Room not found");
                res.setStatusCode(HttpStatus.NO_CONTENT.value());
            }
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
        return res;
    }

    public EntityResponse findByPrice(int price) {
        EntityResponse response = new EntityResponse<>();
        try {
            List<Rooms> existingRooms = roomsRepo.findByPrice(price);
            if (existingRooms.size()>0){
                response.setMessage("Room found successfully");
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(existingRooms);
            }else {
                response.setMessage("No room(s) found with such a price");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
                response.setEntity(null);
            }

        }catch (Exception e){
            log.error("ERROR {}",e);
        }
        return response;
    }

    public EntityResponse findById(Long id) {
        EntityResponse res = new EntityResponse<>();
        try {
            Optional<Rooms> existingRoom =roomsRepo.findById(id);
            if (existingRoom.isPresent()){
                res.setMessage("Room fetched successfully");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(existingRoom);
            }
        }catch (Exception e){
            log.error("Error {}" ,e);
        }
        return res;
    }
}
