package com.CONFIG.demo.Booking;

import com.CONFIG.demo.Shared.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingService {

    @Autowired
    BookingRepository  bookingRepository;

    @Autowired
    GuestRepo  guestRepo;
    @Autowired
    AddressRepo  addressRepo;
    public EntityResponse create(Booking booking) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Booking savedBooking = bookingRepository.save(booking);
            entityResponse.setMessage("Booking added successfully");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setEntity(savedBooking);
        }catch (Exception e){
            log.error("Exception {}",e);
        }
        return entityResponse;
    }

    public EntityResponse add(Guest guest) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Guest savedGuest = guestRepo.save(guest);
            entityResponse.setMessage("Guest added successfully");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setEntity(savedGuest);
        }catch (Exception e){
            log.error("Exception {}",e);
        }
        return entityResponse;
    }

    public EntityResponse getAll() {
        EntityResponse res = new EntityResponse<>();
        try {
            List<Booking> bookingList = bookingRepository.findAll();
            if (bookingList.size()>0){
                res.setMessage("BookingList retrieved successfully");
                res.setStatusCode(HttpStatus.FOUND.value());
                res.setEntity(bookingList);
            }else {
                res.setMessage("Not Found");
                res.setStatusCode(HttpStatus.NO_CONTENT.value());
                res.setEntity(null);
            }

        }catch (Exception e){
            log.error("Exception  {}",e);
        }
        return res;
    }

    public EntityResponse deleteById(Long id) {
        EntityResponse res = new EntityResponse<>();
        try {
            Optional<Booking> existingBooking = bookingRepository.findById(id);
            if (existingBooking.isPresent()){
                bookingRepository.deleteById(id);
                res.setMessage("Booking deleted successfully");
                res.setStatusCode(HttpStatus.OK.value());
                res.setEntity(null);
            }else {
                res.setMessage("No Data Found");
                res.setStatusCode(HttpStatus.NO_CONTENT.value());
                res.setEntity(null);
            }
        }catch (Exception e){
            log.info("Exception {}",e);
        }
      return res;
    }


    public EntityResponse findById(Long id) {
        EntityResponse response = new EntityResponse<>();
        try {
            Optional<Booking> existingBooking = bookingRepository.findById(id);
            if (existingBooking.isPresent()){
                response.setMessage("Booking found");
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(existingBooking);
            }else {
                response.setMessage("Booking Not Found");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
                response.setEntity(null);
            }
        }catch (Exception e){
            log.error("Exception {}",e);
        }
        return response;
    }
}

