package com.frauscher.assignment.VacationService.Repositories;

import com.frauscher.assignment.VacationService.Entities.Staff.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {
    Optional<Worker> findByEmployeeId(Long workerId);
}
