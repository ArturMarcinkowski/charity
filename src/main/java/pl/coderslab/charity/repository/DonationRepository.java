package pl.coderslab.charity.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {



}