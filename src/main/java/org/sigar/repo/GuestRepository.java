package org.sigar.repo;

import org.sigar.model.Guest;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {
    public List<Guest> findByDateOfOccupancyBetween(LocalDate startDate, LocalDate endDate);
    public List<Guest> findByAgeBetween(Integer startAge,Integer endAge);
}
