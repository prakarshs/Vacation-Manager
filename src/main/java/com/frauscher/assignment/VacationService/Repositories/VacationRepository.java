package com.frauscher.assignment.VacationService.Repositories;

import com.frauscher.assignment.VacationService.Entities.Vacation.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<VacationRequest,Long> {

    List<VacationRequest> findByAuthorEmployeeId(Long workerId);

    List<VacationRequest> findByResolvedByEmployeeId(Long managerId);
}
