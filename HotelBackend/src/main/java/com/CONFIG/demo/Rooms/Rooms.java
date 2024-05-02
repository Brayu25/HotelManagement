package com.CONFIG.demo.Rooms;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private String roomType;
    private String amenities;
    private int price;
    private Date checkinTime;
    private Date checkoutTime;
    private int rating ;
    private String roomCondition;
}
