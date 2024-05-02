package com.CONFIG.demo.Booking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    private String guestEmail;
    private String checkinDate;
    private String checkoutDate;
    private String bookingStatus;
    private String bookingAmount;
    private String bookingDate;
    private String mobileNumber;
    private String guestName;
    private boolean tnc;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id") // Unique name for Guests join column
    private List<Guest> guests;


}
