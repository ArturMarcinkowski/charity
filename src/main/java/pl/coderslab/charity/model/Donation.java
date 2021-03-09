package pl.coderslab.charity.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Donation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private int quantity;
        @ManyToMany
        private List<Category> categories;
        @ManyToMany
        private List<Institution> institutions;

        private String street;
        private String city;
        private String zipCode;

        private LocalDate pickUpDate;
        private LocalTime pickUpTime;
        private String pickUpComment;

}
