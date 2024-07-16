package com.frauscher.assignment.VacationService.Repositories;

import com.frauscher.assignment.VacationService.Entities.Staff.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Optional<Manager> findByEmployeeId(Long managerId);
}
