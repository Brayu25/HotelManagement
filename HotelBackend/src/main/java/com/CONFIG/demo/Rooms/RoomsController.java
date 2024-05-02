package com.CONFIG.demo.Rooms;

import com.CONFIG.demo.Shared.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("api/v1/rooms")
@Slf4j
public class RoomsController {
    @Autowired
    RoomsService roomsService;
    @Autowired
    RoomsRepo roomsRepo;
 @PostMapping("/post")
    public EntityResponse create (@RequestBody List<Rooms> rooms){
     EntityResponse res = new EntityResponse<>();
     try {
        List<Rooms> savedRooms = roomsRepo.saveAll(rooms);
         res.setMessage("rooms added successfully");
         res.setStatusCode(HttpStatus.CREATED.value());
         res.setEntity(savedRooms);
     }catch (Exception e){
         log.error("exception {}",e);
     }
     return res;
 }
    @GetMapping("/get")
    public EntityResponse getAll() {
        return roomsService.getAll();
    }
    @PutMapping("/update")
    public EntityResponse update(@RequestBody Rooms  room){
     return  roomsService.update(room);
    }

   @DeleteMapping("/delete")
    public  EntityResponse delete(@RequestParam Long id){
     return roomsService.delete(id);
   }
    @GetMapping("/fetchByPrice")
    public EntityResponse fetchByPrice(@RequestParam int price){
     return roomsService.findByPrice(price);
    }
    @GetMapping("/findById")
    public  EntityResponse findById(@RequestParam Long id){
     return roomsService.findById(id);
    }
}

