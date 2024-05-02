package com.CONFIG.demo.Booking;

import com.CONFIG.demo.Shared.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/booking")
@RestController
@CrossOrigin
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping("/add")
    public EntityResponse create(@RequestBody Booking booking){
        return bookingService.create(booking);
    }
    @PostMapping("/addGuest")
    public EntityResponse add(@RequestBody Guest guest){
        return bookingService.add(guest);
    }
    @GetMapping("/getAll")
    public EntityResponse getAll(){
        return bookingService.getAll();
    }

    @DeleteMapping("/delete")
    public EntityResponse deleteById(@RequestParam Long id){
        return bookingService.deleteById(id);
    }
    @GetMapping("/findById")
    public EntityResponse findById(@RequestParam Long id ){
        return bookingService.findById(id);
    }
}
