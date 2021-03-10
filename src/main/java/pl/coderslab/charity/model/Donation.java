package pl.coderslab.charity.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
        @OneToOne
        @JoinColumn(name = "institution_id")
        private Institution institution;

        private String street;
        private String city;
        private String zipCode;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate pickUpDate;
        private LocalTime pickUpTime;
        private String pickUpComment;

}
