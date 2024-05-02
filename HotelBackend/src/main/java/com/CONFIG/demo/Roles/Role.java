package com.CONFIG.demo.Roles;
//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length= 20, nullable = false, unique = true)
    private String name;


}
